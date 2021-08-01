package ru.netology.web.page;

import com.codeborne.selenide.*;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public static int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private static int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public static void replenishFirst(String sum) {
        $("[class=button__content]").click();
        $("[class=input__control]").setValue(sum);
        $("[type=tel]").setValue("5559 0000 0000 0002");
        $("[class=button__text]").click();
    }

    public static void replenishSecond(String sum) {
        $$("[class=button__content]").get(1).click();
        $("[class=input__control]").setValue(sum);
        $("[type=tel]").setValue("5559 0000 0000 0001");
        $("[class=button__text]").click();
    }
}
