package com.api.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    @Size(min = 2,message = "Should be 2 letter")
    private String name;
    @Email(message = "Email is not  proper Format")

    private String email;
    @Size(min =2,max = 10,message = "Should be 10 digits")
    private String mobile;
    private String message;
}