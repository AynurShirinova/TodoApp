package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.domain.Priority;
import org.example.domain.Status;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class TodoDTO {
    private UUID id;
    private String title;
    private String description;
    private String createdBy;
    private UUID assignedTo;
    private Status status;
    private Priority priority;
    private String created;


}
