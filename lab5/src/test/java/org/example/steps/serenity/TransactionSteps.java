package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.TransactionPage;
import org.junit.Assert;

public class TransactionSteps {

    private TransactionPage transactionPage;

    @Step
    public void clickNewTransaction() {
        this.transactionPage.clickNewTransaction();
    }

    @Step
    public void scrollPage(int x) {
        this.transactionPage.verticalScroll(x);
    }

    @Step
    public void isTransactionCreated(String value, boolean expected) {
        Assert.assertEquals(expected, this.transactionPage.isTransactionCreated(value));
    }

    @Step
    public void goBack() {
        this.transactionPage.goBack();
    }
}
