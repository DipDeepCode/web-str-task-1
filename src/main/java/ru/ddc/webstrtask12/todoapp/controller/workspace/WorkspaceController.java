package ru.ddc.webstrtask12.todoapp.controller.workspace;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.webstrtask12.todoapp.model.Workspace;
import ru.ddc.webstrtask12.todoapp.payload.request.CreateWorkspaceRequest;
import ru.ddc.webstrtask12.todoapp.payload.request.UpdateWorkspaceRequest;
import ru.ddc.webstrtask12.todoapp.dto.WorkspaceDto;
import ru.ddc.webstrtask12.todoapp.service.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkspaceController {
    private final WorkspaceService workspaceService;
    private final ModelMapper modelMapper;
    private final WorkspaceDtoModelAssembler assembler;

    @PostMapping(value = "/workspaces", produces = {"application/hal+json"})
    public ResponseEntity<?> createWorkspace(@RequestBody CreateWorkspaceRequest request) {
        Workspace workspace = workspaceService.save(modelMapper.map(request, Workspace.class));

//        URI uri = MvcUriComponentsBuilder.fromController(getClass())
//                        .path("/{id}")
//                        .buildAndExpand(workspace.getId())
//                        .toUri();

        WorkspaceDto workspaceDto = modelMapper.map(workspace, WorkspaceDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(assembler.toModel(workspaceDto));
    }

    @GetMapping(value = "/workspaces", produces = {"application/hal+json"})
    public ResponseEntity<?> findWorkspaces() {
        List<WorkspaceDto> resources = workspaceService.findAll()
                .stream()
                .map(workspace -> modelMapper.map(workspace, WorkspaceDto.class))
                .toList();
        return ResponseEntity.ok().body(assembler.toCollectionModel(resources));
    }

    @GetMapping(value = "/workspaces/{id}", produces = {"application/hal+json"})
    public ResponseEntity<?> findWorkspace(@PathVariable("id") Long id) {
        Workspace workspace = workspaceService.findById(id);
        WorkspaceDto workspaceDto = modelMapper.map(workspace, WorkspaceDto.class);
        return ResponseEntity.ok().body(assembler.toModel(workspaceDto));
    }

    @PutMapping("/workspaces/{id}")
    public ResponseEntity<?> updateWorkspace(@PathVariable("id") Long id,
                                             @RequestBody UpdateWorkspaceRequest request) {
        Workspace workspace = workspaceService.update(id, modelMapper.map(request, Workspace.class));
        WorkspaceDto workspaceDto = modelMapper.map(workspace, WorkspaceDto.class);
        return ResponseEntity.ok().body(assembler.toModel(workspaceDto));
    }

    @DeleteMapping("/workspaces/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable("id") Long id) {
        workspaceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
