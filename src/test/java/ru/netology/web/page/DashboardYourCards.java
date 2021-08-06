package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardYourCards {
    private SelenideElement replenishFirstCardButton = $("[class=button__content]");
    private SelenideElement replenishSecondCardButton = $$("[class=button__content]").get(1);
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardYourCards() {
        heading.shouldBe(visible);
    }

    public DashboardReplenishCards replenishFirst() {
        replenishFirstCardButton.click();
        return new DashboardReplenishCards();
    }

    public DashboardReplenishCards replenishSecond() {
        replenishSecondCardButton.click();
        return new DashboardReplenishCards();
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}
