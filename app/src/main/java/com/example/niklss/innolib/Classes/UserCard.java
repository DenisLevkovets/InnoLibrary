package com.example.niklss.innolib.Classes;

import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String secondName;
    private String uAddress;
    private String uNumber;
    private int usersType;
    private int uId;
    private ArrayList<Books> listOfBooks;


    public UserCard(String name, String secondName, String adress, int id, String num, int usersType) {
        this.uName = name;
        this.secondName = secondName;
        this.uAddress = adress;
        this.uNumber = num;
        this.usersType = usersType;
        this.uId = id;
    }

    public UserCard(String[] card) {
        this.uName = card[0];
        this.secondName = card[1];
        this.uAddress = card[2];
        this.uId = Integer.parseInt(card[3]);
        this.uNumber = card[4];
        this.usersType = Integer.parseInt(card[5]);
    }

    public int getuId() {
        return uId;
    }

    public String getuAddress() {
        return uAddress;
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

    public void addBookToTheList(Books book) {
        listOfBooks.add(book);
    }

    public ArrayList<Books> getListOfBooks() {
        return listOfBooks;
    }

    public String getSecondName() {
        return secondName;
    }
}
