package com.example.niklss.innolib.Activites;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.DataBase.Base;
import com.example.niklss.innolib.DataBase.DbRepository;
import com.example.niklss.innolib.R;

import java.io.IOException;
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

        //database initialization
<<<<<<< HEAD
        mDBHelper = new Base(this);
=======
       mDBHelper = new Base(this);
>>>>>>> 892a6f1a3e1234937369bdaef29ecb2e65b5e478
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

<<<<<<< HEAD
//        AddU();
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String> list = repository.getUsers();
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
=======


>>>>>>> 892a6f1a3e1234937369bdaef29ecb2e65b5e478






    }


//return ArrayList of books with author and available copies
   /* public ArrayList<String[]> getBooks(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataBooks();
        return Data;
    }
    //return ArrayList of audio/video files with author and available copies
    public ArrayList<String[]> getAV(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataAV();
        return Data;
    }
    //return ArrayList of articles with author and available copies
    public ArrayList<String[]> getArticles(){
        final DbRepository repository = new DbRepository(this.getApplicationContext());
        ArrayList<String[]> Data = repository.getDataArticles();
        return Data;
    }*/

    public void AddU(){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о госте является значениями ключей
        ContentValues values = new ContentValues();
        values.put("First_name", "Denis");
        values.put("Last_name", "Levkovets");
        values.put("address", "Innon");
        values.put("phone", 38484849);
        values.put("status", "s");

        long newRowId = db.insert("Users", null, values);

    }





<<<<<<< HEAD
    View.OnClickListener clCatalog = new View.OnClickListener(){
=======


        View.OnClickListener clCatalog = new View.OnClickListener(){
>>>>>>> 892a6f1a3e1234937369bdaef29ecb2e65b5e478

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InnoLib.this, Catalogs.class);
            startActivity(intent);
        }
    };
}

