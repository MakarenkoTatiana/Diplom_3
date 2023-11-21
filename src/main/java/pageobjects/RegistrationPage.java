package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class RegistrationPage {
    private WebDriver driver;
    private By nameFiled = By.xpath(".//label[text()='Имя']/following-sibling::input[@class='text input__textfield text_type_main-default']");
    private By emailFiled = By.xpath(".//label[text()='Email']/following-sibling::input[@class='text input__textfield text_type_main-default']");
    private By passwordFiled = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    private By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");
    private By userAlredyExistsMessage = By.xpath(".//p[@class='input__error text_type_main-default']");
    private By wrongPasswordMessage = By.xpath(".//p[@class='input__error text_type_main-default']");
    private By loginAnchor = By.xpath(".//a[@class='Auth_link__1fOlj' and @href='/login']");

    public RegistrationPage() {}

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Создаем пользователя через форму регистрации")
    public void createAccount(String email, String name, String password) {
        driver.findElement(emailFiled).sendKeys(email);
        driver.findElement(nameFiled).sendKeys(name);
        driver.findElement(passwordFiled).sendKeys(password);
        driver.findElement(registrationButton).click();
    }

    @Step("Проверяем, что появилось сообщение <Пользователь уже существует>")
    public boolean userAlreadyExistsMessage() {
        List<WebElement> elements = driver.findElements(userAlredyExistsMessage);
        if(elements.size() == 0) {
            return false;
        }else if(elements.size() > 1) {
            for(WebElement element : elements) {
                if(element.isDisplayed() && element.getText().equals("Такой пользователь уже существует")) {
                    return false;
                }
            }
        }
        return true;
    }

    @Step("Проверка сообщения 'Некорректный пароль'")
    public boolean isVisibleWrongPasswordMessage() {
        WebElement element = driver.findElement(wrongPasswordMessage);
        return element.isDisplayed() && element.getText().equals("Некорректный пароль");
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickByLoginAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginAnchor));
        driver.findElement(loginAnchor).click();
    }
}
