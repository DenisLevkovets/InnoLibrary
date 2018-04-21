package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.IOException;

/**
 * Created by Niklss on 21.04.2018.
 */

public class Librarian2 extends Librarian1{
    public Librarian2(String name, String secondName, String adress, int id, String num, int status) {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian2(String[] a) {
        super(a);
    }

    public void addBook(String[] info, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
//        db.addB(info[0], info[1], Integer.parseInt(info[2]), Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]), info[6], info[7], info[8], info[9])
    }
}
