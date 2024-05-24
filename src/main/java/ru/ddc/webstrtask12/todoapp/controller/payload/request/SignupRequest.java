package ru.ddc.webstrtask12.todoapp.controller.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 20, message = "the size should be in the range from {min} to {max}")
    private String username;

    @NotBlank(message = "should be not empty")
    @Size(max = 50, message = "the size should be less than {max}")
    @Email(message = "must have the format of an email address")
    private String email;

    @NotBlank(message = "should be not empty")
    @Size(min = 3, max = 120, message = "the size should be in the range from {min} to {max}")
    private String password;
}
