package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class AccountPage extends PageObject {

    @FindBy(id = "new_account-button")
    private WebElement newAccountButton;

    public void clickNewAccount() {
        this.newAccountButton.click();
    }

    public boolean isAccountCreated(String money) {
        try {
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String xpath = String.format("//ion-label[text()[contains(.,'RON')] and text()[contains(.,'%s')]]", money);
            getDriver().findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public void clickCreatedAccount(String money) {
        String xpath = String.format("//ion-label[text()[contains(.,'RON')] and text()[contains(.,'%s')]]/..", money);
        WebElement account = getDriver().findElement(By.xpath(xpath));
        account.click();
    }
}
