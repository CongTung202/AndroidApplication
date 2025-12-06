package com.example.listviewobject.model;

public class Person {
    private String id;
    private String name;
    private String gender;
    private String dateOfBirth;

    public Person(String id, String name, String gender, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return  id + "-" +
                name + "-" +
                gender + "-" +
                dateOfBirth;
    }
}
