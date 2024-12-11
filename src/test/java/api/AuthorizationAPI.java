package api;

import io.qameta.allure.Step;
import models.login.LoginReqModel;
import models.login.LoginRespModel;

import static io.restassured.RestAssured.*;
import static specs.DemoQASpecs.requestSpec;
import static specs.DemoQASpecs.responseSpec200;

public class AuthorizationAPI {

    @Step("Получение необходимых данных для авторизации")
    public static LoginRespModel getAuthData (String userName, String userPassword) {
        LoginReqModel request = new LoginReqModel();
        request.setUserName(userName);
        request.setPassword(userPassword);

        LoginRespModel response =
                given()
                        .spec(requestSpec)
                        .body(request)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginRespModel.class);

        return response;
    }
}
