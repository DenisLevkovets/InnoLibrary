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
    private ArrayList<Books> listOfBooks;


    public UserCard(String name, String secondName, String adress, int id, String num, int usersType ){
        listOfBooks=new ArrayList<>();
        this.uName = name;
        this.secondName = secondName;
        this.uAdress = adress;
        this.uNumber = num;
        this.usersType = usersType;
        this.uId = id;
    }

    public UserCard UserCardThroughString(String card) {
        String[] a = card.split(" ");
        UserCard userCard = new UserCard(a[0], a[1], a[2], Integer.parseInt(a[3]), a[4], Integer.parseInt(a[5]));
        return userCard;
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

    public ArrayList<Books> getListOfBooks() {
        return listOfBooks;
    }

    public String getSecondName() {
        return secondName;
    }
}
