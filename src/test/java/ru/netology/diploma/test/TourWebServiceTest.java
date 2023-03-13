package ru.netology.diploma.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import ru.netology.diploma.data.DBaseHelp;
import ru.netology.diploma.data.DataHelp;
import ru.netology.diploma.page.TourPurchasePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        DBaseHelp.cleanUpDB();
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Must successfully buy the tour. All fields are valid, current year and month")
    void allFieldsAreValidCurrentYearAndMonthToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_01"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour. All fields are valid, current year and month plus five years")
    void allFieldsAreValidCurrentYearAndMonthPlusFiveYearsToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_02"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour.  All fields are valid, first name and last name two letters")
    void allFieldsAreValidFirstNameAndLastNameTwoLettersToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_03"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the number of the second bank card with the status DECLINED")
    void allFieldsAreValidTheNumberOfTheSecondBankCardWithTheStatusDeclinedToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_04"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.notValidDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.notValidDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is filled with zeros")
    void allFieldsAreValidBankCardNumberIsFilledWithZerosToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_05"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is empty")
    void allFieldsAreValidBankCardNumberIsEmptyToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_06"));
        tourPurchasePage.numberNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, bank card number is partially filled")
    void allFieldsAreValidBankCardNumberIsPartiallyFilledToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_07"));
        tourPurchasePage.numberNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Year field is expired")
    void allFieldsAreValidTheYearFieldIsExpiredToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_08"));
        tourPurchasePage.yearCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the field Year with a period of more than five years")
    void allFieldsAreValidTheFieldYearWithAPeriodOfMoreThanFiveYearsToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_09"));
        tourPurchasePage.yearInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Year field is filled with zeros")
    void allFieldsAreValidTheYearFieldIsFilledWithZerosToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_10"));
        tourPurchasePage.yearCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Year field is empty")
    void allFieldsAreValidTheYearFieldIsEmptyToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_11"));
        tourPurchasePage.yearNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, field Month is expired")
    void allFieldsAreValidFieldMonthIsExpiredToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_12"));
        tourPurchasePage.monthCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the field Month with a period of more than five years")
    void allFieldsAreValidTheFieldMonthWithAPeriodOfMoreThanFiveYearsToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_13"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Month field is filled with zeros")
    void allFieldsAreValidTheMonthFieldIsFilledWithZerosToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_14"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Month field is filled with a non existent value")
    void allFieldsAreValidTheMonthFieldIsFilledWithANonExistentValueToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_15"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, field Month is empty")
    void allFieldsAreValidFieldMonthIsEmptyToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_16"));
        tourPurchasePage.monthNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the field Owner one letter in the name and surname")
    void allFieldsAreValidInTheFieldOwnerOneLetterInTheNameAndSurnameToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_17"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field, first or last name with a non alphabetic character")
    void allFieldsAreValidInTheOwnerFieldFirstOrLastNameWithANonAlphabeticCharacterToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_18"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field, first or last name with a numeric character")
    void allFieldsAreValidInTheOwnerFieldFirstOrLastNameWithANumericCharacterToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_19"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the field Owner the first or last name is merged")
    void allFieldsAreValidInTheFieldOwnerTheFirstOrLastNameIsMergedToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_20"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field there is a hyphen before the name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenBeforeTheNameToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_21"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field there is a hyphen after the name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenAfterTheNameToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_22"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field there is a hyphen before the last name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenBeforeTheLastNameToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_23"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, in the Owner field there is a hyphen after the last name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenAfterTheLastNameToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_24"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the Owner field is empty")
    void allFieldsAreValidTheOwnerFieldIsEmptyToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_25"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the CVC/CVV field is filled with a non existent value")
    void allFieldsAreValidTheCvcCvvFieldIsFilledWithANonExistentValueToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_26"));
        tourPurchasePage.securityCodesNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, the CVC/CVV field is filled with zeros")
    void allFieldsAreValidTheCvcCvvFieldIsFilledWithZerosToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_27"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataToBay().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataToBay().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataToBay().getStatus(), DBaseHelp.paymentData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour. All fields are valid, field CVC/CVV is empty")
    void allFieldsAreValidFieldCvcCvvIsEmptyToBay() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getBayTourButton(DataHelp.generateUser("scenario_28"));
        tourPurchasePage.securityCodesNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must successfully buy the tour on credit.All fields are valid, current year and month")
    void allFieldsAreValidCurrentYearAndMonthOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_01"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataOnCredit().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataOnCredit().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.paymentData().getStatus());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.creditData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour on credit.All fields are valid, current year and month plus five years")
    void allFieldsAreValidCurrentYearAndMonthPlusFiveYearsOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_02"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataOnCredit().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataOnCredit().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.paymentData().getStatus());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.creditData().getStatus());
    }

    @Test
    @DisplayName("Must successfully buy the tour on credit. All fields are valid, first name and last name two letters")
    void allFieldsAreValidFirstNameAndLastNameTwoLettersOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_03"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataOnCredit().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataOnCredit().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.paymentData().getStatus());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.creditData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the number of the second bank card with the status DECLINED")
    void allFieldsAreValidTheNumberOfTheSecondBankCardWithTheStatusDeclinedOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_04"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidDataOnCredit().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.notValidDataOnCredit().getStatus(), DBaseHelp.creditData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, bank card number is filled with zeros")
    void allFieldsAreValidBankCardNumberIsFilledWithZerosOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_05"));
        tourPurchasePage.notValidPopUp();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, bank card number is empty")
    void allFieldsAreValidBankCardNumberIsEmptyOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_06"));
        tourPurchasePage.numberNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, bank card number is partially filled")
    void allFieldsAreValidBankCardNumberIsPartiallyFilledOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_07"));
        tourPurchasePage.numberNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Year field is expired")
    void allFieldsAreValidTheYearFieldIsExpiredOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_08"));
        tourPurchasePage.yearCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the field Year with a period of more than five years")
    void allFieldsAreValidTheFieldYearWithAPeriodOfMoreThanFiveYearsOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_09"));
        tourPurchasePage.yearInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Year field is filled with zeros")
    void allFieldsAreValidTheYearFieldIsFilledWithZerosOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_10"));
        tourPurchasePage.yearCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Year field is empty")
    void allFieldsAreValidTheYearFieldIsEmptyOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_11"));
        tourPurchasePage.yearNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, field Month is expired")
    void allFieldsAreValidFieldMonthIsExpiredOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_12"));
        tourPurchasePage.monthCardExpired();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the field Month with a period of more than five years")
    void allFieldsAreValidTheFieldMonthWithAPeriodOfMoreThanFiveYearsOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_13"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Month field is filled with zeros")
    void allFieldsAreValidTheMonthFieldIsFilledWithZerosOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_14"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Month field is filled with a non existent value")
    void allFieldsAreValidTheMonthFieldIsFilledWithANonExistentValueOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_15"));
        tourPurchasePage.monthInvalidCardDate();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, field Month is empty")
    void allFieldsAreValidFieldMonthIsEmptyOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_16"));
        tourPurchasePage.monthNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the field Owner one letter in the name and surname")
    void allFieldsAreValidInTheFieldOwnerOneLetterInTheNameAndSurnameOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_17"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field, first or last name with a non alphabetic character")
    void allFieldsAreValidInTheOwnerFieldFirstOrLastNameWithANonAlphabeticCharacterOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_18"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field, first or last name with a numeric character")
    void allFieldsAreValidInTheOwnerFieldFirstOrLastNameWithANumericCharacterOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_19"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the field Owner the first or last name is merged")
    void allFieldsAreValidInTheFieldOwnerTheFirstOrLastNameIsMergedOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_20"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field there is a hyphen before the name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenBeforeTheNameOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_21"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field there is a hyphen after the name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenAfterTheNameOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_22"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field there is a hyphen before the last name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenBeforeTheLastNameOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_23"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, in the Owner field there is a hyphen after the last name")
    void allFieldsAreValidInTheOwnerFieldThereIsAHyphenAfterTheLastNameOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_24"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the Owner field is empty")
    void allFieldsAreValidTheOwnerFieldIsEmptyOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_25"));
        tourPurchasePage.nameNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the CVC/CVV field is filled with a non existent value")
    void allFieldsAreValidTheCvcCvvFieldIsFilledWithANonExistentValueOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_26"));
        tourPurchasePage.securityCodesNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, the CVC/CVV field is filled with zeros")
    void allFieldsAreValidTheCvcCvvFieldIsFilledWithZerosOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_27"));
        tourPurchasePage.validPopUp();
        assertEquals(DataHelp.validDataOnCredit().getNewRowsDB(), DBaseHelp.countAll());
        assertEquals(DataHelp.validDataOnCredit().getPrice(), DBaseHelp.paymentData().getAmount());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.paymentData().getStatus());
        assertEquals(DataHelp.validDataOnCredit().getStatus(), DBaseHelp.creditData().getStatus());
    }

    @Test
    @DisplayName("Must not successfully pay for the tour on credit. All fields are valid, field CVC/CVV is empty")
    void allFieldsAreValidFieldCvcCvvIsEmptyOnCredit() {
        var tourPurchasePage = new TourPurchasePage();
        tourPurchasePage.getCreditTourButton(DataHelp.generateUser("scenario_28"));
        tourPurchasePage.securityCodesNotCorrect();
        assertEquals(DataHelp.notValidInfo().getNewRowsDB(), DBaseHelp.countAll());
    }
}
