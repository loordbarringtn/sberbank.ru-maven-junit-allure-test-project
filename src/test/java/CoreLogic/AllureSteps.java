package CoreLogic;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
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

    @Step("Click on search results: {searchResults}")
    public void clickOnSearchResults(String searchResults){
        $(By.xpath("(//yass-span[contains(.,'"+searchResults+"')])[1]")).click();
    }

    @Step("Click on web element: {webElement}")
    public void clickOnWebElement(String webElement){
        $$(byText(""+webElement+"")).find(Condition.visible).click();
    }

    @Step("Click on Demo mode to continue")
    public void clickOnDemoMode(){
        $(By.xpath("//a[@href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']")).click();
    }

    @Step("Searching Vacancy: {webElement}")
    public void searchVacancy(String vacancyName){
        $(By.className("career__search-input")).setValue(""+vacancyName+"").pressEnter();
    }

    @Step("Close on needless tab")
    public void closeNeedlessTab(){
        switchTo().window(0).close();
        switchTo().window(0);
    }

    @Step("Scroll to the Bottom of the Page")
    public void scrollToBottomOfPage(){
        Selenide.executeJavaScript("window.scrollBy(0,10000)");
    }

    @Step("Close popup")
    public void closePopUp(){
        $(By.xpath("//a[@class='close']")).click();
    }

    @Step("Click on like")
    public void clickOnLike(){
        $(By.xpath("//i[contains(@class,'like-form__icon-like')]//*[local-name()='svg']")).click();
    }

    @Step("Type text in opinion review")
    public void typeOpinionText(){
        $(By.xpath("//textarea[@placeholder='Что мы могли бы улучшить?']")).setValue("Много чего!");
    }

    @Step("Check that we logged in demo SBOL successfully")
    public void checkSuccessSBOLLogin(){
        String textToCheck = $(By.xpath("//div[@class='simulator-title']")).getText();
        assertTrue(textToCheck.contains("Демо-версия"));
    }

    @Step("Check that we logged in demo Sberbank Business online successfully")
    public void checkSuccessBusinessOnlineLogin(){
        String textToCheck = $(by("data-test-id", "HeaderProfileWidget__userName")).getText();
        assertTrue(textToCheck.contains("Иванов Иван Иванович"));
    }

}
