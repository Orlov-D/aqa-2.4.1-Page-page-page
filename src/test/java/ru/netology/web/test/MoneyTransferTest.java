package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
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
        int firstBalanceBefore = DashboardPage.getFirstCardBalance();
        int secondBalanceBefore = DashboardPage.getSecondCardBalance();
        int difference = 200;
        DashboardPage.replenishFirst(Integer.toString(difference));
        assertEquals(firstBalanceBefore + difference, DashboardPage.getFirstCardBalance());
        assertEquals(secondBalanceBefore - difference, DashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        int firstBalanceBefore = DashboardPage.getFirstCardBalance();
        int secondBalanceBefore = DashboardPage.getSecondCardBalance();
        int difference = 200;
        DashboardPage.replenishSecond(Integer.toString(difference));
        assertEquals(firstBalanceBefore - difference, DashboardPage.getFirstCardBalance());
        assertEquals(secondBalanceBefore + difference, DashboardPage.getSecondCardBalance());
    }





}

