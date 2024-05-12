package org.example.backend.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
    private String userCode;
    private String role;

    public JwtResponse(String accessToken, Long id, String username, String email, String role, String userCode) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.userCode = userCode;
    }
}

