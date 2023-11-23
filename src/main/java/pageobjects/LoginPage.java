package pageobjects;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private By registrationLink = By.xpath(".//a[@class='Auth_link__1fOlj' and @href='/register']");
    private By emailInput = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");
    private By passwordInput = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @type='password']");
    private By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Войти']");
    private By restorePasswordAnchor = By.xpath(".//a[@class='Auth_link__1fOlj' and @href='/forgot-password']");
    private By constructorAnchor = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/']");
    private By mainAnchor = By.xpath(".//a[@href='/']");
    private By loginDiv = By.xpath(".//div[@class='Auth_login__3hAey']");

    public LoginPage() {}

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку 'Зарегистрироваться'")
    public void clickOnRegistrationLink() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(registrationLink));
        driver.findElement(registrationLink).click();
    }

    @Step("Логин")
    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку 'Восстановить пароль'")
    public void clickByRestorePasswordAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(restorePasswordAnchor));
        driver.findElement(restorePasswordAnchor).click();
    }

    @Step("Нажатие на кнопку 'Конструктор'")
    public void clickByConstructorAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(constructorAnchor));
        driver.findElement(constructorAnchor).click();
    }

    @Step("Нажатие на логотип 'Stellar Burgers'")
    public void clickByMainAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(mainAnchor));
        driver.findElement(mainAnchor).click();
    }

    @Step("Проверка кнопки 'Войти'")
    public boolean isLoginButtonVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Проверка формы логина")
    public boolean isLoginDivVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginDiv));
        return driver.findElement(loginDiv).isDisplayed();
    }
}