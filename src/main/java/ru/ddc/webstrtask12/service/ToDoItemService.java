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

    public ToDoItem save(ToDoItem toDoItem) {
        return toDoItemRepository.save(toDoItem);
    }

    public List<ToDoItem> findAll() {
        return toDoItemRepository.findAll();
    }

    public ToDoItem findById(Long id) {
        return toDoItemRepository.findById(id);
    }

    public int update(ToDoItem toDoItem) {
        return toDoItemRepository.update(toDoItem);
    }

    public int delete(Long id) {
        return toDoItemRepository.deleteById(id);
    }
}
