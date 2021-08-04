package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardReplenishCards;
import ru.netology.web.page.DashboardYourCards;
import ru.netology.web.page.LoginPageV1;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferTest {
    @BeforeEach
    public void setUpAll() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var cardsInfo = DataHelper.getCardsInfo();
        var replenishThis = new DashboardReplenishCards();
        var yourCards = new DashboardYourCards();
        int firstBalanceBefore = yourCards.getFirstCardBalance();
        int secondBalanceBefore = yourCards.getSecondCardBalance();
        int difference = 200;
        yourCards.replenishFirst();
        replenishThis.replenish(Integer.toString(difference), cardsInfo, 1);
        assertEquals(firstBalanceBefore + difference, yourCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore - difference, yourCards.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var cardsInfo = DataHelper.getCardsInfo();
        var replenishThis = new DashboardReplenishCards();
        var yourCards = new DashboardYourCards();
        int firstBalanceBefore = yourCards.getFirstCardBalance();
        int secondBalanceBefore = yourCards.getSecondCardBalance();
        int difference = 200;
        yourCards.replenishSecond();
        replenishThis.replenish(Integer.toString(difference), cardsInfo, 2);
        assertEquals(firstBalanceBefore - difference, yourCards.getFirstCardBalance());
        assertEquals(secondBalanceBefore + difference, yourCards.getSecondCardBalance());
    }
}

