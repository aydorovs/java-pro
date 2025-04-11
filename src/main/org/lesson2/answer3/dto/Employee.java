package org.lesson2.answer3.dto;

public class Employee {

    private String name;
    private int age;
    private String position;

    public Employee(String name, int age, String position) {
        if (age > 70 || age < 18) {
            throw new IllegalArgumentException("Некорректный возраст.");
        }
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }
}