package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.LogoutPage;

public class LogoutSteps {

    private LogoutPage logoutPage;

    @Step
    public void clickLogout() {
        this.logoutPage.clickLogout();
    }

    @Step
    public void logout() {
        this.clickLogout();
    }
}
