package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.diploma.data.DBaseHelp;
import ru.netology.diploma.data.DataHelp;
import ru.netology.diploma.page.TourPurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.diploma.data.DBaseHelp.*;
import static ru.netology.diploma.data.DataHelp.generateUser;

public class TourWebServiceTest {
    @BeforeAll
    static void setUpAll() {
        DataHelp.creatingTestDataMap();
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        //Configuration.holdBrowserOpen = true;
        cleanUpDB();
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Must successfully buy the tour. All fields are valid, current year and month")
    void allFieldsAreValidCurrentYearAndMonthToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_01"));
        tourPurchasePage.validPopUp();
        assertEquals("011", DBaseHelp.countAll());
        assertEquals("4500000", DBaseHelp.paymentData().getAmount());
        assertEquals("APPROVED", DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the number of the second bank card with the status DECLINED")
    void allFieldsAreValidTheNumberOfTheSecondBankCardWithTheStatusDeclinedToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(generateUser("scenario_04"));
        tourPurchasePage.notValidPopUp();
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is filled with zeros")
    void allFieldsAreValidBankCardNumberIsFilledWithZerosToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(generateUser("scenario_05"));
        tourPurchasePage.notValidPopUp();
    }
    @Test
    @DisplayName("Must successfully buy the tour on credit.All fields are valid, current year and month")
    void allFieldsAreValidCurrentYearAndMonthOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(generateUser("scenario_01"));
        tourPurchasePage.validPopUp();
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the number of the second bank card with the status DECLINED")
    void allFieldsAreValidTheNumberOfTheSecondBankCardWithTheStatusDeclinedOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(generateUser("scenario_04"));
        tourPurchasePage.notValidPopUp();
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, bank card number is filled with zeros")
    void allFieldsAreValidBankCardNumberIsFilledWithZerosOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(generateUser("scenario_05"));
        tourPurchasePage.notValidPopUp();
    }
}
