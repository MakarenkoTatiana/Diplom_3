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
import model.Auth;
import io.restassured.parsing.Parser;
import model.User;

public class TestBase {
    protected static WebDriver driver;
    protected static final String url = "https://stellarburgers.nomoreparties.site";
    private static final String BASE_URL = System.getenv("url") == null ? "https://stellarburgers.nomoreparties.site/" : System.getenv("url");
    private static final String API = System.getenv("api") == null ? "/api" : System.getenv("api");
    private static final Parser PARSER = Parser.fromContentType("application/json;charset=utf-8");
    protected static RequestSpecification specification;
    protected static ObjectMapper mapper;

    @BeforeClass
    public static void setUp() {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setBasePath(API)
                .setContentType(PARSER.getContentType())
                .setAccept(ContentType.JSON);
        specification = builder.build();
        mapper = new ObjectMapper();
    }

    @Before
    public void init() throws JsonProcessingException {
        initDriver();
        createUser(Constants.EMAIL, Constants.NAME, Constants.PASSWORD);
    }

    @After
    public void tearDown() throws JsonProcessingException {
        deleteUser(login(Constants.EMAIL, Constants.PASSWORD));
        driver.quit();
    }

    @Step("Создание пользователя")
    public Response createUser(String email, String name, String password) throws JsonProcessingException {
        return RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(new User(email, name, password)))
                .post("/auth/register");
    }

    @Step("Авторизация пользователя")
    public String login(String email, String password) throws JsonProcessingException {
        Auth auth = new Auth(email,  password);
        Response response = RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(auth))
                .post("/auth/login");
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String token) {
        RestAssured.given(specification)
                .header("Authorization", String.format("%s", token))
                .delete("/auth/user");
    }

    @Step("Инициализация web-драйвера")
    protected void initDriver() {
        String driverType = System.getenv("driverType") == null ? "" : System.getenv("driverType");
        String path = System.getenv("driverPath") == null ? "" : System.getenv("driverType");
        switch (driverType) {
            case "yandex":
                ChromeOptions options = new ChromeOptions();
                options.setBinary(path);
                driver = new ChromeDriver(options);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.get(BASE_URL);
    }
}
