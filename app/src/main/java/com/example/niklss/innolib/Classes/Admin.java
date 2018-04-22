package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Created by Niklss on 21.04.2018.
 */

public class Admin extends UserCard {
    private PrintWriter out = new PrintWriter(new File("Log"));
    public Admin(String name, String secondName, String adress, int id, String num, int usersType) throws FileNotFoundException {
        super(name, secondName, adress, id, num, usersType);
    }

    public Admin(String[] card) throws FileNotFoundException {
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

        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "addedLibrarian " + db.getListOfUsers().get(db.getListOfUsers().size() - 1).getuId());
        out.close();
    }

    public void deleteLibrarian(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.deletePatron(id);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "deletedLibrarian " + db.getListOfUsers().get(db.getListOfUsers().size() - 1).getuId());
        out.close();
    }

    public void modifyLibrarian(UserCard user, Context context) throws IOException{
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateUserData(user);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "modifiedLibrarian " + db.getListOfUsers().get(db.getListOfUsers().size() - 1).getuId());
        out.close();
    }
}
