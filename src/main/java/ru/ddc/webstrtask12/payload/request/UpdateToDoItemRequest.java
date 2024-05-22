package ru.ddc.webstrtask12.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateToDoItemRequest {
    private String name;
    private String description;
    private Boolean isDone = false;
    private LocalDateTime startDateTime;
    private Duration duration;
    private String priority;
    private Boolean isPostponed = false;
}
