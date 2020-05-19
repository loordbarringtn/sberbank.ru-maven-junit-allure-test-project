import CoreLogic.AllureSteps;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private AllureSteps steps = new AllureSteps();

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 15000;
        Configuration.headless = false;
        clearBrowserCache();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Тестирование главной страницы")
    @Description(value = "Загрузка страницы и проверка текста с лицензией")
    @Severity(value = SeverityLevel.BLOCKER)
    public void openWebsite (){
        steps.openWebsite();
        steps.checkLicenseTextOnMainPage();
    }

    @Test
    @DisplayName("Тестирование входа в Сбербанк Онлайн Демо")
    @Description(value = "Пытаемся войти в демо кабинет Сбербанк онлайн")
    @Epic("Регресс тесты")
    @Feature("Тестирование онлайн кабинетов")
    @Severity(value = SeverityLevel.CRITICAL)
    public void findAndLoginDemoCabinet (){
        steps.openWebsite();
        steps.clickOnSearchCircle();
        steps.typeWordToSearch("Демо-версия Сбербанк Онлайн");
        steps.clickOnSBOLDemo();
        steps.closeNeedlessTab();
        steps.closePopUp();
        steps.checkSuccessSBOLLogin();
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
