package ru.reklama.pages.AdvertismentPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdvertismentPageSelectors {
    public WebDriver driver;

    public AdvertismentPageSelectors(WebDriver driver) {
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
     * Кнопка "Избранное"
     */
    @FindBy(id = "favorites-link")
    public static WebElement FAVORITES_BUTTON;

    /**
     * Сердечко в правой части объявления
     */
    @FindBy(css = "tbody:nth-child(2) > tr:nth-child(1) > td > .info-wrapper > .info > .fav-add > img")
    public WebElement HEART;

    /**
     * Крестик в правой части объявления
     */
    @FindBy(css = "tbody > tr:nth-child(1) > td:nth-child(4) > div > div > .fav-remove > img")
    public WebElement REMOVE;

    /**
     * Количество элементов у кнопки "Избранное"
     */
    @FindBy(id = "favorites_count")
    public static WebElement FAVORITES_COUNT;

    /**
     * Кнопка "Добавить в избранное"
     */
    @FindBy(css = ".favourite")
    public static WebElement ADD_TO_FAVORITE;

    /**
     * Кнопка "Удалить из избранного
     */
    @FindBy(id = "favs-link")
    public static WebElement REMOVE_FROM_FAVORITE;

    /**
     * Выпадающий список с категориями объявлений
     */
    @FindBy(id = "cat-0")
    public static WebElement DROP_DOWN_CATEGORY;

    /**
     * Категория "Транспорт"
     */
    @FindBy(xpath = "//a[contains(@href,'/ru/transport/sell/menus.html')]")
    public static WebElement TRANSPORT;

    /**
     * Раскрывающийся список у категории "Мототранспорт"
     */
    @FindBy(css = "#categories > fieldset > ul > li:nth-child(3) > a")
    public static WebElement UNPACK;

    /**
     * Раздел Мотоциклы
     */
    @FindBy(xpath = "//a[contains(@href,'/ru/transport/mototransport/motorcycles/table.html')]")
    public static WebElement MOTOCYCLES;

    /**
     * Раздел Мототранспорт на главной странице
     */
    @FindBy(xpath = "//a[contains(@href,'/ru/transport/mototransport/menus.html')]")
    public static WebElement MOTOTRANSPORT;

    /**
     * Фото байка
     */
    @FindBy(css = "tr:nth-child(3) .ad-photo")
    public static WebElement MOTO_PHOTO;
}