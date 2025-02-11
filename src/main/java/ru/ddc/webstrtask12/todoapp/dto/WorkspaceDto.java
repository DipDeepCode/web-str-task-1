package ru.ddc.webstrtask12.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDto {
    private Long id;
    private Long customerId;
    private String name;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();
}
