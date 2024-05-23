package ru.ddc.webstrtask12.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItemDto {
    private Long id;
    private Long workspaceId;
    private String name;
    private String description;
    private Boolean isDone = false;
    private LocalDateTime startDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Duration duration;
    private String priority;
    private Boolean isPostponed = false;
    private LocalDateTime createdAt = LocalDateTime.now();
}
