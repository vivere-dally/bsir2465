package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.NewAccountPage;

public class NewAccountSteps {

    private NewAccountPage newAccountPage;

    @Step
    public void inputCurrency(String currency) {
        this.newAccountPage.inputCurrency(currency);
    }

    @Step
    public void inputMoney(String money) {
        this.newAccountPage.inputMoney(money);
    }

    @Step
    public void inputMonthlyIncomeInput(String monthlyIncome) {
        this.newAccountPage.inputMonthlyIncomeInput(monthlyIncome);
    }

    @Step
    public void clickCreate() {
        this.newAccountPage.clickCreate();
    }

    @Step
    public void newAccount(String currency, String money, String monthlyIncome) {
        this.inputCurrency(currency);
        this.inputMoney(money);
        this.inputMonthlyIncomeInput(monthlyIncome);
        this.clickCreate();
    }
}
