package ru.reklama.pages.MemoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.reklama.util.Util;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static ru.reklama.autotests.BaseTest.log;


public class MemoPage {
    public WebDriver driver;
    public MemoPageSelectors memoPageSelectors;

    public MemoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.memoPageSelectors = new MemoPageSelectors(driver);
    }

    @Step("Перейти в раздел Избранное")
    public void goToMemoSection() {
        log.info("Перейти в раздел Избранное");
        Util.isElementPresent(driver,memoPageSelectors.FAVORITES_BUTTON,5);
        memoPageSelectors.FAVORITES_BUTTON.click();
    }

    @Step("Посчитать количество объявлений в избранном")
    public int countMemos(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    @Step("Вернуться на предыдущую страницу")
    public void goBack() {
        driver.navigate().back();
    }

    @Step("Очистить список избранных объявлений")
    public void clearMemoList() {
        log.info("Очистить список избранных объявлений");
        Util.isElementPresent(driver,memoPageSelectors.CLEAR_MEMO_BUTTON,5);
        memoPageSelectors.CLEAR_MEMO_BUTTON.click();
    }

    @Step("Дождаться появления сообщение \"У Вас пока нет фаворитов\"")
    public void waitUntilMessageAboutCleaningMemoIsDisplayed() {
        log.info("Дождаться появления сообщение \"У Вас пока нет фаворитов\"");
        waitForElementPresent(
                By.cssSelector("#icon_view > span"),
                "Не появилось сообщение о том, что список объявлений пуст",
                5
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutSec){
        WebDriverWait wait = new WebDriverWait(driver, timeoutSec);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndClick(By by, String error, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error, timeoutInSeconds);
        element.click();
        return element;
    }

}