package com.example.niklss.innolib.Classes;

import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String secondName;
    private String uAdress;
    private String uNumber;
    private int usersType;
    private int uId;
    private ArrayList<Books> listOfBooks = new ArrayList();


    public UserCard(String name, String secondName, String adress, int id, String num, int usersType ){
        this.uName = name;
        this.secondName = secondName;
        this.uAdress = adress;
        this.uNumber = num;
        this.usersType = usersType;
        this.uId = id;
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

    public int getUsersType() {
        return usersType;
    }

    public void addBookToTheList(Books book){listOfBooks.add(book);}

    public String getSecondName() {
        return secondName;
    }
}
