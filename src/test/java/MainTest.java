import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 10000;
    }

    @Test
    @DisplayName("Тестирование главной страницы")
    public void openWebsite (){
        open("https://www.sberbank.ru/ru/");
        String availableText = $(By.xpath("//div[@class='footer__info']//p[contains(text(),'лицензия')]")).getText();
        assertTrue(availableText.contains("Генеральная лицензия на осуществление банковских операций от 11 августа 2015 года. Регистрационный номер — 1481."));
    }

    @Test
    @DisplayName("Тестирование входа в Сбербанк Бизнес Онлайн Демо")
    public void findAndLoginBusinessDemoCabinet (){
        open("https://www.sberbank.ru/ru/");
        $(By.cssSelector(".ya-site-form-search__button")).click();
        $(By.className("ya-site-form__input-text")).setValue("Бизнес онлайн").pressEnter();
        $(By.xpath("//yass-span[contains(.,'(СББОЛ)')]")).click();
        switchTo().window(0).close();
        switchTo().window(0);
        $(By.xpath("//a[contains(@class,'b-btn btn-inverse b-btn_green-hover')]")).click();
        $(By.xpath("//a[@href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']")).click();
        String textToCheck = $(by("data-test-id", "HeaderProfileWidget__userName")).getText();
        assertTrue(textToCheck.contains("Иванов Иван Иванович"));
    }

}
