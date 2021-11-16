package ru.netology.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardDeliveryTest {
    private RegistrationByCardInfo registrationByCardInfo;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        registrationByCardInfo = DataGenerator.Registration.generateByCard("ru");
    }

    @Test
    void shouldSubmitRequest() {

        $("[data-test-id=city] input").setValue(registrationByCardInfo.getCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String plannedDate = DataGenerator.Registration.getDate(10);
        $("[data-test-id=date] input").setValue(plannedDate);
        $("[data-test-id=name] input").setValue(registrationByCardInfo.getName());
        $("[data-test-id=phone] input").setValue(registrationByCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + plannedDate));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String replanDate = DataGenerator.Registration.getDate(12);
        $("[data-test-id=date] input").setValue(replanDate);
        $(byText("Запланировать")).click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $(byText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно запланирована на " + replanDate));
    }
}
