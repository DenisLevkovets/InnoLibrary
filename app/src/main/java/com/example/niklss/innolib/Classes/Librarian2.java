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

public class Librarian2 extends Librarian1 {
    public Librarian2(String name, String secondName, String adress, int id, String num, int status) throws FileNotFoundException {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian2(String[] a) throws FileNotFoundException {
        super(a);
    }

    public void addBook(String[] info, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.addB(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]), info[6], info[7], info[8], Integer.parseInt(info[9]));
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " addedBook " + db.getListOfBooks().get(db.getListOfBooks().size() - 1).getBookId());
    }

    public void addArticle(String[] info, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.addArt(info[0], info[1], info[2], info[3], info[4], info[5], Integer.parseInt(info[6]), Integer.parseInt(info[7]), info[8], Integer.parseInt(info[9]));
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " addedArticle " + db.getListOfArticles().get(db.getListOfArticles().size() - 1).getArticleId());
    }
//
    public void addAv(String[] info, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.addArt(info[0], info[1], info[2], info[3], info[4], info[5], Integer.parseInt(info[6]), Integer.parseInt(info[7]), info[8], Integer.parseInt(info[9]));
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " addedAV " + db.getListOfAV().get(db.getListOfAV().size() - 1).getAvId());
    }

    public void addPatron(String[] info, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        if (Integer.parseInt(info[4]) < 5) {
            db.createUser(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]));
            String[] buf = cal.getTime().toString().split(" ");
            String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
            db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " addedPatron " + db.getListOfUsers().get(db.getListOfUsers().size() - 1).getuId());
        }
    }
}
