package com.example.pdf_project.admin_panel;

public class User_model {
    String name,phobe,email,randomkey;

    public User_model() {
    }

    public User_model(String name, String phobe, String email, String randomkey) {
        this.name = name;
        this.phobe = phobe;
        this.email = email;
        this.randomkey = randomkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhobe() {
        return phobe;
    }

    public void setPhobe(String phobe) {
        this.phobe = phobe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRandomkey() {
        return randomkey;
    }

    public void setRandomkey(String randomkey) {
        this.randomkey = randomkey;
    }
}
