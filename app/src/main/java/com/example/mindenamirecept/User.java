package com.example.mindenamirecept;

public class User {

    String id;
    String username;
    String password;
    String name;
    String email;

    public User(){

    }

    public User(String id, String username, String password, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
