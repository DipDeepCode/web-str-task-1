package ru.ddc.webstrtask12.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.model.Workspace;
import ru.ddc.webstrtask12.payload.request.CreateWorkspaceRequest;
import ru.ddc.webstrtask12.payload.request.UpdateWorkspaceRequest;
import ru.ddc.webstrtask12.service.WorkspaceService;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;
    private final ModelMapper modelMapper;

    @PostMapping("/workspaces")
    public ResponseEntity<?> createWorkspace(@RequestBody CreateWorkspaceRequest request) {
        Workspace workspace = workspaceService.save(modelMapper.map(request, Workspace.class));
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/workspaces/%d".formatted(workspace.getId())))
                .body(workspace);
    }

    @GetMapping("/workspaces")
    public ResponseEntity<?> findWorkspaces() {
        return ResponseEntity.ok().body(workspaceService.findAll());
    }

    @GetMapping("/workspaces/{id}")
    public ResponseEntity<?> findWorkspace(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspaceService.findById(id));
    }

    @PutMapping("/workspaces/{id}")
    public ResponseEntity<?> updateWorkspace(@PathVariable("id") Long id,
                                             @RequestBody UpdateWorkspaceRequest request) {
        return ResponseEntity.ok().body(workspaceService.update(id, modelMapper.map(request, Workspace.class)));
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspaceService.delete(id));
    }
}
