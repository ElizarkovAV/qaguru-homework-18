package api;

import data.AuthorizationData;
import helpers.extensions.LoginExtension;
import io.qameta.allure.Step;
import models.books.AddBooksCollectionRequestModel;
import models.books.AllBooksFromProfileResponseModel;
import models.books.BookModel;
import models.books.ISBNModel;

import java.util.List;

import static data.AuthorizationData.UserToken;
import static data.AuthorizationData.User_ID;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.DemoQASpecs.*;

public class BooksApi {

    @Step("Очистить корзину через API запрос")
    public BooksApi deleteAllBooksFromCart() {

             given(requestSpec)
                .header("Authorization", "Bearer " + UserToken)
                .queryParam("UserId", User_ID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);

             return this;
    }

    @Step("Добавить книгу в корзину пользователя через API")
    public BooksApi addBookToCart(String isbn) {
        ISBNModel book = new ISBNModel();
        book.setIsbn(isbn);

        AddBooksCollectionRequestModel booksCollection = new AddBooksCollectionRequestModel();
        booksCollection.setUserId(User_ID);
        booksCollection.setCollectionOfIsbns(List.of(book));

        given(requestSpec)
                .header("Authorization", "Bearer " + UserToken)
                .body(booksCollection)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec201);

        return this;
    }

    @Step("Проверить, что в корзине пусто (API).")
    public void checkResultOnApi() {

        AllBooksFromProfileResponseModel response =
                given(requestSpec)
                        .when()
                        .header("Authorization", "Bearer " + UserToken)
                        .get("/Account/v1/User/" + User_ID)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(AllBooksFromProfileResponseModel.class);

        List<BookModel> listOfBooks = response.getBooks();

        assertTrue(listOfBooks.isEmpty());
    }
}
