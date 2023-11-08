package com.tourismAgencySystem.Model;

public class Admin extends User{
    public Admin() {
    }

    public Admin(int id, String name, String uname, String password, String type) {
        super(id, name, uname, password, type);
    }
}
