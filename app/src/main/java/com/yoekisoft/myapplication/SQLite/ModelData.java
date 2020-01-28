package com.yoekisoft.myapplication.SQLite;

public class ModelData {


    static String name;
    static String email;
    static String phone_number;


    public ModelData(String name, String email, String phone_number) {
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
    }

    public ModelData() {

    }

    public String getName() {
        return this.name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public static String asdfg() {
        return email;
    }
}