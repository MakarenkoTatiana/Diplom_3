import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobjects.MainPage;

public class ConstructorTest extends TestBase {
    private MainPage mainPage;

    @Before
    public void init() throws JsonProcessingException {
        super.init();
        mainPage = new MainPage(DRIVER);
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void shouldMoveToSouce(){
        mainPage.clickBySauceSpan();
        Assert.assertTrue(mainPage.isSouceSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void shouldMoveToFilling(){
        mainPage.clickByFillingSpan();
        Assert.assertTrue(mainPage.isFillingSelected());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void shouldMoveToBuns(){
        mainPage.clickBySauceSpan();
        mainPage.clickByBreadSpan();
        Assert.assertTrue(mainPage.isBreadSelected());
    }
}
