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

public class Librarian3 extends Librarian2 {
    public Librarian3(String name, String secondName, String adress, int id, String num, int status) throws FileNotFoundException {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian3(String[] a) throws FileNotFoundException {
        super(a);
    }

    public Librarian3(UserCard a) throws FileNotFoundException {
        super(a.getuName(), a.getSecondName(), a.getuAddress(), a.getuId(), a.getuNumber(), a.getUsersType());
    }

    public void deleteBook(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.deleteBook(id);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " deletedBook " + id);
    }

    public void deleteArticle(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.deleteArticle(id);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " deletedArticle " + id);
    }

    public void deleteAv(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.deleteAV(id);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " deletedAV " + id);
    }

    public void deletePatron(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        if (Integer.parseInt(db.getArrayUser(id)[3]) < 5) {
            db.deletePatron(id);
            String[] buf = cal.getTime().toString().split(" ");
            String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
            db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " deletedPatron " + id);
        }
    }
}
