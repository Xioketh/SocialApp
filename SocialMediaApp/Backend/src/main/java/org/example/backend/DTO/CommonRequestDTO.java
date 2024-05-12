package org.example.backend.DTO;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommonRequestDTO {
    private String userCode;
    private String role;

    private String mobileNumOne;

    int pageNo;
    int pageSize;

    //request for get users
    String userName;
    String email;
    int isActive;
    List<String> roles;
}
