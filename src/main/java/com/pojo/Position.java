package com.pojo;

public class Position {
    String id;
    String position;
    Float salary;

    @Override
    public String toString() {
        return "Position{" +
                "id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Position() {
    }

    public Position(String id, String position, Float salary) {
        this.id = id;
        this.position = position;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }
}
