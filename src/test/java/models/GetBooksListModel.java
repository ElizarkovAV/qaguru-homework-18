package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetBooksListModel {
    String userId, username;
    List<BookModel> books;
}
