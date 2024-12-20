package tests;

import api.BooksApi;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.extensions.WithLogin;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("APITests")
@DisplayName("Тесты с использованием API и UI на страницу profile demoqa и book store")
public class DemoQABooksTests extends TestBase {


    ProfilePage profilePage = new ProfilePage();
    BooksApi booksAPI = new BooksApi();
    String isbn = "9781449325862";

    @Test
    @WithLogin
    @DisplayName("Удаление книги со страницы пользователя")
    void deleteBookFromProfileCart() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        booksAPI.deleteAllBooksFromCart();

        booksAPI.addBookToCart(isbn);

        profilePage.openProfilePage();
        profilePage.checkLastProfileAddedBook(isbn);
        profilePage.deleteBookFromCart();

        profilePage.checkThatCartIsEmpty();
        booksAPI.checkResultOnApi();
    }
}
