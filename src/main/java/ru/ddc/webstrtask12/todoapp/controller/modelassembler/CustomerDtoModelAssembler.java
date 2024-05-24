package ru.ddc.webstrtask12.todoapp.controller.modelassembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import ru.ddc.webstrtask12.todoapp.controller.CustomerController;
import ru.ddc.webstrtask12.todoapp.dto.CustomerDto;
import ru.ddc.webstrtask12.todoapp.controller.WorkspaceController;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
public class CustomerDtoModelAssembler implements SimpleRepresentationModelAssembler<CustomerDto> {

    @Override
    public void addLinks(EntityModel<CustomerDto> resource) {
        CustomerDto content = resource.getContent();
        if (content != null) {
            Long id = content.getId();
            resource.add(WebMvcLinkBuilder.linkTo(methodOn(CustomerController.class).getCustomers())
                    .slash(id)
                    .withSelfRel());
            resource.add(linkTo(methodOn(WorkspaceController.class).getWorkspacesByCustomerId(id))
                    .withRel("workspaces"));
        }
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<CustomerDto>> resources) {
        Collection<EntityModel<CustomerDto>> collection = resources.getContent();
        if (!collection.isEmpty()) {
            CustomerDto content = collection.stream().findAny().orElseThrow().getContent();
            if (content != null) {
                resources.add(linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel());
            }
        }
    }
}
