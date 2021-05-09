package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class AccountPage extends PageObject {

    @FindBy(id = "new_account-button")
    private WebElement newAccountButton;

    public void clickNewAccount() {
        this.newAccountButton.click();
    }
}
