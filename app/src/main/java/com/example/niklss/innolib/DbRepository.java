package com.example.niklss.innolib;

/**
 * Created by solo1 on 03.02.2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
public class DbRepository {
    private SQLiteDatabase db;
    private Context cont;
    public DbRepository(Context context) {
        //Подключение к базе данных
        db = new Base(context).getWritableDatabase();
        cont=context;
    }
    public ArrayList<String[]> getDataBooks()
    {
        Fields field1 = Fields.title;
        Fields field2 = Fields.author;
        Fields field3 = Fields.available_copies;
        ArrayList<String[]> list = new ArrayList<String[]>();

        Cursor cursor = db.query("Books", null, null, null, null, null, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {

            cursor.moveToFirst();


            do {
                String [] array= new String [3];
                array[0]=cursor.getString(field1.getFieldCode());
                array[1]=cursor.getString(field2.getFieldCode());
                array[2]=cursor.getString(field3.getFieldCode());
                list.add(array);


            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<String[]> getDataAV()
    {
        Fields field1 = Fields.title;
        Fields field2 = Fields.author;
        Fields field3 = Fields.numbers;
        ArrayList<String[]> list = new ArrayList<String[]>();

        Cursor cursor = db.query("AV", null, null, null, null, null, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {

            cursor.moveToFirst();


            do {
                String [] array= new String [3];
                array[0]=cursor.getString(field1.getFieldCode());
                array[1]=cursor.getString(field2.getFieldCode());
                array[2]=cursor.getString(field3.getFieldCode());
                list.add(array);


            } while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<String[]> getDataArticles()
    {
        Fields field1 = Fields.title;
        Fields field2 = Fields.author;
        Fields field3 = Fields.numbers;
        ArrayList<String[]> list = new ArrayList<String[]>();

        Cursor cursor = db.query("Articles", null, null, null, null, null, null);
        if ((cursor != null) && (cursor.getCount() > 0)) {

            cursor.moveToFirst();


            do {
                String [] array= new String [3];
                array[0]=cursor.getString(field1.getFieldCode());
                array[1]=cursor.getString(field2.getFieldCode());
                array[2]=cursor.getString(field3.getFieldCode());
                list.add(array);


            } while (cursor.moveToNext());
        }
        return list;
    }
}