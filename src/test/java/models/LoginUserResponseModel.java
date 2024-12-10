package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserResponseModel {
    String userId, username, password, token, expires, created_date;
    Boolean isActive;
}
