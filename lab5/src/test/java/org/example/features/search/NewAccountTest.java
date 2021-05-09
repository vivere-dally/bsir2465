package org.example.features.search;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Random;

@RunWith(SerenityRunner.class)
public class NewAccountTest {

    private final Random random = new Random();

    @Managed
    private WebDriver webDriver;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private LogoutSteps logoutSteps;

    @Steps
    private HeaderSteps headerSteps;

    @Steps
    private AccountSteps accountSteps;

    @Steps
    private NewAccountSteps newAccountSteps;

    @Test
    public void newAccountTest() {

        // Login
        this.loginSteps.login("vvssadmin", "VVSSAdmin@123");
        this.headerSteps.isLoggedIn(true);
        this.headerSteps.isLoggedOut(false);

        // New account
        this.accountSteps.clickNewAccount();

        String money = this.getRandomNumber();
        String monthlyIncome = this.getRandomNumber();
        this.newAccountSteps.newAccount(money, monthlyIncome);

        // Check if account is created
        this.accountSteps.isAccountCreated(money, true);

        // Logout
        this.logoutSteps.logout();
        this.headerSteps.isLoggedIn(false);
        this.headerSteps.isLoggedOut(true);
    }

    private String getRandomNumber() {
        int fractional1 = this.random.nextInt(10);
        int fractional2 = this.random.nextInt(10);

        int integer = 1 + this.random.nextInt(Integer.MAX_VALUE / 2);

        return String.format("%d.%d%d", integer, fractional1, fractional2);
    }
}
