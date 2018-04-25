package com.example.niklss.innolib.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by user on 22.04.2018.
 */

public class AdminLog extends AppCompatActivity {
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlog);
        ListView lv=(ListView) findViewById(R.id.list);
        try {
            db=new DataBaseHelper(AdminLog.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String[] arr={db.inp()};
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(AdminLog.this,android.R.layout.simple_list_item_1,arr);
            lv.setAdapter(adapter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
