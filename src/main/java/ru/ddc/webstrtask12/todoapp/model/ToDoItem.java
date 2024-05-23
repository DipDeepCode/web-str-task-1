package ru.ddc.webstrtask12.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToDoItem {
    private Long id;
    private Long workspaceId;
    private String name;
    private String description;
    private Boolean isDone = false;
    private LocalDateTime startDateTime;
    private Duration duration;
    private String priority;
    private Boolean isPostponed = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "ToDoItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                ", startDateTime=" + startDateTime +
                ", duration=" + duration +
                ", priority=" + priority +
                ", isPostponed=" + isPostponed +
                ", createdAt=" + createdAt +
                '}';
    }
}
