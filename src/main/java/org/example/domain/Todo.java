package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@SuppressWarnings("ALL")
@Getter
@Setter
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

    @Builder
    public Todo(String projectİD,String title, String description, String createdBy, UUID assignedTo, Status status, String priority, LocalDate created,UUID fkUserId) {
        this.id = UUID.randomUUID();
        this.fkUserId=fkUserId;
        this.projectId=projectİD;
        this.assignedTo = assignedTo;
        this.created = created;
        this.createdBy = createdBy;
        this.priority = priority;
        this.description = description;
        this.status = status;
        this.title = title;
    };
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
