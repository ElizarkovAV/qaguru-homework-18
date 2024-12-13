package tests;

import api.BooksAPI;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.extensions.WithLogin;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@Tag("APITests")
public class DemoQABooksTests extends TestBase {


    ProfilePage profilePage = new ProfilePage();
    BooksAPI booksAPI = new BooksAPI();
    String isbn = "9781449325862";

    @Test
    @WithLogin
    @DisplayName("Удаление книги со страницы пользователя")
    void deleteBookFromProfileCart() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        //arrange
        booksAPI.deleteAllBooksFromCart(); //удалить все книги из корзины пользователя API

        //act
        booksAPI.addBookToCart(isbn); //добавить книгу через API

        profilePage.openProfilePage(); //открыть страницу Profile
        profilePage.checkLastProfileAddedBook(isbn); // проверить, что в UI отображается добавленная книга по isbn
        profilePage.deleteBookFromCart(); //удалить книгу из корзины

        //assert
        profilePage.checkThatCartIsEmpty(); // проверить что корзина пустая UI
        booksAPI.checkResultOnApi(); // проверить что корзина пустая API
    }
}
