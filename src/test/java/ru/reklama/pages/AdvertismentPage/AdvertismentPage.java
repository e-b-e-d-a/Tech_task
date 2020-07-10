package ru.reklama.pages.AdvertismentPage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.reklama.pages.MemoPage.MemoPageSelectors;
import ru.reklama.util.Util;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static ru.reklama.autotests.BaseTest.log;


public class AdvertismentPage {
    public WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public AdvertismentPageSelectors advertismentPageSelectors;

    public AdvertismentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.advertismentPageSelectors = new AdvertismentPageSelectors(driver);
    }

    @Step("Кликнуть по названию раздела \"Дома\"")
    public void clickOnHousesSection() {
        log.info("Кликнуть по названию раздела \"Дома\"");
        Util.isElementPresent(driver,advertismentPageSelectors.HOUSES,5);
        advertismentPageSelectors.HOUSES.click();
    }

    @Step("Кликнуть по названию региона - Олайне")
    public void clickOnRegionLink() {
        log.info("Кликнуть по названию региона - Олайне");
        Util.isElementPresent(driver,advertismentPageSelectors.OLAJNE,5);
        advertismentPageSelectors.OLAJNE.click();
    }

    @Step("Кликнуть по названию раздела \"Собаки\"")
    public void clickOnDogsSection() {
        log.info("Кликнуть по названию раздела \"Собаки\"");
        Util.isElementPresent(driver,advertismentPageSelectors.DOGS,5);
        advertismentPageSelectors.DOGS.click();
    }

    @Step("Перейти на вкладку \"Продают\"")
    public void goToSellLink() {
        log.info("Перейти на вкладку \"Продают\"");
        Util.isElementPresent(driver,advertismentPageSelectors.SELL,5);
        advertismentPageSelectors.SELL.click();
    }

    @Step("Выбрать породу собаки - английский бульдог")
    public void selectDogType() {
        log.info("Выбрать породу собаки - английский бульдог");
        Util.isElementPresent(driver,advertismentPageSelectors.ENGLISH_BULDOG,4);
        advertismentPageSelectors.ENGLISH_BULDOG.click();
    }

    @Step("Кликнуть на фото собаки")
    public void clickOnDogPhoto() {
        log.info("Кликнуть на фото собаки");
        Util.isElementPresent(driver,advertismentPageSelectors.DOG_PHOTO,5);
        advertismentPageSelectors.DOG_PHOTO.click();
    }

    @Step("Кликнуть по кнопке \"Добавить в избранное\" в правой части объявления")
    public void clickAddToFavoritesOnRightPane() throws InterruptedException {
        log.info("Кликнуть по кнопке \"Добавить в избранное\" в правой части объявления");
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child(1)"));
        Thread.sleep(2000);
        action.moveToElement(element).build().perform();
        Util.isElementPresent(driver,advertismentPageSelectors.HEART,5);
        advertismentPageSelectors.HEART.click();
    }

    @Step("Кликнуть по кнопке \"Удалить из избранного\" в правой части объявления")
    public void clickRemoveFromFavoritesOnRightPane() throws InterruptedException {
        log.info("Кликнуть по кнопке \"Удалить из избранного\" в правой части объявления");
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector("tbody > tr.over"));
        Thread.sleep(2000);
        action.moveToElement(element).build().perform();
        Util.isElementPresent(driver,advertismentPageSelectors.REMOVE,5);
        advertismentPageSelectors.REMOVE.click();
    }

    @Step("Перейти в раздел Избранное")
    public void goToMemoSection()
    {
        log.info("Перейти в раздел Избранное");
        Util.isElementPresent(driver,advertismentPageSelectors.FAVORITES_BUTTON,5);
        advertismentPageSelectors.FAVORITES_BUTTON.click();
    }

    @Step("Прокрутить объявление до кнопки \"Добавить в избранное\"")
    public void scrollToAddToMemoButton()
    {
        log.info("Прокрутить объявление до кнопки \"Добавить в избранное\"");
      //  Util.isElementPresent(driver,advertismentPageSelectors.ADD_TO_FAVORITE,5);
        WebElement DIVelement = driver.findElement(By.cssSelector(".favourite"));
        js.executeScript("arguments[0].scrollIntoView(true)", DIVelement);
       // advertismentPageSelectors.ADD_TO_FAVORITE.sendKeys(Keys.PAGE_DOWN);

    }

    @Step("Кликнуть по иконке \"Добавить в избранное\"")
    public void addToMemoButton()
    {
        log.info("Кликнуть по иконке \"Добавить в избранное\"");
        Util.isElementPresent(driver,advertismentPageSelectors.ADD_TO_FAVORITE,5);
        advertismentPageSelectors.ADD_TO_FAVORITE.click();

    }

    @Step("Удалить объявление из избранного")
    public void removeFromMemoButton()
    {
        log.info("Удалить объявление из избранного");
        Util.isElementPresent(driver,advertismentPageSelectors.REMOVE_FROM_FAVORITE,5);
        advertismentPageSelectors.REMOVE_FROM_FAVORITE.click();
    }

    @Step("Кликнуть на выпадающий список с категориями объявлений")
    public void advertismentDropDownClick()
    {
        log.info("Кликнуть на выпадающий список с категориями объявлений");
        Util.isElementPresent(driver,advertismentPageSelectors.DROP_DOWN_CATEGORY,5);
        advertismentPageSelectors.DROP_DOWN_CATEGORY.click();
    }

    @Step("Выбрать категорию - Тарнспорт")
    public void selectTransportCategory()
    {
        log.info("Выбрать категорию - Тарнспорт");
        Util.isElementPresent(driver,advertismentPageSelectors.TRANSPORT,5);
        advertismentPageSelectors.TRANSPORT.click();
    }

    @Step("Выбрать категорию - Мототраспорт")
    public void selectMototransportCategory()
    {
        log.info("Выбрать категорию - Мототраспорт");
        Util.isElementPresent(driver,advertismentPageSelectors.MOTOTRANSPORT,5);
        advertismentPageSelectors.MOTOTRANSPORT.click();
    }


    @Step("Кликнуть на раскрывающийся список у ссылки - Мототранспорт")
    public void showMotoTypes()
    {
        log.info("Кликнуть на раскрывающийся список у ссылки - Мототранспорт");
        Util.isElementPresent(driver,advertismentPageSelectors.UNPACK,5);
        advertismentPageSelectors.UNPACK.click();
    }

    @Step("Кликнуть на раздел Мотоциклы")
    public void selectMotocycles()
    {
        log.info("Кликнуть на раздел Мотоциклы");
        Util.isElementPresent(driver, advertismentPageSelectors.MOTOCYCLES, 5);
        advertismentPageSelectors.MOTOCYCLES.click();
    }

    @Step("Кликнуть на фото мотоцикла")
    public void clickOnMotoPhoto() {
        Util.isElementPresent(driver,advertismentPageSelectors.MOTO_PHOTO,5);
        advertismentPageSelectors.MOTO_PHOTO.click();
    }

    @Step("Вернуться на предыдущую страницу")
    public void goBack()
    {
        log.info("Вернуться назад");
        driver.navigate().back();
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