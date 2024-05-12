package org.example.backend.DTO.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;

    private String address;
    private String nic;
    private String name;
    private String mobileNum;
    /**
     * rider Commission percentage.
     */
    private Double commissionRate;

    /**
     * rider Commission price.
     */
    private Double commissionPrice;
}

