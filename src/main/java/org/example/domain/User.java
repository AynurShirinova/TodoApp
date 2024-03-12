package org.example.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

//@SuppressWarnings("ALL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table
public class User {
    @Id
    UUID id;
    private String userName;
    private String mail;
    private String password;


}
