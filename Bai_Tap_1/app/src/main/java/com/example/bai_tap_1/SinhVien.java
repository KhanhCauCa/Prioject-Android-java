package com.example.bai_tap_1;

public class SinhVien{
    public  String Name;
    public  Integer Age;

    public SinhVien(String name, Integer age) {
        Name = name;
        Age = age;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
