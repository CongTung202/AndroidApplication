package com.example.simplespinner.model;

public class Person {
    public int id;
    public String name;
    public int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
