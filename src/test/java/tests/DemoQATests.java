package tests;

import api.AccountAPI;
import api.BookStoreAPI;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.extensions.WithLogin;
import io.qameta.allure.selenide.AllureSelenide;
import models.GetBooksListModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("APITests")
@DisplayName("Тесты на книжный магазин DemoQA")
public class DemoQATests extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Тест на удаление товара из списка")
    void successfulDeleteBookFromBooksList() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Предварительно очистить корзину от книг", () ->
                BookStoreAPI.deleteBooksFromCart());

        step("Добавить книгу в корзину", () ->
                BookStoreAPI.addBookToList("9781449325862"));

        step("Удалить добавленную книгу", () -> {
                ProfilePage.openProfilePage();
                ProfilePage.deleteItem();
            });

        step("Проверить, что книга действительно удалилась", () -> {
            ProfilePage.openProfilePage();
            ProfilePage.checkDeleteBookWithUI();
        });

        step("Получить список книг, которые находятся в корзине, через API", () -> {
            GetBooksListModel response = AccountAPI.getLisOfBooks();
            assertThat(response.getBooks()).isNotNull();
        });

        step("Проверить удаление книги через API", () -> {
            GetBooksListModel response = AccountAPI.getLisOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });

    }
}
