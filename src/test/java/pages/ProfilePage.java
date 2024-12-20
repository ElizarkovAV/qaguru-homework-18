package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProfilePage {

    @Step("Открыть страницу Profile")
    public void openProfilePage() {
        open("/profile");
    }

    @Step("Проверить последнюю добавленную книгу на странице профиля пользователя")
    public void checkLastProfileAddedBook(String isbn) {
        $$(".rt-tr-group").first()
                .$("a[href='/profile?book=" + isbn + "']")
                .shouldBe(exist);
    }

    @Step("Удалить книгу из корзины пользователя")
    public void deleteBookFromCart() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }

    @Step("Проверка, что в корзине пользователя нет записей")
    public void checkThatCartIsEmpty() {
        assertThat($(".rt-noData").text()).isEqualTo("No rows found");
    }
}
