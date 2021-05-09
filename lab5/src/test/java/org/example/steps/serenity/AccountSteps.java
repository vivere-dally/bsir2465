package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.AccountPage;

public class AccountSteps {

    private AccountPage accountPage;

    @Step
    public void clickNewAccount() {
        this.accountPage.clickNewAccount();
    }
}
