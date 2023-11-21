package pageobjects;


import io.qameta.allure.Step;
import model.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private By loginAnchor = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/account']");
    private By loginButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Войти в аккаунт']");
    private By createOrderButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    private By constructorText = By.xpath(".//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");
    private By bunSpan = By.xpath(String.format(".//span[@class='text text_type_main-default' and text()='%s']", Constants.BUNS));
    private By sauceSpan = By.xpath(String.format(".//span[@class='text text_type_main-default' and text()='%s']", Constants.SOUCE));
    private By fillingSpan= By.xpath(String.format(".//span[@class='text text_type_main-default' and text()='%s']", Constants.FILLING));
    private By selectedSpan = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");


    public MainPage() {}

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на ссылку 'Личный кабинет'")
    public void clickByLoginAnchor() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginAnchor));
        driver.findElement(loginAnchor).click();
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void clickByLoginButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на раздел 'Булки'")
    public void clickByBreadSpan() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(bunSpan));
        driver.findElement(bunSpan).click();
    }

    @Step("Нажатие на раздел 'Соусы'")
    public void clickBySauceSpan() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(bunSpan));
        driver.findElement(sauceSpan).click();
    }

    @Step("Нажатие на раздел 'Начинки'")
    public void clickByFillingSpan() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(bunSpan));
        driver.findElement(fillingSpan).click();
    }

    @Step("Раздел 'Булки'")
    public boolean isBreadSelected() {
        return driver.findElement(selectedSpan).getText().equals(Constants.BUNS);
    }

    @Step("Раздел 'Соусы'")
    public boolean isSouceSelected() {
        return driver.findElement(selectedSpan).getText().equals(Constants.SOUCE);
    }

    @Step("Раздел 'Начинки'")
    public boolean isFillingSelected() {
        return driver.findElement(selectedSpan).getText().equals(Constants.FILLING);
    }

    @Step("Кнопка 'Заказать'")
    public boolean isCreateOrderVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Текст 'Соберите бургер'")
    public boolean isConstructorTextVisible() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(constructorText));
        return driver.findElement(constructorText).isDisplayed();
    }
}
