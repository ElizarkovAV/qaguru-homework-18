package helpers.extensions;

import api.AccountAPI;
import models.LoginUserResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    public static LoginUserResponseModel cookies;

    @Override
    public void beforeEach(ExtensionContext context) {
        cookies = AccountAPI.getAuthCookie();

        step("Добавить полученные cookies из ответа к текущему браузеру, для дальнейших дейтсвий", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));

        });

        step("Проверка успешного входа в учетную запись", () -> {
                    open("/profile");
                    $("#userName-value").shouldHave(text(System.getProperty("userLogin")));
                }
        );
    }
}
