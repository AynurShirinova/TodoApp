package org.example.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.domain.Priority;
import org.example.domain.Status;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UsersDTO {
    UUID id;
    String userName;
    private String mail;
    private String password;
}
