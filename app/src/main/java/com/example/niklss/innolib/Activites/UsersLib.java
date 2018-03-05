package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niklss.innolib.R;

/**
 * Created by user on 28.02.2018.
 */

public class UsersLib extends Activity {
    AlertDialog.Builder ad;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userslib);
        ListView list=(ListView) findViewById(R.id.list);
        String[] arr={"1","2","3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(UsersLib.this).setTitle("Title!");
        ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UsersLib.this,"It was deleted",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UsersLib.this,"It was modified",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UsersLib.this,"You didnt choose",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setCancelable(true);

    }

    ListView.OnItemClickListener click = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ad.setMessage("What do you want"+String.valueOf(i));
            ad.show();
        }
    });


}
