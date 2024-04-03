package org.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;
//@SuppressWarnings("ALL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "todos")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Todo {
    @Id
     UUID id;
 //  UUID fkUserId;
     String projectId;
     String title;
     String description;
     String createdBy;
     UUID assignedTo;
     Status status;
     Priority priority;
     LocalDate created;
     String endDateInput;
     String startDateInput;

}
