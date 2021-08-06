package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardReplenishCards {
    private SelenideElement sumField = $("[class=input__control]");
    private SelenideElement cardField = $("[type=tel]");
    private SelenideElement replenishButton = $("[class=button__text]");
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardReplenishCards() {
        heading.shouldBe(visible);
    }

    public DashboardYourCards replenish(String sum, DataHelper.CardsInfo cardsInfo, int number) {
        sumField.setValue(sum);
        if (number != 1) {
            cardField.setValue(cardsInfo.getFirst());
        } else {
            cardField.setValue(cardsInfo.getSecond());
        }
        replenishButton.click();
        return new DashboardYourCards();
    }

}
