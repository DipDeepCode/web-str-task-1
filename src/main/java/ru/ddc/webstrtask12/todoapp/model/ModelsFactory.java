package ru.ddc.webstrtask12.todoapp.model;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ModelsFactory {

    public ToDoItem getSampleToDoItem(String name,
                                      String description,
                                      LocalDateTime startDateTime,
                                      Duration duration,
                                      String priority) {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setWorkspaceId(1L);
        toDoItem.setName(name);
        toDoItem.setDescription(description);
        toDoItem.setStartDateTime(startDateTime);
        toDoItem.setDuration(duration);
        toDoItem.setPriority(priority);
        return toDoItem;
    }
}
