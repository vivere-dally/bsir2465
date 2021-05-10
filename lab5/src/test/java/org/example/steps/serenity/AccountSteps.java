package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.AccountPage;
import org.junit.Assert;

public class AccountSteps {

    private AccountPage accountPage;

    @Step
    public void clickNewAccount() {
        this.accountPage.clickNewAccount();
    }

    @Step
    public void isAccountCreated(String money, boolean expected) {
        Assert.assertEquals(expected, this.accountPage.isAccountCreated(money));
    }

    @Step
    public void clickCreatedAccount(String money) {
        this.accountPage.clickCreatedAccount(money);
    }
}
