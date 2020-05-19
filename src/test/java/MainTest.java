import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 15000;
        Configuration.headless = false;
        clearBrowserCache();
    }

    @Test
    @DisplayName("Тестирование главной страницы")
    public void openWebsite (){
        open("https://www.sberbank.ru/ru/");
        String availableText = $(By.xpath("//div[@class='footer__info']//p[contains(text(),'лицензия')]")).getText();
        assertTrue(availableText.contains("Генеральная лицензия на осуществление банковских операций от 11 августа 2015 года. Регистрационный номер — 1481."));
    }

    @Test
    @DisplayName("Тестирование входа в Сбербанк Онлайн Демо")
    public void findAndLoginDemoCabinet (){
        open("https://www.sberbank.ru/ru/");
        $(By.xpath("//div[contains(@class, 'search__button')]")).click();
        $(By.className("ya-site-form__input-text")).setValue("демо").pressEnter();
        $(By.xpath("(//yass-span[contains(.,'Демо-версия Сбербанк Онлайн')])[1]")).click();
        switchTo().window(0).close();
        switchTo().window(0);
        $(By.xpath("//a[@class='close']")).click();
        String textToCheck = $(By.xpath("//div[@class='simulator-title']")).getText();
        assertTrue(textToCheck.contains("Демо-версия"));
    }

    @Test
    @DisplayName("Тестирование входа в Сбербанк Бизнес Онлайн Демо")
    public void findAndLoginBusinessDemoCabinet (){
        open("https://www.sberbank.ru/ru/");
        $(By.xpath("//div[contains(@class, 'search__button')]")).click();
        $(By.className("ya-site-form__input-text")).setValue("Бизнес онлайн").pressEnter();
        $(By.xpath("//yass-span[contains(.,'(СББОЛ)')]")).click();
        switchTo().window(0).close();
        switchTo().window(0);
        $(By.xpath("//a[contains(@class,'b-btn btn-inverse b-btn_green-hover')]")).click();
        $(By.xpath("//a[@href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']")).click();
        String textToCheck = $(by("data-test-id", "HeaderProfileWidget__userName")).getText();
        assertTrue(textToCheck.contains("Иванов Иван Иванович"));
    }

    @Test
    @DisplayName("Тестирование раздела Вакансии")
    public void searchJob (){
        open("https://www.sberbank.ru/ru/");
        Selenide.executeJavaScript("window.scrollBy(0,10000)");
        $(By.xpath("//a[contains(@class,'link_s')][contains(text(),'Вакансии')]")).click();
        $(By.className("career__search-input")).setValue("Java").pressEnter();
        switchTo().window(0).close();
        switchTo().window(0);
        $(By.xpath("//a[contains(text(),'Java разработчик')]")).waitUntil(Condition.visible, 60000).click();
    }

    @Test
    @DisplayName("Тестирование раздела о Банке и проставление оценки")
    public void rateBank (){
        open("https://www.sberbank.ru/ru/");
        $(By.xpath("//a[contains(text(),'О банке')]")).click();
        $(By.xpath("//a[contains(.,'О миссии и ценностях банка')]")).click();
        $(By.xpath("//i[contains(@class,'like-form__icon-like')]//*[local-name()='svg']")).click();
        $(By.xpath("//textarea[@placeholder='Что мы могли бы улучшить?']")).setValue("Много чего!");
        $(By.xpath("//button[@class='kit-button kit-button_type_big like-form__button']")).click();
    }

}
