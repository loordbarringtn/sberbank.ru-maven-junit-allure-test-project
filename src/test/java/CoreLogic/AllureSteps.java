package CoreLogic;
import com.codeborne.selenide.*;
import com.google.common.io.Files;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllureSteps {

    @Step("Заходим на главную страницу сайта")
    public void openWebsite(){
        open("https://www.sberbank.ru/ru/");
    }

    @Step("Проверяем наличие текста с номером лицензии на главной странице")
    public void checkLicenseTextOnMainPage() {
        String availableText = $(By.xpath("//div[@class='footer__info']//p[contains(text(),'лицензия')]")).getText();
        assertTrue(availableText.contains("Генеральная лицензия на осуществление банковских операций от 11 августа 2015 года. Регистрационный номер — 1481."));
    }

    @Step("Кликаем на элемент с изображением лупы")
    public void clickOnSearchCircle(){
        $(By.xpath("//div[@class='ya-site-form-search__button ya-site-form-search_visible ']")).click();
    }

    @Step("Вводим слово: {word} для поиска")
    public void typeWordToSearch(String word){
        $(By.className("ya-site-form__input-text")).setValue(word).pressEnter();
    }

    @Step("Кликаем на результат поиска: {searchResults}")
    public void clickOnSearchResults(String searchResults){
        $(By.xpath("(//yass-span[contains(.,'"+searchResults+"')])[1]")).click();
    }

    @Step("Кликаем по web элементу: {webElement}")
    public void clickOnWebElement(String webElement){
          $$(byText(""+webElement+"")).find(Condition.visible).click();
    }

    @Step("Кликаем по ссылке для продолжения работы в демо-режиме")
    public void clickOnDemoMode(){
        $(By.xpath("//a[@href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']")).click();
    }

    @Step("Ищем вакансию: {vacancyName}")
    public void searchVacancy(String vacancyName){
        $(By.className("career__search-input")).setValue(""+vacancyName+"").pressEnter();
    }

    @Step("Закрываем ненужную вкладку браузера")
    public void closeNeedlessTab(){
        switchTo().window(0).close();
        switchTo().window(0);
    }

    @Step("Скролим страницу вниз")
    public void scrollToBottomOfPage(){
        Selenide.executeJavaScript("window.scrollBy(0,10000)");
    }

    @Step("Закрываем всплывающее окно")
    public void closePopUp(){
        $(By.xpath("//a[@class='close']")).click();
    }

    @Step("Кликаем на лайк")
    public void clickOnLike(){
        $(By.xpath("//i[contains(@class,'like-form__icon-like')]//*[local-name()='svg']")).click();
    }

    @Step("Пишем текст для оценки улучшения страницы")
    public void typeOpinionText(){
        $(By.xpath("//textarea[@placeholder='Что мы могли бы улучшить?']")).setValue("Много чего!");
    }

    @Step("Проверяем что залогинились в Демо версию кабинета Сбербанк онлайн")
    public void checkSuccessSbolLogin(){
        String textToCheck = $(By.xpath("//div[@class='simulator-title']")).getText();
        assertTrue(textToCheck.contains("Демо-версия"));
    }

    @Step("Проверяем что залогинились в Демо версию кабинета Бизнес Сбербанк онлайн")
    public void checkSuccessBusinessOnlineLogin(){
        String textToCheck = $(by("data-test-id", "HeaderProfileWidget__userName")).getText();
        assertTrue(textToCheck.contains("Иванов Иван Иванович"));
    }

    public String getCurrentDateAndTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH.mm.ss");
        Date today = Calendar.getInstance().getTime();
        String date = dateFormat.format(today);
        return date;
    }

    public void makeScreenshot(String fileName) {
        try {
            Allure.addAttachment(fileName + ".png", new FileInputStream(screenshot(fileName+getCurrentDateAndTime())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }}
}
