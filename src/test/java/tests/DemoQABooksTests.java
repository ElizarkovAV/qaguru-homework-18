package tests;

import api.BooksAPI;
import helpers.extensions.WithLogin;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

public class DemoQABooksTests extends TestBase {


    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    void deleteBookFromProfileCart() {
        BooksAPI booksAPI = new BooksAPI();
        booksAPI.deleteAllBooksFromCart();
        System.out.println("test");
    }
}
