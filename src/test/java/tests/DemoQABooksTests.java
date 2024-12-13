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

        profilePage.openProfilePage();
        profilePage.checkLastProfileAddedBook(isbn);
        profilePage.deleteBookFromCart();
        profilePage.checkThatCartIsEmpty();

        System.out.println("123");


    }
}
