package api;

import io.qameta.allure.Step;
import models.books.AddBooksCollectionRequestModel;
import models.books.AllBooksFromProfileResponseModel;
import models.books.BookModel;
import models.books.ISBNModel;

import java.util.List;

import static data.AuthorizationData.USER_ID;
import static data.AuthorizationData.USER_TOKEN;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.DemoQASpecs.*;

public class BooksAPI {

    @Step("Очистить корзину через API запрос")
    public BooksAPI deleteAllBooksFromCart() {

             given(requestSpec)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .queryParam("UserId", USER_ID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);

             return this;
    }

    @Step("Добавить книгу в корзину пользователя через API")
    public BooksAPI addBookToCart(String isbn) {
        ISBNModel book = new ISBNModel();
        book.setIsbn(isbn);

        AddBooksCollectionRequestModel booksCollection = new AddBooksCollectionRequestModel();
        booksCollection.setUserId(USER_ID);
        booksCollection.setCollectionOfIsbns(List.of(book));

        given(requestSpecification)
                .header("Authorization", "Bearer " + USER_TOKEN)
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
                given(requestSpecification)
                        .when()
                        .header("Authorization", "Bearer " + USER_TOKEN)
                        .get("/Account/v1/User/" + USER_ID)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(AllBooksFromProfileResponseModel.class);

        List<BookModel> listOfBooks = response.getBooks();

        assertTrue(listOfBooks.isEmpty());
    }
}
