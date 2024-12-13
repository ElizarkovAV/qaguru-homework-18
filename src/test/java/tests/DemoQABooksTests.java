package tests;

import api.AuthorizationAPI;
import api.BooksAPI;
import helpers.extensions.WithLogin;
import models.books.AllBooksFromProfileResponseModel;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.open;

public class DemoQABooksTests extends TestBase {


    ProfilePage profilePage = new ProfilePage();
    BooksAPI booksAPI = new BooksAPI();
    String isbn = "9781449325862";

    @Test
    @WithLogin
    void deleteBookFromProfileCart() {
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
