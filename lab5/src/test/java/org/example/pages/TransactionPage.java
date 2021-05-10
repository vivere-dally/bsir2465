package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.example.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class TransactionPage extends PageObject {

    @FindBy(id = "new_transaction-button")
    private WebElement newTransactionButton;

    @FindBy(id = "account_feed_page-ion-content")
    private WebElement ionContent;

    @FindBy(xpath = "//ion-back-button[@text='Accounts']")
    private WebElement backButton;

    public void clickNewTransaction() {
        this.newTransactionButton.click();
    }

    public void verticalScroll(int x) {
        getDriver().manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        getDriver().findElement(By.id("account_feed_page-ion-content"));

        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
//        String script = String.format("(await arguments[0].getScrollElement()).scrollBy(0, %d);window.setTimeout(arguments[arguments.length - 1], 500);", x);
//        String script = "(await arguments[0].getScrollElement()).scrollBy(0, 50);arguments[arguments.length - 1];";
        String script = "(await document.querySelector('#account_feed_page-ion-content').getScrollElement()).scrollBy(0, 50);window.setTimeout(arguments[arguments.length - 1], 500);";
//        executor.executeAsyncScript(script, ionContent);
        executor.executeAsyncScript(script);
    }

    public boolean isTransactionCreated(String value) {
        try {
            getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            String xpath = String.format("//ion-label/h3/span[text()[contains(.,'RON')] and text()[contains(.,'%s')]]", value);
            getDriver().findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public void goBack() {
        WebElement definitelyABackButton = SeleniumUtils.getShadowRootElement(getDriver(), backButton);
        definitelyABackButton.click();
    }
}
