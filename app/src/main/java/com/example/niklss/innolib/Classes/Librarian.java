package com.example.niklss.innolib.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.niklss.innolib.Activites.InnoLib;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

/**
 * Created by Niklss on 31.01.2018.
 * Classes with teachers construction and their possibilities.
 */

public class Librarian extends UserCard{

    public SQLiteDatabase db;


    public Librarian(String name, String secondName, String adress, int id, String num, int isLib) {
        super(name, secondName, adress, id, num, isLib);

    }

    public void addB(String title,String author,int available_copies,int type,int price,int edition,String date,String published_by,String keywords,int is_bestseller) {
        DataBaseHelper l = new DataBaseHelper(this);
        l.addB(title,author,available_copies,type,price,edition,date,published_by,keywords,is_bestseller);

    }

    private void removeB(int id){

    }

    private void modifyB(int id){

    }

    private void checkOverDue(){

    }

    interface managePatrons{
    }

}
