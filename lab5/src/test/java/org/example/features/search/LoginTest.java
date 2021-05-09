package org.example.features.search;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.HeaderSteps;
import org.example.steps.serenity.LoginSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/loginData.csv")
public class LoginTest {

    @Managed
    private WebDriver webDriver;

    @Steps
    private LoginSteps loginSteps;

    @Steps
    private HeaderSteps headerSteps;

    private String username, password;
    private Boolean valid;

    @Test
    public void loginTest() {
        this.loginSteps.login(this.username, this.password);
        this.headerSteps.isLoggedIn(this.valid);
    }
}
