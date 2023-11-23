package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Auth;
import model.User;

public class UserRequest {
    @Step("Создание пользователя")
    public static Response createUser(String email, String name, String password, RequestSpecification specification, ObjectMapper mapper) throws JsonProcessingException {
        return RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(new User(email, name, password)))
                .post("/auth/register");
    }

    @Step("Авторизация пользователя")
    public static String login(String email, String password, RequestSpecification specification, ObjectMapper mapper) throws JsonProcessingException {
        Auth auth = new Auth(email,  password);
        Response response = RestAssured.given(specification)
                .when()
                .body(mapper.writeValueAsString(auth))
                .post("/auth/login");
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString("accessToken");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String token, RequestSpecification specification) {
        RestAssured.given(specification)
                .header("Authorization", String.format("%s", token))
                .delete("/auth/user");
    }
}
