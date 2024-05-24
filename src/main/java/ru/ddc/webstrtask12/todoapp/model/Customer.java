package ru.ddc.webstrtask12.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Getter
    private Long id;
    @Getter
    private String username;
    @Getter
    private String email;
    @Getter
    private String password;
    private Role role;
    @Getter
    private LocalDateTime createdAt = LocalDateTime.now();
    @Getter
    private Boolean isNonLocked = true;

    public String getRole() {
        return role == null ? null : role.name();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                ", isNonLocked=" + isNonLocked +
                '}';
    }
}
