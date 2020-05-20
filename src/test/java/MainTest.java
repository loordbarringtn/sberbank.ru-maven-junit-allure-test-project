import CoreLogic.AllureSteps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class MainTest {
    private AllureSteps steps = new AllureSteps();

    @BeforeAll
    public static void setUp() {
        Configuration.timeout = 20000;
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
    @Epic("Регресс тесты")
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
        steps.checkSuccessSBOLLogin();
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
    public void searchVacancy (){
        steps.openWebsite();
        steps.scrollToBottomOfPage();
        steps.clickOnWebElement("Вакансии");
        steps.searchVacancy("Java");
        steps.closeNeedlessTab();
        steps.clickOnWebElement("Java разработчик");
    }

    @Test
    @DisplayName("Тестирование раздела о Банке и проставление оценки")
    @Description(value = "Пишем текст для оценки страницы")
    @Epic("Регресс тесты")
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
