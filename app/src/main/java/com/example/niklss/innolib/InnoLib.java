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

    }




        View.OnClickListener clCatalog = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnoLib.this, Catalogs.class);
                startActivity(intent);
            }
        };
}

