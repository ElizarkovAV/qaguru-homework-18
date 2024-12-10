package api;

import models.AddBookListReqModel;
import models.ISBNModel;

import java.util.List;

import static helpers.extensions.LoginExtension.cookies;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQASpecs.requestWithJson;
import static specs.DemoQASpecs.responseLogs;

public class BookStoreAPI {

    public static void deleteBooksFromCart() {
        step("Выполнить удаление всех книг из корзины", () -> {
            given(requestWithJson)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .queryParam("UserId", cookies.getUserId())

                    .when()
                    .delete("/BookStore/v1/Books")

                    .then()
                    .spec(responseLogs)
                    .statusCode(204);
        });
    }

    public static void addBookToList (String isbn) {
        ISBNModel isbnModel = new ISBNModel(isbn);
        AddBookListReqModel request = new AddBookListReqModel(cookies.getUserId(), List.of(isbnModel));

        step("Сделать запрос добавления книги в корзину", () -> {
            given(requestWithJson)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .body(request)
                    .when()
                    .post("/BookStore/v1/Books")
                    .then()
                    .spec(responseLogs)
                    .statusCode(200);
        });
    }

}
