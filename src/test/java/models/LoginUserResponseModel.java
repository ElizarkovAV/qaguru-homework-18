package models;

import lombok.Data;

@Data
public class LoginUserResponseModel {
    String userId, username, password, token, expires, created_date;
    Boolean isActive;
}
