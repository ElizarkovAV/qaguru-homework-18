package api;

import models.GetBooksListModel;
import models.LoginReqUserModel;
import models.LoginUserResponseModel;
import specs.DemoQASpecs;

import static helpers.extensions.LoginExtension.cookies;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQASpecs.*;

public class AccountAPI {

    public static LoginUserResponseModel getAuthCookie() {

        LoginReqUserModel requestUser= new LoginReqUserModel(System.getProperty("userLogin"),
                System.getProperty("userPass"));

        LoginUserResponseModel response = step("Выполнить запрос логина, записать ответ", () ->
                given(requestWithJson)
                        .body(requestUser)
                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(responseLogs)
                        .statusCode(200)
                        .extract().as(LoginUserResponseModel.class));

        return response;
    }

    public static GetBooksListModel getLisOfBooks() {
        GetBooksListModel response = step("Выполнить запрос к списку книг в корзине, " +
                "сохранить результат в переменную", () ->
                    given(requestCommon)
                            .header("Authorization", "Bearer " + cookies.getToken())
                            .queryParam("UserId", cookies.getUserId())
                            .when()
                            .get("/Account/v1/User/" + cookies.getUserId())
                            .then()
                            .spec(responseLogs)
                            .statusCode(200)
                            .extract().as(GetBooksListModel.class));
                return response;
    }
}
