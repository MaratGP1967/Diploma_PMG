package ru.netology.diploma.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.diploma.data.DataHelp;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TourPurchasePage {

    private SelenideElement tourName = $x("//h2[contains (text(), 'Путешествие дня')]");
    private SelenideElement tourPic = $x("//img[@alt=`Марракэш`]");
    private SelenideElement bayButton = $x("//button[.//*[(text()='Купить')]]");
    private SelenideElement creditButton = $x("//button[.//*[(text()='Купить в кредит')]]");
    private SelenideElement numberField = $x("//span[(text()='Номер карты')]/..//input");
    private SelenideElement monthField = $x("//span[(text()='Месяц')]/..//input");
    private SelenideElement yearField = $x("//span[(text()='Год')]/..//input");
    private SelenideElement nameField = $x("//span[(text()='Владелец')]/..//input");
    private SelenideElement securityCodesField = $x("//span[(text()='CVC/CVV')]/..//input");
    private SelenideElement sendButton = $x("//button[.//*[(text()='Продолжить')]]");

    private SelenideElement title = $x("//div[contains (text(), 'Ошибка')]");
    private SelenideElement text = $x("//*[@data-test-id='error-notification']//*[@class='notification__content']");
    private SelenideElement icon = $x("//*[@data-test-id='error-notification']//button");

    public TourPurchasePage() {
        tourName.shouldBe(visible);
        tourPic.shouldBe(visible);
    }

    public void getBayTourButton(DataHelp.UserCardInfo user) {
        bayButton.click();
        cardDataEntry(user);
    }

    public void getCreditTourButton(DataHelp.UserCardInfo user) {
        creditButton.click();
        cardDataEntry(user);
    }

    public void cardDataEntry(DataHelp.UserCardInfo user) {
        // ((JavascriptExecutor) driver).executeScript("document.getElementById('my-form').reset()");
        numberField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        numberField.setValue(user.getNumber());
        monthField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        monthField.setValue(user.getMonth());
        yearField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        yearField.setValue(user.getYear());
        nameField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        nameField.setValue(user.getName());
        securityCodesField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        securityCodesField.setValue(user.getSecurityCodes());
        sendButton.click();
    }
    public void notValidPopUp() {
        title.shouldBe(Condition.visible);
        text.shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
        icon.click();
    }

    public void notValidPassPopUp() {
        title.shouldBe(Condition.visible);
        text.shouldHave(Condition.text("Ввод пароля заблокирован! Пароль введен не верно три раза.")).shouldBe(Condition.visible);
        icon.click();
    }
}
