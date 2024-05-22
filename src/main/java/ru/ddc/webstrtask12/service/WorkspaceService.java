package ru.ddc.webstrtask12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.model.ToDoItem;
import ru.ddc.webstrtask12.model.Workspace;
import ru.ddc.webstrtask12.repository.ToDoItemRepository;
import ru.ddc.webstrtask12.repository.WorkspaceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final ToDoItemRepository toDoItemRepository;

    public Workspace save(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    public List<Workspace> findAll() {
        return workspaceRepository.findAll();
    }

    public Workspace findById(Long id) {
        Workspace workspace = workspaceRepository.findById(id);
        workspace.setToDoItems(toDoItemRepository.findAllByWorkspaceId(id));
        return workspace;
    }

    public int update(Long id, Workspace workspace) {
        workspace.setId(id);
        return workspaceRepository.update(workspace);
    }

    public int delete(Long id) {
        return workspaceRepository.deleteById(id);
    }
}
