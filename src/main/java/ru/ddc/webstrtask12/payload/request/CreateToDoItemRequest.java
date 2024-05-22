package ru.ddc.webstrtask12.payload.request;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateToDoItemRequest {
    private String name;
    private String description;
    private Boolean isDone = false;
    private LocalDateTime startDateTime;
    private Duration duration;
    private String priority;
    private Boolean isPostponed = false;
}
