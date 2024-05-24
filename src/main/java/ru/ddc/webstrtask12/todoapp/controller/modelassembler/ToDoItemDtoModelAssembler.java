package ru.ddc.webstrtask12.todoapp.controller.modelassembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.ddc.webstrtask12.todoapp.controller.ToDoItemController;
import ru.ddc.webstrtask12.todoapp.controller.WorkspaceController;
import ru.ddc.webstrtask12.todoapp.dto.ToDoItemDto;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ToDoItemDtoModelAssembler implements SimpleRepresentationModelAssembler<ToDoItemDto> {

    @Override
    public void addLinks(EntityModel<ToDoItemDto> resource) {
        ToDoItemDto content = resource.getContent();
        if (content != null) {
            Long id = content.getId();
            Long workspaceId = content.getWorkspaceId();
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(WorkspaceController.class).getWorkspaces())
                    .slash(workspaceId)
                    .withRel("workspace"));
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(ToDoItemController.class).findToDoItemsByWorkspaceId(workspaceId))
                    .slash(id)
                    .withSelfRel());
        }
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<ToDoItemDto>> resources) {
        Collection<EntityModel<ToDoItemDto>> collection = resources.getContent();
        if (!collection.isEmpty()) {
            ToDoItemDto content = collection.stream().findAny().orElseThrow().getContent();
            if (content != null) {
                resources.add(
                        linkTo(methodOn(ToDoItemController.class).findToDoItemsByWorkspaceId(content.getWorkspaceId()))
                        .withSelfRel()
                );
            }
        }
    }
}
