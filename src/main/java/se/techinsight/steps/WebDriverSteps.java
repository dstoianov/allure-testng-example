package se.techinsight.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Dmitry Baev charlie@yandex-team.ru
 *         Date: 28.10.13
 */
public class WebDriverSteps {

    private WebDriver driver;

    public WebDriverSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void openMainPage() {
        driver.get("http://ya.ru");
    }

    @Step("Search by \"{0}\"")
    public void search(String text) {
        driver.findElement(By.id("text")).sendKeys(text);
        driver.findElement(By.cssSelector(".search2__button")).submit();
    }

    @Step("Make test fail")
    public void makeError() {
        driver.findElement(By.id("error"));
    }

    @Attachment(value = "Screenshot on demand", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}