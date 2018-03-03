package com.example.niklss.innolib.Activites;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.DataBase.DbRepository;
import com.example.niklss.innolib.R;

import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 */

public class InnoLib extends AppCompatActivity {

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.search);
        Button catalog = (Button) findViewById(R.id.catalog);
        catalog.setOnClickListener(clCatalog);





    }


    public ArrayList<String[]> getBooks(){
//return ArrayList of books with author and available copies
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
    }








        View.OnClickListener clCatalog = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnoLib.this, Catalogs.class);
                startActivity(intent);
            }
        };
}

