package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    @Step("Открыть страницу Profile")
    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }
}
