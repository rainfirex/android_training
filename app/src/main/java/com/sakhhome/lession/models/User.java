package com.sakhhome.lession.models;

public class User {
    private static int _ID = 0;

    private int id;
    private String name;
    private String email;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, String email, int age) {
        _ID++;

        this.id = _ID;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
