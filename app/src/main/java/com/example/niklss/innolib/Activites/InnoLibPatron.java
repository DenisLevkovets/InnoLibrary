package com.example.niklss.innolib.Activites;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 */

public class InnoLibPatron extends AppCompatActivity {

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patron);
        Button search = (Button) findViewById(R.id.search);
        Button catalog = (Button) findViewById(R.id.catalog);
        Button blist = (Button) findViewById(R.id.blist);
        blist.setOnClickListener(clBList);
        catalog.setOnClickListener(clCatalog);
    }



        View.OnClickListener clCatalog = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnoLibPatron.this, CatalogsPatron.class);
                startActivity(intent);
            }
        };

        View.OnClickListener clBList = ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InnoLibPatron.this, BooksListPatron.class);
                startActivity(intent);
            }
        });
}

