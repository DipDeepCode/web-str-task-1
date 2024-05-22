package ru.ddc.webstrtask12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.model.ToDoItem;
import ru.ddc.webstrtask12.service.ToDoItemService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todoitems")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;

    @PostMapping
    public ResponseEntity<?> createToDoItem(@RequestBody ToDoItem toDoItem) {
        toDoItem = toDoItemService.save(toDoItem);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/todoitems/%d".formatted(toDoItem.getId())))
                .body(toDoItem);
    }

    @GetMapping
    public ResponseEntity<?> findToDoItems() {
        return ResponseEntity.ok().body(toDoItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findToDoItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(toDoItemService.findById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateToDoItem(@RequestBody ToDoItem toDoItem) {
        return ResponseEntity.ok().body(toDoItemService.update(toDoItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDoItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(toDoItemService.delete(id));
    }
}
