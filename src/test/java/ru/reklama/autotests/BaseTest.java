package ru.reklama.autotests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITest;
import org.testng.annotations.Optional;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ru.reklama.pages.AdvertismentPage.AdvertismentPage;
import ru.reklama.pages.MemoPage.MemoPage;
import ru.reklama.properties.Prop;
import ru.reklama.testdata.User;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest implements ITest {
    public static WebDriver driver;
    public static MemoPage memoPage;
    public static AdvertismentPage advertismentPage;

    public static final Logger log = Logger.getLogger(BaseTest.class);
    public static Map<String, User> users;
    public static String browser;
    public static String url;
    public static String logPath;
    public static String downloadFilePath;

    public SoftAssert softAssert;

    public static Properties properties;

    private ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeSuite
    @Parameters({"file"})
    public void setupSuite(String file) {

        System.out.println("BeforeSuite");
        getLoginProperties(file);
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        logPath = "log/AutoTest.log";
        System.setProperty("logFile.name", logPath);
        String log4jConfPath = "src/test/resources/ru/reklama/properties/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        log.info("###################################################################################");
        File folder = new File("errors");
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            users = createUsers(file);
        } catch (IOException e) {
            log.error(e);
        }
        downloadFilePath = System.getProperty("user.dir") + "/" + properties.getProperty("pathToDownload");
        File folderToDownloads = new File(downloadFilePath);
        if (!folderToDownloads.exists())
            folderToDownloads.mkdir();
        url = properties.getProperty("url");
    }

    private static WebDriver getDriver() {
        log.info("Получаем driver для браузера " + browser);
        try {
            switch (browser.trim()) {
                case "chrome": {
                    Map<String, Object> preferences = new Hashtable<String, Object>();
                    preferences.put("download.default_directory", downloadFilePath);
                    preferences.put("safebrowsing.enabled", "false");
                    preferences.put("plugins.plugins_disabled", new String[] {
                            "Chrome PDF Viewer"
                    });
                    preferences.put("plugins.always_open_pdf_externally", true);
                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", preferences);
                    options.addArguments("--disable-extensions");

                    ChromeOptions cap = new ChromeOptions();
                    cap.setCapability(ChromeOptions.CAPABILITY, options);
                    if (System.getProperty("os.name").split(" ")[0].trim().equals("Windows"))
                        System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
                    else
                        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
                    driver = new ChromeDriver(options);
                    break;
                }
            }
        } catch (IllegalStateException e) {
            log.error("IllegalStateException: " + e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }



    @Parameters({"file"})
    private Map<String, User> createUsers(String file) throws IOException {
        String[] userModes = new String[]{""};

        Map<String, User> users = new HashMap<>();
        InputStream is = Prop.class.getResourceAsStream(file);
        Properties props = new Properties();
        props.load(is);

        for (String mode : userModes) {
            User user = new User();
            users.put(mode, user);
        }

        return users;
    }

    private void createPages(WebDriver driver) {
        memoPage = new MemoPage(driver);
        advertismentPage = new AdvertismentPage(driver);
    }

    @BeforeMethod
    @Parameters({"browser", "url"})
    public void setupMethod(String browser, Method method, @Optional("url") String url)throws MalformedURLException {
        this.browser = browser;
        driver = getDriver();
        log.info("Переходим по ссылке " + url);
        createPages(driver);
        if (!url.equals("url"))
            driver.get(url);
        else
            driver.get(this.url);
        testName.set(method.getName() + " " + browser);
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public static void shutDown() {
        log.info("Закрыть браузер " + browser);
        driver.quit();
    }

    @Override
    public String getTestName() {
        return testName.get();
    }

    @AfterSuite
    public static void endSuite() throws IOException, SQLException, ClassNotFoundException {
        log.info("Количество ошибок после выполнения тестов: " + isError);
        if (isError > 0) {
            File errors = new File("errors");
            if (errors.exists()) {
                deleteDirectory(errors);
            }
        }
        File downloads = new File(downloadFilePath);
        deleteDirectory(downloads);
    }

    /**
     * Метод для принудительного обозначения теста как непройденного
     */
    private static int isError = 0;
    public void assertTest(String methodName, Throwable e) throws IOException {
        isError += 1;
        String pathToFile;
        pathToFile = makeScreenshot(methodName);
        getBytes(pathToFile);
        org.testng.Assert.fail(e.getMessage());
    }

    @Attachment("Скриншот")
    private static byte[] getBytes(String path) throws IOException {
        log.error("path = " + path);
        return Files.readAllBytes(Paths.get(path, ""));
    }

    /**
     * Метод для удаления директории
     * @param folder директория
     */
    private static void deleteDirectory(File folder) {
        if (folder.exists()) {
            File files[] = folder.listFiles();
            for (File file: files) {
                file.delete();
            }
            folder.delete();
        }
    }

    /**
     * Метод для создания скриншота
     * @param methodName название метода чтобы таким же именем назвать скриншот с ошибкой
     * @return строка с путем до файла
     */
    private static String makeScreenshot(String methodName) {
        try {
            log.info("Делаем скриншот с ошибкой");
            Screenshot screenshot = new AShot().takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();
            String pathname = "errors/" + methodName  + ".png";
            File outputImage = new File(pathname);
            ImageIO.write(image, "png", outputImage);
            return pathname;
        } catch (IOException e) {
            log.error("IOException: " + e.getMessage());
            return null;
        }
    }

    /**
     * Метод для получения названия метода
     * @return строка с названием метода
     */
    public String getMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    /**
     * Метод, в который оборачиваются тесты, чтобы ловить исключения
     * @param testBody тело теста
     */
    public void runTest(TestBody testBody) {
        try {
            testBody.run();
        } catch (Exception | AssertionError e) {
            String methodName = getMethodName();
            log.error("Exception in " + methodName + ": " + e.getMessage().split("\\(")[0]);
            try {
                assertTest(methodName, e);
            } catch (IOException e1) {
                log.error(e1.getMessage());
            }
        }
    }

    public static void getLoginProperties(String file){
        InputStream is = Prop.class.getResourceAsStream(file);
        properties = new Properties();
        try {
            properties.load(is);
            is.close();
        } catch (IOException e) {
            log.error(e);
        }
    }


}
