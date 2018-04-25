package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Niklss on 21.04.2018.
 */

public class Admin extends UserCard {
    public Admin(String name, String secondName, String adress, int id, String num, int usersType) throws FileNotFoundException {
        super(name, secondName, adress, id, num, usersType);
    }

    public Admin(String[] card) throws FileNotFoundException {
        super(card);
    }

    public void addLibrarian(String[] information, int typeOfLibrarian, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        if (typeOfLibrarian == 1){
            db.createUser(information[0], information[1], information[2], information[3], 5);
        }
        else if (typeOfLibrarian == 2){
            db.createUser(information[0], information[1], information[2], information[3], 6);
        }
        else if (typeOfLibrarian == 3){
            db.createUser(information[0], information[1], information[2], information[3], 7);
        }

        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " Admin" + " addedLibrarian " + db.getListOfLibrarians().get(db.getListOfLibrarians().size() - 1).getuId());
    }

    public void deleteLibrarian(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.deletePatron(id);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " Admin" + " deletedLibrarian " + db.getListOfLibrarians().get(db.getListOfLibrarians().size() - 1).getuId());
    }

    public void modifyLibrarian(UserCard user, Context context) throws IOException{
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.updateUserData(user);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " Admin" + " modifiedLibrarian " + db.getListOfLibrarians().get(db.getListOfLibrarians().size() - 1).getuId());
    }

    public String checkLog(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.inp();
    }

    private int month(String mm) {
        switch (mm) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 0;
        }
    }
}
