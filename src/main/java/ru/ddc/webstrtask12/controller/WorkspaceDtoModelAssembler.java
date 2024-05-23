package ru.ddc.webstrtask12.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ru.ddc.webstrtask12.dto.WorkspaceDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class WorkspaceDtoModelAssembler implements SimpleRepresentationModelAssembler<WorkspaceDto> {

    @Override
    public void addLinks(EntityModel<WorkspaceDto> resource) {
        WorkspaceDto content = resource.getContent();
        if (content != null) {
            Long id = content.getId();
            resource.add(linkTo(methodOn(WorkspaceController.class).findWorkspaces()).slash(id).withSelfRel());
            resource.add(linkTo(methodOn(ToDoItemController.class).findToDoItemsByWorkspaceId(id)).withRel("toToItems"));
        }
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<WorkspaceDto>> resources) {
        resources.add(linkTo(methodOn(WorkspaceController.class).findWorkspaces()).withSelfRel());
    }
}
