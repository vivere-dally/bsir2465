package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;

public class NewAccountPage extends PageObject {

    @FindBy(xpath = "//ion-select[@id='currency-select']/input")
    private WebElement currencySelectInput;

    @FindBy(xpath = "//ion-input[@id='money-input']/input")
    private WebElement moneyInput;

    @FindBy(xpath = "//ion-input[@id='monthly_income-input']/input")
    private WebElement monthlyIncomeInput;

    @FindBy(id = "create-submit")
    private WebElement createButton;

    public void inputCurrency(String currency) {
        this.currencySelectInput.sendKeys(currency);
    }

    public void inputMoney(String money) {
        this.moneyInput.sendKeys(money);
    }

    public void inputMonthlyIncomeInput(String monthlyIncome) {
        this.monthlyIncomeInput.sendKeys(monthlyIncome);
    }

    public void clickCreate() {
        this.createButton.click();
    }
}
