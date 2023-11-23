import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.Constants;
import pageobjects.*;

public class RegistrationTest extends TestBase {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @Before
    public void init() {
        initDriver();
        mainPage = new MainPage(DRIVER);
        loginPage = new LoginPage(DRIVER);
        registrationPage = new RegistrationPage(DRIVER);
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void shouldCreateAccount() {
        mainPage.clickByLoginAnchor();
        loginPage.clickOnRegistrationLink();
        registrationPage.createAccount(Constants.EMAIL, Constants.NAME, Constants.PASSWORD);
        Assert.assertFalse(registrationPage.userAlreadyExistsMessage());
    }

    @Test
    @DisplayName("Получение ошибки для некорректного пароля")
    public void shouldReturnWrongPasswordMessage() {
        mainPage.clickByLoginAnchor();
        loginPage.clickOnRegistrationLink();
        registrationPage.createAccount(Constants.EMAIL, Constants.NAME, Constants.PASSWORD.substring(0, 2));
        Assert.assertTrue(registrationPage.isVisibleWrongPasswordMessage());
    }
}
