package com.example.niklss.innolib.Classes;

/**
 * Created by user on 02.02.2018.
 * It should content the patrons functions and difference between teachers and student
 */

public class Patron extends UserCard {

    private boolean isStudent;

    public Patron(String name, String adress, int id, String num, boolean isSt) {
        super(name, adress, id, num);
    }

    private void searchDoc(String anyName){

    }

//    private void checkOut{
//        int status = ;
//        int bookRef = ;
//        if ((status && bookRef == 0) || (!status)){
//
//        }
//        else{
//            System.out.println("Not available");
//        }
//    }

    private void returnDoc(int id){

    }


}
