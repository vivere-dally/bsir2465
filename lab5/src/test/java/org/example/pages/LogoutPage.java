package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class LogoutPage extends PageObject {

    @FindBy(id = "logout-button")
    private WebElement logoutButton;

    public void clickLogout() {
        this.logoutButton.click();
    }
}
