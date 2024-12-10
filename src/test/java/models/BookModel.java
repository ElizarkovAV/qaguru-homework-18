package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookModel {
    String isbn, title, subTitle, author, publish_date, publisher;

    int pages;

    String description, website;

}
