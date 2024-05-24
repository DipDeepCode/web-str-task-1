package ru.ddc.webstrtask12.todoapp.controller.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "should be not empty")
    private String username;
    @NotBlank(message = "should be not empty")
    private String password;
}
