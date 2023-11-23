import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import model.Constants;
import utils.UserRequest;
import model.Auth;
import io.restassured.parsing.Parser;
import model.User;

import static utils.UserRequest.*;

public class TestBase {
    protected static WebDriver DRIVER;
    private static final String BASE_URL = System.getenv("url") == null ? "https://stellarburgers.nomoreparties.site/" : System.getenv("url");
    private static final String API = System.getenv("api") == null ? "/api" : System.getenv("api");
    private static final Parser PARSER = Parser.fromContentType("application/json;charset=utf-8");
    protected static RequestSpecification SPECIFICATION;
    protected static ObjectMapper MAPPER;

    @BeforeClass
    public static void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(API)
                .setContentType(PARSER.getContentType())
                .setAccept(ContentType.JSON);
        SPECIFICATION = builder.build();
        MAPPER = new ObjectMapper();
    }

    @Before
    public void init() throws JsonProcessingException {
        initDriver();
        createUser(Constants.EMAIL, Constants.NAME, Constants.PASSWORD, SPECIFICATION, MAPPER);
    }

    @After
    public void tearDown() throws JsonProcessingException {
        deleteUser(login(Constants.EMAIL, Constants.PASSWORD, SPECIFICATION, MAPPER), SPECIFICATION);
        DRIVER.quit();
    }
    @Step("Инициализация web-драйвера")
    protected void initDriver() {
        String driverType = System.getenv("driverType") == null ? "" : System.getenv("driverType");
        String path = System.getenv("driverPath") == null ? "" : System.getenv("driverType");
        switch (driverType) {
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                options.setBinary(path);
                DRIVER = new ChromeDriver(options);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                DRIVER = new ChromeDriver();
        }
        DRIVER.get(BASE_URL);
    }
}
