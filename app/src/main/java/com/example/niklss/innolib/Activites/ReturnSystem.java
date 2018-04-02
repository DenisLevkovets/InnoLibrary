package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.03.2018.
 */

public class ReturnSystem extends Activity {
    AlertDialog.Builder ad;
    DataBaseHelper db;
    ArrayList<Patron> deptors;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returnsystem);
        ListView list=(ListView) findViewById(R.id.list);

        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        deptors =db.debtorUsers();

        String[] arr=new String[deptors.size()];
        for (int i = 0; i < deptors.size(); i++) {
            arr[i]=db.getUserInfoShort(deptors.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(ReturnSystem.this).setTitle("Title!");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ReturnSystem.this,"Book was returned",Toast.LENGTH_SHORT).show();
            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ReturnSystem.this,"You didnt choose",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setCancelable(true);

    }

    ListView.OnItemClickListener click = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ad.setMessage(db.getUserInfoFull(deptors.get(i)));
            ad.show();
        }
    });
}
