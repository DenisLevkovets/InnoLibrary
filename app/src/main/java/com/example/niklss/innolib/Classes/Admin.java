package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.IOException;

/**
 * Created by Niklss on 21.04.2018.
 */

public class Admin extends UserCard {

    public Admin(String name, String secondName, String adress, int id, String num, int usersType) {
        super(name, secondName, adress, id, num, usersType);
    }

    public Admin(String[] card) {
        super(card);
    }

    public void addLibrarian(String[] information, int typeOfLibrarian, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (typeOfLibrarian == 1){
            db.createUser(information[0], information[1], information[2], information[3], 5);
        }
        else if (typeOfLibrarian == 2){
            db.createUser(information[0], information[1], information[2], information[3], 6);
        }
        else if (typeOfLibrarian == 3){
            db.createUser(information[0], information[1], information[2], information[3], 7);
        }

    }

    public void deleteLibrarian(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.deletePatron(id);
    }

    public void modifyLibrarian(UserCard user, Context context) throws IOException{
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateUserData(user);
    }
}
