package ru.netology.web.page;

import lombok.Value;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    public static DashboardPage replenishFirst(String sum, DataHelper.CardsInfo info) {
        $("[class=button__content]").click();
        $("[class=input__control]").setValue(sum);
        $("[type=tel]").setValue(info.getSecond());
        $("[class=button__text]").click();
        return new DashboardPage();
    }

    public static DashboardPage replenishSecond(String sum, DataHelper.CardsInfo info) {
        $$("[class=button__content]").get(1).click();
        $("[class=input__control]").setValue(sum);
        $("[type=tel]").setValue(info.getFirst());
        $("[class=button__text]").click();
        return new DashboardPage();
    }

}
