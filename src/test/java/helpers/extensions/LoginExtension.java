package helpers.extensions;

import api.AuthorizationApi;

import static data.AuthorizationData.*;
import models.login.LoginRespModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        String userName = System.getProperty("loginUser", "login");
        String userPassword = System.getProperty("passwordUser", "password");

        LoginRespModel authResponse = AuthorizationApi.getAuthData(userName, userPassword);

        step("Открыть фавикон страницы, для авторизации", ()-> {
            open("/favicon.ico");
        });

        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));

        User_ID = authResponse.getUserId();
        UserToken = authResponse.getToken();
        Expires = authResponse.getExpires();
        CreateDate = authResponse.getCreatedDate();
        IsActive = authResponse.getIsActive();

    }
}
