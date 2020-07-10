package ru.reklama.autotests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemoTest extends BaseTest {

    @BeforeMethod
    public void before() {
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void after() {
        if (driver.getWindowHandles().size() > 1) {
            driver.close();
        }
        else {
            driver.quit();
        }
    }


    @Test
    @Description("Добавить одно объявление из общего списка в избранное, " +
                 "зайти в избранное и очистить список")
    public void addOneAdvertismentToMemoAndCleanMemo() {
        runTest(() -> {
            Thread.sleep(2000);
            advertismentPage.clickOnHousesSection();
            advertismentPage.clickOnRegionLink();
            advertismentPage.clickAddToFavoritesOnRightPane();
            softAssert.assertEquals
                    ("1",
                    advertismentPage.advertismentPageSelectors.FAVORITES_COUNT
                            .getText());
            memoPage.goToMemoSection();
            memoPage.clearMemoList();
            memoPage.waitUntilMessageAboutCleaningMemoIsDisplayed();
            softAssert.assertTrue(
                    memoPage.countMemos
                            (By.xpath("//*[@class='table_ver1']//table/tbody/tr"))
                            < 1);
            softAssert.assertAll();
        });
    }

    @Test
    @Description("Открыть карточку с объявлением и добавить объявление в избранное," +
                 "убедиться, что объявление появилось в избранном" +
                 "вернуться на карточку объявления и удалить его из избранного")
    public void addOneAdvertismentFromCardToMemoAndCleanMemo() {
        runTest(() -> {
            advertismentPage.clickOnDogsSection();
            advertismentPage.goToSellLink();
            advertismentPage.selectDogType();
            advertismentPage.clickOnDogPhoto();
        //    advertismentPage.scrollToAddToMemoButton();
            advertismentPage.addToMemoButton();
            Thread.sleep(2000);
            softAssert.assertEquals(
                    "1",
                    advertismentPage.advertismentPageSelectors.FAVORITES_COUNT
                            .getText());
            memoPage.goToMemoSection();
            softAssert.assertTrue(
                    memoPage.countMemos
                            (By.xpath("//*[@class='table_ver1']//table/tbody/tr"))
                            > 0);
            advertismentPage.goBack();
            advertismentPage.removeFromMemoButton();
            softAssert.assertEquals(
                    "Добавить в избранное",
                    advertismentPage.advertismentPageSelectors.ADD_TO_FAVORITE
                            .getText());
            softAssert.assertAll();
        });
    }

    @Test
    @Description("Добавить несколько объявлений из разных категорий в избранное, " +
            "зайти в избранное и очистить список")
    public void addFewAdvertismentToMemoAndCleanMemo() {
        runTest(() -> {
            Thread.sleep(2000);
            advertismentPage.clickOnHousesSection();
            advertismentPage.clickOnRegionLink();
            advertismentPage.clickAddToFavoritesOnRightPane();
            advertismentPage.goBack();
            advertismentPage.goBack();
            advertismentPage.selectMototransportCategory();
            advertismentPage.selectMotocycles();
            advertismentPage.clickOnMotoPhoto();
            advertismentPage.addToMemoButton();
            memoPage.goToMemoSection();
            softAssert.assertEquals
                    ("2",
                            memoPage.memoPageSelectors.FAVORITES_COUNT
                                    .getText());
            memoPage.clearMemoList();
            memoPage.waitUntilMessageAboutCleaningMemoIsDisplayed();
            softAssert.assertTrue(
                    memoPage.countMemos
                            (By.xpath("//*[@class='table_ver1']//table/tbody/tr"))
                            < 1);
            softAssert.assertAll();
        });
    }

    @Test
    @Description("Добавить несколько объявлений в избранное, " +
            "зайти в избранное и удалить одно из объявлений" +
            "найти в поиске удаленное объявление и снова добавить его в список")
    public void addFewAdvertismentToMemoCleanMemoAndRepeatAdd() {
        runTest(() -> {
            advertismentPage.clickOnDogsSection();
            advertismentPage.goToSellLink();
            advertismentPage.selectDogType();
            advertismentPage.clickOnDogPhoto();
            advertismentPage.addToMemoButton();
            advertismentPage.goBack();
            advertismentPage.goBack();
            advertismentPage.advertismentDropDownClick();
            advertismentPage.selectTransportCategory();
            advertismentPage.showMotoTypes();
            advertismentPage.selectMotocycles();
            advertismentPage.clickOnMotoPhoto();
            advertismentPage.addToMemoButton();
            memoPage.goToMemoSection();
            advertismentPage.clickRemoveFromFavoritesOnRightPane();
            advertismentPage.goBack();
            softAssert.assertAll();
        });
    }

    @Test
    @Description("Открывать карточки с разными объявлениями и добавить их в избранное," +
            "убедиться, что объявления появились в избранном")
    public void addFewAdvertismentFromCardToMemo() {
        runTest(() -> {
            advertismentPage.clickOnDogsSection();
            advertismentPage.goToSellLink();
            advertismentPage.selectDogType();
            advertismentPage.clickOnDogPhoto();
            advertismentPage.addToMemoButton();
            advertismentPage.goBack();
            advertismentPage.goBack();
            advertismentPage.advertismentDropDownClick();
            advertismentPage.selectTransportCategory();
            advertismentPage.showMotoTypes();
            advertismentPage.selectMotocycles();
            advertismentPage.clickOnMotoPhoto();
            advertismentPage.addToMemoButton();
            memoPage.goToMemoSection();
            softAssert.assertEquals
                    ("2",
                            memoPage.memoPageSelectors.FAVORITES_COUNT
                                    .getText());
            softAssert.assertAll();
        });
    }

}