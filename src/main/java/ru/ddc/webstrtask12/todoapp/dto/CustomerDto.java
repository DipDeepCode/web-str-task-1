package ru.ddc.webstrtask12.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ddc.webstrtask12.todoapp.model.Role;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isNonLocked = true;
}
