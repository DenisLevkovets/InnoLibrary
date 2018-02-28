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


    public UserCard(String name, String secondName, String address, int id, String num, int usersType ){
        this.uName = name;
        this.secondName = secondName;
        this.uAddress = address;
        this.uId = id;
        this.uNumber = num;
        this.usersType = usersType;
    }

    public UserCard(String card) {
        String[] a = card.split(" ");
        this.uName = a[0];
        this.secondName = a[1];
        this.uAddress = a[2];
        this.uId = Integer.parseInt(a[3]);
        this.uNumber = a[4];
        this.usersType = Integer.parseInt(a[5]);
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

    public void addBookToTheList(Books book){listOfBooks.add(book);}

    public ArrayList<Books> getListOfBooks() {
        return listOfBooks;
    }

    public String getSecondName() {
        return secondName;
    }
}
