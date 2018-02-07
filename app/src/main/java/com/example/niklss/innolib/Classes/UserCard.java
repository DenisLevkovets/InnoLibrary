package com.example.niklss.innolib.Classes;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String uAdress;
    private int uId;
    private String uNumber;
    private int uStatus;

    public UserCard(String name, String adress,  String num , int status ){
        this.uName = name;
        this.uAdress = adress;
        this.uNumber = num;
        this.uStatus = status;
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
