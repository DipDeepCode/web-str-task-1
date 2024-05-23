package ru.ddc.webstrtask12.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ddc.webstrtask12.todoapp.model.ModelsFactory;
import ru.ddc.webstrtask12.todoapp.model.ToDoItem;
import ru.ddc.webstrtask12.todoapp.repository.ToDoItemRepository;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
class ToDoItemCrudRepositoryTest {

    @Autowired
    private ToDoItemRepository repository;

    @Autowired
    private ModelsFactory modelsFactory;

    @Test
    public void givenJdbcClient() {
        ToDoItem toDoItem = modelsFactory.getSampleToDoItem("name", "description", LocalDateTime.now(),
                Duration.ofHours(1L), "USUAL");
        ToDoItem save = repository.save(toDoItem);
        System.out.println(save);
    }
}