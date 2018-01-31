package com.example.niklss.innolib;

import java.util.ArrayList;

/**
 * Created by Niklss on 31.01.2018.
 */

public class UserCard implements UserInterface{
    boolean isStudent;
    String[] name;
    String address;
    String phoneNumber;
    int cardNumber;

    ArrayList materials = new ArrayList();

    public UserCard(boolean isIt, int num, String nameU){
        isStudent = isIt;
        cardNumber = num;
        name = nameU.split(" ");
    }

    public void checkOut(int bookId){
        if (hasBook){
            materials.add(bookId);
        }
    }

    public void searchFor(String nameAB){

    }

    public boolean isFuled(){
        if (address.isEmpty() || phoneNumber.isEmpty()){
            return false;
        }

        return true;
    }

    public void setAddress(String setAdd){
        address = setAdd;
    }

    public void setPhone(String setPh){
        phoneNumber = setPh;
    }
}
