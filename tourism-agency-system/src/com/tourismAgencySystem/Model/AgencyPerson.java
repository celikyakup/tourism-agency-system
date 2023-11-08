package com.tourismAgencySystem.Model;

public class AgencyPerson extends User{
    public AgencyPerson() {
    }

    public AgencyPerson(int id, String name, String uname, String password, String type) {
        super(id, name, uname, password, type);
    }
}
