package com.example.mindenamirecept;

public class User {

    String id;
    String username;

    String name;
    String email;

    public User(){

    }

    public User(String id, String username, String name, String email) {
        this.id = id;
        this.username = username;

        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
