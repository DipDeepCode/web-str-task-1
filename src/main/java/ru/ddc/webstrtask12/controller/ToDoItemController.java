package ru.ddc.webstrtask12.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.model.ToDoItem;
import ru.ddc.webstrtask12.payload.request.CreateToDoItemRequest;
import ru.ddc.webstrtask12.payload.request.UpdateToDoItemRequest;
import ru.ddc.webstrtask12.service.ToDoItemService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;
    private final ModelMapper modelMapper;

    @PostMapping("/workspaces/{workspaceId}/todoitems")
    public ResponseEntity<?> createToDoItem(@PathVariable("workspaceId") Long workspaceId,
                                            @RequestBody CreateToDoItemRequest request) {
        ToDoItem toDoItem = toDoItemService.save(workspaceId, modelMapper.map(request, ToDoItem.class));
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/todoitems/%d".formatted(toDoItem.getId())))
                .body(toDoItem);
    }

    @GetMapping("/workspaces/{workspaceId}/todoitems")
    public ResponseEntity<?> findToDoItemsByWorkspaceId(@PathVariable Long workspaceId) {
        return ResponseEntity.ok().body(toDoItemService.findAll(workspaceId));
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity<?> findToDoItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(toDoItemService.findById(id));
    }

    @PutMapping("/todoitems/{id}")
    public ResponseEntity<?> updateToDoItem(@PathVariable("id") Long id,
                                            @RequestBody UpdateToDoItemRequest request) {
        return ResponseEntity.ok().body(toDoItemService.update(id, modelMapper.map(request, ToDoItem.class)));
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity<?> deleteToDoItem(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(toDoItemService.delete(id));
    }
}
