package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBookListReqModel {
    String userId;
    List<ISBNModel> collectionOfIsbns;
}
