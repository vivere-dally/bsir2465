package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumUtils {
    public static WebElement getShadowRootElement(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (WebElement) executor.executeScript("return arguments[0].shadowRoot", element);
    }

    public void a() {
        ChromeOptions op = new ChromeOptions();
    }
}
