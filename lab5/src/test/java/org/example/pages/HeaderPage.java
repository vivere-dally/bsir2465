package org.example.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

public class HeaderPage extends PageObject {

    public boolean isAuthenticationTitleVisible() {
        try {
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            getDriver().findElement(By.id("authentication-title"));
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public boolean isAccountsTitleVisible() {
        try {
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            getDriver().findElement(By.id("accounts-title"));
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }
}
