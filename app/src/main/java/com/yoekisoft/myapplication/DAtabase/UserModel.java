package com.yoekisoft.myapplication.DAtabase;

public class UserModel {

    public String Fname;
    public String Sname;
    public String email;
    public String phone;

    // Default constructor required for calls to
    // DataSnapshot.getValue(UserModel.class)
    public UserModel() {
    }

    public UserModel(String Fname,String Sname, String email,String phone) {
        this.Fname = Fname;
        this.Sname = Sname;
        this.email=email;
        this.phone=phone;
    }
}