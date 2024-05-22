package ru.ddc.webstrtask12.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.model.Workspace;
import ru.ddc.webstrtask12.repository.WorkspaceRepository;

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

    public int update(Workspace workspace) {
        return workspaceRepository.update(workspace);
    }

    public int delete(Long id) {
        return workspaceRepository.deleteById(id);
    }
}
