package org.example.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@SuppressWarnings("ALL")
@Getter
public class User {
    UUID id;
    private String userName;
   // private String mail;
    private String password;
@Builder
    public User(String password, String userName,UUID id) {
        this.id = UUID.randomUUID();
        this.password = password;
       // this.mail=mail;
        this.userName = userName;
    }

}
