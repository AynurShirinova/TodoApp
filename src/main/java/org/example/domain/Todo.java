package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("ALL")
@Getter
@Setter
@Builder
public class Todo {
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

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
