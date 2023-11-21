package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestorePasswordPage {
    private WebDriver driver;
    private By loginAnchor = By.xpath(".//a[@class='Auth_link__1fOlj' and @href='/login']");

    public RestorePasswordPage() {}

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickByLoginAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginAnchor));
        driver.findElement(loginAnchor).click();
    }
}
