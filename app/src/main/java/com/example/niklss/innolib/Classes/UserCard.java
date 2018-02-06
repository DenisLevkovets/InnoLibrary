package com.example.niklss.innolib.Classes;

import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String uAdress;
    private int uId;
    private String uNumber;
    private int usersType;
    private ArrayList<Books> listOfBooks = new ArrayList();


    public UserCard(String name, String adress, int id, String num, int users ){
        this.uName = name;
        this.uAdress = adress;
        this.uId = id;
        this.uNumber = num;
        this.usersType = users;
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
}
