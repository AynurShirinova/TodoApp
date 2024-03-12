package org.example.domain;


import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
@SuppressWarnings("ALL")
@Getter
@Builder
public class User {
    UUID id;
    private String userName;
    private String mail;
    private String password;



}
