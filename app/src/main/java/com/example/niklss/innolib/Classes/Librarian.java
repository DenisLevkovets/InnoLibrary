package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by Niklss on 31.01.2018.
 * Classes with teachers construction and their possibilities.
 */

public class Librarian extends UserCard{

    public Librarian(String name, String secondName, String adress, int id, String num, int isLib) {
        super(name, secondName, adress, id, num, isLib);
        //asd
    }

    public Librarian(String[] a){
        super(a);
    }

    private void add() {

    }

    private void remove(int id){

    }

    private void modify(int id){

    }

    private void checkOverDue(){

    }

    interface managePatrons{
    }

    private ArrayList<Patron> getUsers(Context context){
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfUsers();
    }

}
