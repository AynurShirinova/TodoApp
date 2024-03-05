package org.example.domain;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("ALL")

public class Database {
    public final static List<User> USERS = new ArrayList<>();
    public List<User> getUSERS() {
        return USERS;
    }
    public final static List<Todo> TASKS = new ArrayList<>();
}
