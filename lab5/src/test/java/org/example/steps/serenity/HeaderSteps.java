package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.HeaderPage;
import org.junit.Assert;

public class HeaderSteps {

    private HeaderPage headerPage;

    @Step
    public void isLoggedIn(boolean expected) {
        Assert.assertEquals(expected, headerPage.isAccountsTitleVisible());
    }

    @Step
    public void isLoggedOut(boolean expected) {
        Assert.assertEquals(expected, this.headerPage.isAuthenticationTitleVisible());
    }
}
