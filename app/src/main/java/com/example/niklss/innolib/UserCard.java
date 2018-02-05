package com.example.niklss.innolib;

/**
 * Created by user on 02.02.2018.
 * UserCard
 */

public class UserCard {
    private String uName;
    private String uAdress;
    private int uId;
    private String uNumber;

    public UserCard(String name, String adress, int id, String num ){
        this.uName = name;
        this.uAdress = adress;
        this.uId = id;
        this.uNumber = num;
    }

    public int getuId() {
        return uId;
    }

    public String getuAdress() {
        return uAdress;
    }

    public String getuName() {
        return uName;
    }

    public String getuNumber() {
        return uNumber;
    }

}
