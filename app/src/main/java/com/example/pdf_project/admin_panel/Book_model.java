package com.example.pdf_project.admin_panel;

public class Book_model {


    String name,pdf;
    public Book_model() {
    }

    public Book_model(String name, String pdf) {
        this.name = name;
        this.pdf = pdf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
