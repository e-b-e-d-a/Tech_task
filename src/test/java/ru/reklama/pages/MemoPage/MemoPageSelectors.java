package ru.reklama.pages.MemoPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MemoPageSelectors {
    public WebDriver driver;

    public MemoPageSelectors(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Пункт ДОМА в меню
     */
    @FindBy(xpath = "//a[contains(@href,'/ru/realty/houses/menus.html')]")
    public static WebElement HOUSES;

    /**
     * Пункт СОБАКИ в меню
     */
    @FindBy(xpath = "//a[contains(@href,'/ru/animals/dogs/menus.html')]")
    public static WebElement DOGS;

    /**
     * Фото собаки
     */
    @FindBy(xpath = "//img[@class='ad-photo']")
    public static WebElement DOG_PHOTO;

    /**
     * Регион - Олайне в меню
     */
    @FindBy(linkText = "Олайне")
    public static WebElement OLAJNE;

    /**
     * Вкладка - Продают в меню
     */
    @FindBy(css = "#nav > li:nth-child(2) > a > span")
    public static WebElement SELL;

    /**
     * Порода собаки - английский бульдог
     */
    @FindBy(linkText = "Английский бульдог")
    public static WebElement ENGLISH_BULDOG;

    /**
     * Количество элементов в избранном
     */
    @FindBy(xpath = "//*[@class='table_ver1']//table/tbody/tr")
    public static WebElement FAVORITES_VALUE;

    /**
     * Кнопка "Очистить список"
     */
    @FindBy(xpath = "//input[@value='Очистить список']")
    public static WebElement CLEAR_MEMO_BUTTON;

    /**
     * Кнопка "Избранное"
     */
    @FindBy(id = "favorites-link")
    public static WebElement FAVORITES_BUTTON;

    /**
     * Уведомление "У Вас пока нет фаворитов"
     */
    @FindBy(css = "#icon_view > span")
    public WebElement NOTIFICATION_MESSAGE;

    /**
     * Кнопка "Закрыть" у уведомления
     */
    @FindBy(css = "news > div > div > div.body > div.button.btn-yellow")
    public WebElement CLOSE_NOTIFICATION_BUTTON;

    /**
     * Заголовок уведомления
     */
    @FindBy(css = "news > div > div > div.header")
    public WebElement NOTIFICATION_HEADER;

    /**
     * Сердечко в правой части объявления
     */
    @FindBy(css = "tbody:nth-child(2) > tr:nth-child(1) > td > .info-wrapper > .info > .fav-add > img")
    public WebElement HEART;

    /**
     * Колтчество объявлений на плашке "Избранное"
     */
    @FindBy(id = "favorites_count")
    public WebElement FAVORITES_COUNT;



}