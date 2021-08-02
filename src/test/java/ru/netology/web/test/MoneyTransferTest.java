package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.page.TransferPage;

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
        int firstBalanceBefore = DataHelper.getFirstCardBalance();
        int secondBalanceBefore = DataHelper.getSecondCardBalance();
        int difference = 200;
        TransferPage.replenishFirst(Integer.toString(difference), cardsInfo);
        assertEquals(firstBalanceBefore + difference, DataHelper.getFirstCardBalance());
        assertEquals(secondBalanceBefore - difference, DataHelper.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        var cardsInfo = DataHelper.getCardsInfo();
        int firstBalanceBefore = DataHelper.getFirstCardBalance();
        int secondBalanceBefore = DataHelper.getSecondCardBalance();
        int difference = 200;
        TransferPage.replenishSecond(Integer.toString(difference), cardsInfo);
        assertEquals(firstBalanceBefore - difference, DataHelper.getFirstCardBalance());
        assertEquals(secondBalanceBefore + difference, DataHelper.getSecondCardBalance());
    }
}

