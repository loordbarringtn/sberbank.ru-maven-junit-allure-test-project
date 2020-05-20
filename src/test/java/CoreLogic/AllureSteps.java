package CoreLogic;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllureSteps {

    @Step("Go to Sberbank website")
    public void openWebsite(){
        open("https://www.sberbank.ru/ru/");
    }

    @Step("Check License Text on Main Page")
    public void checkLicenseTextOnMainPage() {
        String availableText = $(By.xpath("//div[@class='footer__info']//p[contains(text(),'лицензия')]")).getText();
        assertTrue(availableText.contains("Генеральная лицензия на осуществление банковских операций от 11 августа 2015 года. Регистрационный номер — 1481."));
    }

    @Step("Click on search circle")
    public void clickOnSearchCircle(){
        $(By.xpath("//div[@class='ya-site-form-search__button ya-site-form-search_visible ']")).click();
    }

    @Step("Type word: {word} to search")
    public void typeWordToSearch(String word){
        $(By.className("ya-site-form__input-text")).setValue(word).pressEnter();
    }

    @Step("Click on SBOL Demo")
    public void clickOnSBOLDemo(){
        $(By.xpath("(//yass-span[contains(.,'Демо-версия Сбербанк Онлайн')])[1]")).click();
    }

    @Step("Close on needless tab")
    public void closeNeedlessTab(){
        switchTo().window(0).close();
        switchTo().window(0);
    }

    @Step("Close popup")
    public void closePopUp(){
        $(By.xpath("//a[@class='close']")).click();
    }

    @Step("Check that we logged in successfully")
    public void checkSuccessSBOLLogin(){
        String textToCheck = $(By.xpath("//div[@class='simulator-title']")).getText();
        assertTrue(textToCheck.contains("Демо-версия"));
    }


}
