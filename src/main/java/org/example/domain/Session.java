package org.example.domain;
@SuppressWarnings("ALL")

public class Session {
    static String currentUserName;

    public static void setCurrentUserName(String currentUserName) {
        Session.currentUserName = currentUserName;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    static boolean isLogged;
}
