package com.example.test.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class LoginResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private String email;
    private Boolean status;
    private List<String> listRole;
    public LoginResponse(String accessToken, String username, String email, Boolean status, List<String> listRole) {
        this.accessToken = accessToken;
        this.username = username;
        this.email = email;
        this.status = status;
        this.listRole = listRole;
    }


}
