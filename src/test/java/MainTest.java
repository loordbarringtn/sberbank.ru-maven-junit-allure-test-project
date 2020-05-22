import CoreLogic.AllureSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class MainTest {
    private AllureSteps steps = new AllureSteps();

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 25000;
        Configuration.headless = false;
        clearBrowserCache();
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }


    @AfterAll
    public static void tearDown() {
        SelenideLogger.removeListener("allure");
        closeWebDriver();
    }

    @Test
    @DisplayName("Тестирование главной страницы")
    @Description(value = "Загрузка страницы и проверка текста с лицензией")
    @Epic("Смок тесты")
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
        steps.clickOnSearchResults("Демо-версия Сбербанк Онлайн");
        steps.closeNeedlessTab();
        steps.closePopUp();
        steps.checkSuccessSbolLogin();
    }

    @Test
    @DisplayName("Тестирование входа в Сбербанк Бизнес Онлайн Демо")
    @Description(value = "Пытаемся войти в демо кабинет Сбербанк Бизнес онлайн")
    @Epic("Регресс тесты")
    @Feature("Тестирование онлайн кабинетов")
    @Severity(value = SeverityLevel.CRITICAL)
    public void findAndLoginBusinessDemoCabinet (){
        steps.openWebsite();
        steps.clickOnSearchCircle();
        steps.typeWordToSearch("Бизнес онлайн");
        steps.clickOnSearchResults("СББОЛ");
        steps.closeNeedlessTab();
        steps.clickOnWebElement("Демо-доступ");
        steps.clickOnDemoMode();
        steps.checkSuccessBusinessOnlineLogin();
    }

    @Test
    @Flaky
    @Issue("Время от времени, поиск выдаёт значение, отличное от значения, введённого в строку поиска")
    @DisplayName("Тестирование раздела Вакансии")
    @Description(value = "Ищем вакансию для Java разработчика")
    @Epic("Регресс тесты")
    @Severity(value = SeverityLevel.NORMAL)
    @RepeatedTest(10)
    public void searchVacancy () throws IOException {
        steps.openWebsite();
        steps.scrollToBottomOfPage();
        steps.clickOnWebElement("Вакансии");
        steps.searchVacancy("Java");
        steps.makeScreenshot("BeAware");
        steps.closeNeedlessTab();
        steps.clickOnWebElement("Java разработчик");
    }

    @Test
    @DisplayName("Тестирование раздела о Банке и проставление оценки")
    @Description(value = "Пишем текст для оценки страницы")
    @Epic("Смок тесты")
    @Severity(value = SeverityLevel.NORMAL)
    public void rateBank (){
        steps.openWebsite();
        steps.clickOnWebElement("О банке");
        steps.clickOnWebElement("О миссии и ценностях банка");
        steps.scrollToBottomOfPage();
        steps.clickOnLike();
        steps.typeOpinionText();
        steps.clickOnWebElement("Отправить");
    }

}
