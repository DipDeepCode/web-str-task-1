package ru.ddc.webstrtask12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.model.ToDoItem;
import ru.ddc.webstrtask12.repository.ToDoItemRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;

    public ToDoItem save(Long workspaceId, ToDoItem toDoItem) {
        toDoItem.setWorkspaceId(workspaceId);
        return toDoItemRepository.save(toDoItem);
    }

    public List<ToDoItem> findAll(Long workspaceId) {
        return toDoItemRepository.findAllByWorkspaceId(workspaceId);
    }

    public ToDoItem findById(Long id) {
        return toDoItemRepository.findById(id);
    }

    public int update(Long id, ToDoItem toDoItem) {
        toDoItem.setId(id);
        return toDoItemRepository.update(toDoItem);
    }

    public int delete(Long id) {
        return toDoItemRepository.deleteById(id);
    }
}
