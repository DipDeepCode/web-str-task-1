package ru.ddc.webstrtask12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.model.Workspace;
import ru.ddc.webstrtask12.service.WorkspaceService;

import java.net.URI;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<?> createWorkspace(@RequestBody Workspace workspace) {
        workspace = workspaceService.save(workspace);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/workspaces/%d".formatted(workspace.getId())))
                .body(workspace);
    }

    @GetMapping
    public ResponseEntity<?> findWorkspaces() {
        return ResponseEntity.ok().body(workspaceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findWorkspace(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspaceService.findById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateWorkspace(@RequestBody Workspace workspace) {
        return ResponseEntity.ok().body(workspaceService.update(workspace));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(workspaceService.delete(id));
    }
}
