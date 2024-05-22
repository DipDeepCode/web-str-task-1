package ru.ddc.webstrtask12.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Workspace {

    private Long id;
    private String name;
    private List<ToDoItem> toDoItems = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "Workspace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
