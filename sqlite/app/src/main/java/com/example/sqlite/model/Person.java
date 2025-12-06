package com.example.sqlite.model;

import java.util.Date;

public class Person {
    private int Id;
    private String Name;
    private Date Birthday;
    private String Gender;

    public Person() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Person(int id, String name, Date birthday, String gender) {
        Id = id;
        Name = name;
        Birthday = birthday;
        Gender = gender;
    }
    @Override
    public String toString() {
        return
                 Id + '\t' +
                Name + '\t' + Birthday +
                '\t' + Gender + '\t'
             ;
    }
}
