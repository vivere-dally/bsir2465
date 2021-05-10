package org.example.features.search;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.example.steps.serenity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

@RunWith(SerenityRunner.class)
public class NewAccountTest {

    private final Random random = new Random();

    @Managed
    private WebDriver webDriver;

    @Steps
    private SignupSteps signupSteps;

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

//    @Steps
//    private TransactionSteps transactionSteps;
//
//    @Steps
//    private NewTransactionSteps newTransactionSteps;

    @Test
    public void newAccountTest() {

        // Signup
        String username = getRandomUsername();
        String password = username + "A1$";
        this.loginSteps.openPage();
        this.loginSteps.clickSignup();
        this.signupSteps.signup(username, password);
        this.headerSteps.isLoggedOut(true);

        // Login
        this.loginSteps.login(username, password);
        this.headerSteps.isLoggedIn(true);
        this.headerSteps.isLoggedOut(false);

        // New account
        this.accountSteps.clickNewAccount();

        String money = this.getRandomNumber();
        String monthlyIncome = this.getRandomNumber();
        this.newAccountSteps.newAccount(money, monthlyIncome);

        // Check if account is created
        this.accountSteps.isAccountCreated(money, true);

//        this.accountSteps.clickCreatedAccount(money);
//
//        // New transaction
//        this.transactionSteps.clickNewTransaction();
//
//        String message = "automated transaction";
//        String value = this.getRandomNumber();
//        this.newTransactionSteps.newTransaction(message, value);
//
//        // Check if transaction is created
//        this.transactionSteps.scrollPage(50);
//        this.transactionSteps.isTransactionCreated(value, true);
//        this.transactionSteps.goBack();

        // Logout
        this.logoutSteps.logout();
        this.headerSteps.isLoggedIn(false);
        this.headerSteps.isLoggedOut(true);
    }

    private String getRandomUsername() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    private String getRandomNumber() {
        int fractional1 = this.random.nextInt(10);
        int fractional2 = this.random.nextInt(10);

        int integer = 1 + this.random.nextInt(Integer.MAX_VALUE / 2);

        return String.format("%d.%d%d", integer, fractional1, fractional2);
    }

    private String getRandomString() {
        byte[] array = new byte[7];
        this.random.nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
