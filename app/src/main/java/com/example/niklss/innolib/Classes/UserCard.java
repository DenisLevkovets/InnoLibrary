package com.example.niklss.innolib.Classes;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String uAdress;
    private String uNumber;

//    public UserCard(String name, String adress, String num ){
//        this.uName = name;
//        this.uAdress = adress;
//        this.uNumber = num;
//    }

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
