package com.agnibank.agniBankAuth.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    @NotBlank(message = "Username cannot be blank")
    public String name;
    @NotBlank(message = "email should not be blank")
    @Email(message = "Invalid email format")
    public String email;

}
