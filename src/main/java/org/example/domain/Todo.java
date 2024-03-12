package org.example.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

//@SuppressWarnings("ALL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table
public class Todo {
    @Id
    UUID id;
    UUID fkUserId;
    private String projectId;
    private String title;
    private String description;
    private String createdBy;
    private UUID assignedTo;
    private Status status;
    private String priority;
    private LocalDate created;

}
