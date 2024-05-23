package ru.ddc.webstrtask12.todoapp.controller.todoitem;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.todoapp.dto.ToDoItemDto;
import ru.ddc.webstrtask12.todoapp.model.ToDoItem;
import ru.ddc.webstrtask12.todoapp.payload.request.CreateToDoItemRequest;
import ru.ddc.webstrtask12.todoapp.payload.request.UpdateToDoItemRequest;
import ru.ddc.webstrtask12.todoapp.service.ToDoItemService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ToDoItemController {
    private final ToDoItemService toDoItemService;
    private final ModelMapper modelMapper;
    private final ToDoItemDtoModelAssembler assembler;

    @PostMapping("/workspaces/{workspaceId}/todoitems")
    public ResponseEntity<?> createToDoItem(@PathVariable("workspaceId") Long workspaceId,
                                            @RequestBody CreateToDoItemRequest request) {
        ToDoItem toDoItem = toDoItemService.save(workspaceId, modelMapper.map(request, ToDoItem.class));
        ToDoItemDto toDoItemDto = modelMapper.map(toDoItem, ToDoItemDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(toDoItemDto));
    }

    @GetMapping("/workspaces/{workspaceId}/todoitems")
    public ResponseEntity<?> findToDoItemsByWorkspaceId(@PathVariable Long workspaceId) {
        List<ToDoItem> toDoItems = toDoItemService.findAll(workspaceId);
        List<ToDoItemDto> toDoItemDtos = toDoItems.stream()
                .map(toDoItem -> modelMapper.map(toDoItem, ToDoItemDto.class))
                .toList();
        return ResponseEntity.ok().body(assembler.toCollectionModel(toDoItemDtos));
    }

    @GetMapping("/todoitems/{id}")
    public ResponseEntity<?> findToDoItem(@PathVariable("id") Long id) {
        ToDoItem toDoItem = toDoItemService.findById(id);
        ToDoItemDto toDoItemDto = modelMapper.map(toDoItem, ToDoItemDto.class);
        return ResponseEntity.ok().body(assembler.toModel(toDoItemDto));
    }

    @PutMapping("/todoitems/{id}")
    public ResponseEntity<?> updateToDoItem(@PathVariable("id") Long id,
                                            @RequestBody UpdateToDoItemRequest request) {
        ToDoItem toDoItem = toDoItemService.update(id, modelMapper.map(request, ToDoItem.class));
        ToDoItemDto toDoItemDto = modelMapper.map(toDoItem, ToDoItemDto.class);
        return ResponseEntity.ok().body(assembler.toModel(toDoItemDto));
    }

    @DeleteMapping("/todoitems/{id}")
    public ResponseEntity<?> deleteToDoItem(@PathVariable("id") Long id) {
        toDoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
