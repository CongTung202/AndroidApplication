package com.example.sqlite_qlnv.model;
import java.util.Date;

public class Person {
    private int id;
    private String name;
    private Date birthday;
    private String gender;

    public Person() {}

    public Person(int id, String name, Date birthday, String gender) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }
    // ... (Giữ nguyên các Getter và Setter như code bạn gửi)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}