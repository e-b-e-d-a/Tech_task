package ru.reklama.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Util {

    private WebDriver driver;

    public Util(WebDriver driver){
        this.driver = driver;
    }

    public static void handleMultipleWindows(WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }

    /**
     * Метод для проверки есть ли элемент в DOM. Есть элемент возвращаем true, нет элемента возвращаем false
     * @param driver драйвер
     * @param element элемент
     */
    public static boolean isElementPresent(WebDriver driver,  WebElement element, int time) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            Wait<WebDriver> wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOf(element));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }

    public static String getFileNameFromUrl(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    /**
     * Метод для ввода даты
     * @param field селектор поля ввода
     * @param date дата
     */
    public static void inputDate(WebElement field, String date) {
        field.sendKeys(date);
    }



}