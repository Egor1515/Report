import com.beust.ah.A;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardDeliveryTest {


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");

    }

    @Test
    void shouldSendFormV1() {
        Configuration.holdBrowserOpen = true;
        var daysToAddForFirstMeeting = 4;
        var daysToAddForSecondMeeting = 5;
        open("http://localhost:7777/");
        $("[data-test-id ='city'] input").val(DataGenerator.Registration.generateInfo("ru").getCity());
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.COMMAND, "A"));
        $("[data-test-id ='date'] input").sendKeys(BACK_SPACE);
        $("[data-test-id ='date'] input").val(DataGenerator.Registration.generateDate(daysToAddForFirstMeeting));
        $("[data-test-id ='name'] input").val(DataGenerator.Registration.generateInfo("ru").getName());
        $("[data-test-id ='phone'] input").val(DataGenerator.Registration.generateInfo("ru").getPhone());
        $(".checkbox__box").click();
        $(".button[role='button']").click();
        $("[data-test-id='success-notification']").should(Condition.visible);
        $("[data-test-id ='date'] input").sendKeys(Keys.chord(Keys.COMMAND, "A"));
        $("[data-test-id ='date'] input").sendKeys(BACK_SPACE);
        $("[data-test-id ='date'] input").val(DataGenerator.Registration.generateDate(daysToAddForSecondMeeting));
        $(".button[role='button']").click();
        $("[data-test-id='replan-notification']").click();


    }

}