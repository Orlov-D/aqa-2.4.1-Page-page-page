package ru.netology.web.data;

import com.codeborne.selenide.ElementsCollection;
import lombok.Value;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class DataHelper {
    private static ElementsCollection cards = $$(".list__item");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    private DataHelper() {
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

    @Value
    public static class CardsInfo {
        private String first;
        private String second;
    }

    public static CardsInfo getCardsInfo() {
        return new CardsInfo("5559 0000 0000 0001", "5559 0000 0000 0002");
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
