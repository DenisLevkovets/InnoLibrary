package com.example.niklss.innolib;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 */

public class InnoLib extends AppCompatActivity {

    private Base mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.search);
        Button catalog = (Button) findViewById(R.id.catalog);
        catalog.setOnClickListener(clCatalog);
        int a=0;
        //database initialization
        mDBHelper = new Base(this);

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        mDBHelper.takeBook(mDb,1);
        ArrayList<String[]> list = getBooks();
        for (int i = 0; i <list.size() ; i++) {
            for (int j = 0; j <list.get(i).length ; j++) {
                System.out.print(list.get(i)[j]+" ");
            }
            System.out.println("");
        }




    }



    public ArrayList<String[]> getBooks(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataBooks();
        return Data;
    }
    public ArrayList<String[]> getAV(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataAV();
        return Data;
    }
    public ArrayList<String[]> getArticles(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataArticles();
        return Data;
    }





        View.OnClickListener clCatalog = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnoLib.this, Catalog.class);
                startActivity(intent);
            }
        };
}

