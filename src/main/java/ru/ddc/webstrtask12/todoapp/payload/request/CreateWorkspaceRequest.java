package ru.ddc.webstrtask12.todoapp.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkspaceRequest {
    private String name;
    private String description;
}
