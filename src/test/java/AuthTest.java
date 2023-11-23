import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.*;
import model.Constants;

public class AuthTest extends TestBase {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RestorePasswordPage restorePasswordPage;
    private AccountPage accountPage;

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        mainPage = new MainPage(DRIVER);
        loginPage = new LoginPage(DRIVER);
        registrationPage = new RegistrationPage(DRIVER);
        restorePasswordPage = new RestorePasswordPage(DRIVER);
        accountPage = new AccountPage(DRIVER);
    }

    @Test
    @DisplayName("Логин по кнопке 'Войти в аккаунт'")
    public void shouldLoginByLoginButton() {
        mainPage.clickByLoginButton();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        Assert.assertTrue(mainPage.isCreateOrderVisible());
    }

    @Test
    @DisplayName("Логин по кнопке 'Личный кабинет'")
    public void shouldLoginByloginAnchor() {
        mainPage.clickByLoginAnchor();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        Assert.assertTrue(mainPage.isCreateOrderVisible());
    }

    @Test
    @DisplayName("Логин по кнопке 'Личный кабинет' в форме регистрации")
    public void shouldLoginByRegistrationLoginAnchor() {
        mainPage.clickByLoginAnchor();
        loginPage.clickOnRegistrationLink();
        registrationPage.clickByLoginAnchor();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        Assert.assertTrue(mainPage.isCreateOrderVisible());
    }

    @Test
    @DisplayName("Логин по кнопке 'Личный кабинет' в форме восстановления пароля")
    public void shouldLoginByRestorePasswordLoginAnchor() {
        mainPage.clickByLoginAnchor();
        loginPage.clickByRestorePasswordAnchor();
        restorePasswordPage.clickByLoginAnchor();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        Assert.assertTrue(mainPage.isCreateOrderVisible());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти'")
    public void shouldExitFromProfile() {
        mainPage.clickByLoginAnchor();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        mainPage.clickByLoginAnchor();
        accountPage.clickOnExitButton();
        Assert.assertTrue(loginPage.isLoginDivVisible());
    }
}
