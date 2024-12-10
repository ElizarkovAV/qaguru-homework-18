package models;

import lombok.Data;

import java.util.List;

@Data
public class GetBooksListModel {
    String userId, username;
    List<BookModel> books;
}
