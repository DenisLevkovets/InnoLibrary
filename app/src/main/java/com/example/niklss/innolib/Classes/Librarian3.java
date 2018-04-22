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

public class Librarian3 extends Librarian2 {
    private PrintWriter out = new PrintWriter(new File("Log"));
    public Librarian3(String name, String secondName, String adress, int id, String num, int status) throws FileNotFoundException {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian3(String[] a) throws FileNotFoundException {
        super(a);
    }

    public void deleteBook(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.deleteBook(id);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "deletedBook " + id);
        out.close();
    }

    public void deleteArticle(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.deleteArticle(id);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "deletedArticle " + id);
        out.close();
    }

    public void deleteAv(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.deleteAV(id);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "deletedAV " + id);
        out.close();
    }

    public void deletePatron(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (Integer.parseInt(db.getArrayUser(id)[3]) < 5) {
            db.deletePatron(id);
            out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "deletedPatron " + id);
            out.close();
        }
    }
}
