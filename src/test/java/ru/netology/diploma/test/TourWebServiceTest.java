package ru.netology.diploma.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

public class TourWebServiceTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Must successfully buy the tour, card expires today")
    void mustSuccessfullyBuyTourCurrentTime() {

    }

    @Test
    @DisplayName("Must successfully buy the tour, card expires in five years")
    void mustSuccessfullyBuyTourFiveYears() {

    }

}
