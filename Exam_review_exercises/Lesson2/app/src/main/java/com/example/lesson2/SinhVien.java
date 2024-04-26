package com.example.lesson2;

public class SinhVien
{
    public int id;
    public String identification_number;
    public String name ;
    public float math,physical,chemistry;
    public SinhVien(int id, String identification_number, String name, float math, float physical, float chemistry) {
        this.id = id;
        this.identification_number = identification_number;
        this.name = name;
        this.math = math;
        this.physical = physical;
        this.chemistry = chemistry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentification_number() {
        return identification_number;
    }

    public void setIdentification_number(String identification_number) {
        this.identification_number = identification_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMath() {
        return math;
    }

    public void setMath(float math) {
        this.math = math;
    }

    public float getPhysical() {
        return physical;
    }

    public void setPhysical(float physical) {
        this.physical = physical;
    }

    public float getChemistry() {
        return chemistry;
    }

    public void setChemistry(float chemistry) {
        this.chemistry = chemistry;
    }

    public float tongdiem(){
        return math+physical+chemistry;
    }

    public float diemTrungBinh(){
        return tongdiem()/3;
    }
}
