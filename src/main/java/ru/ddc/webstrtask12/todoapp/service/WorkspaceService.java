package ru.ddc.webstrtask12.todoapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.todoapp.model.Workspace;
import ru.ddc.webstrtask12.todoapp.repository.WorkspaceRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    public Workspace save(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    public List<Workspace> findAll() {
        return workspaceRepository.findAll();
    }

    public Workspace findById(Long id) {
        return workspaceRepository.findById(id);
    }

    public Workspace update(Long id, Workspace workspace) {
        workspace.setId(id);
        workspaceRepository.update(workspace);
        return findById(id);
    }

    public int delete(Long id) {
        return workspaceRepository.deleteById(id);
    }

    public List<Workspace> findAllByCustomerId(Long customerId) {
        return null;
    }
}
