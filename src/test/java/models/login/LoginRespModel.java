package models.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginRespModel {

    private String userId, username, password, token, expires;

    @JsonProperty("created_date")
    private String createdDate;

    private Boolean isActive;
}
