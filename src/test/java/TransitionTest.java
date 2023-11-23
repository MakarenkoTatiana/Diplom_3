import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.Constants;
import pageobjects.*;

public class TransitionTest extends TestBase {
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountPage accountPage;

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        mainPage = new MainPage(DRIVER);
        loginPage = new LoginPage(DRIVER);
        accountPage = new AccountPage(DRIVER);
    }

    @Test
    @DisplayName("Переход на страницу логина")
    public void shouldTransitionToLoginPage() {
        mainPage.clickByLoginAnchor();
        Assert.assertTrue(loginPage.isLoginButtonVisible());
    }

    @Test
    @DisplayName("Переход в профиль пользователя")
    public void shouldTransitionToProfilePage() {
        mainPage.clickByLoginAnchor();
        loginPage.login(Constants.EMAIL, Constants.PASSWORD);
        mainPage.clickByLoginAnchor();
        Assert.assertTrue(accountPage.isChangeDataTextVisible());
    }

    @Test
    @DisplayName("Переход по клику на 'Конструктор'")
    public void shouldTransitionToConstructor() {
        mainPage.clickByLoginAnchor();
        loginPage.clickByConstructorAnchor();
        Assert.assertTrue(mainPage.isConstructorTextVisible());
    }

    @Test
    @DisplayName("Переход на главную страницу")
    public void shouldTransitionToMainPage() {
        mainPage.clickByLoginAnchor();
        loginPage.clickByMainAnchor();
        Assert.assertTrue(mainPage.isConstructorTextVisible());
    }
}
