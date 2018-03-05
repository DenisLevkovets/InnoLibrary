package com.example.niklss.innolib.Activites;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

/**
 * Created by user on 02.02.2018.
 */

public class InnoLibLib extends AppCompatActivity {

    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);
        Button returns = (Button) findViewById(R.id.returns);
        Button catalog = (Button) findViewById(R.id.catalog);
        Button users = (Button) findViewById(R.id.users);
        users.setOnClickListener(clUsers);
        returns.setOnClickListener(clReturn);
        catalog.setOnClickListener(clCatalog);
    }

    View.OnClickListener clCatalog = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InnoLibLib.this, CatalogsLib.class);
            startActivity(intent);
        }
    };

    View.OnClickListener clUsers = (new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(InnoLibLib.this,UsersLib.class);
            startActivity(intent);
        }
    });

    View.OnClickListener clReturn = ( new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(InnoLibLib.this,ReturnSystem.class);
            startActivity(intent);
        }
    });
}

