package ru.ddc.webstrtask12.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Workspace {
    private Long id;
    private Long customerId;
    private String name;
    private String description;
    private List<ToDoItem> toDoItems = new ArrayList<>();
    private LocalDateTime createdAt = LocalDateTime.now();
}
