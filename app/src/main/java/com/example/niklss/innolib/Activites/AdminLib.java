package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.Dialogs.AddLib;
import com.example.niklss.innolib.Dialogs.ModifyLib;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 28.02.2018.
 */

public class AdminLib extends Activity {
    AlertDialog.Builder ad;
    DataBaseHelper db;
    int index;
    ArrayList<Patron> users;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlib);
        ListView list=(ListView) findViewById(R.id.list);


        try {
            db= new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            users= db.getListOfLibrarians();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] arr=new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            arr[i]=users.get(i).getuName()+" "+users.get(i).getSecondName();
        }

        FloatingActionButton add= (FloatingActionButton) findViewById(R.id.add);

        add.setOnClickListener(clAdd);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(AdminLib.this).setTitle("Action with librarian");

        ad.setPositiveButton("Delete",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AdminLib.this,"It was deleted",Toast.LENGTH_SHORT).show();
                db.deletePatron(users.get(index).getuId());
            }
        });
        ad.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Patron patron=users.get(index);
                DialogFragment modify=new ModifyLib(patron.getuName(),patron.getSecondName(),patron.getuAddress(),patron.getuNumber(),patron.getUsersType(),patron.getuId());
                modify.show(getFragmentManager(),"modify");
            }
        });
        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(AdminLib.this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setCancelable(true);

    }

    ListView.OnItemClickListener click = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            try {
                ad.setMessage(db.getUserInfoFull(db.getListOfLibrarians().get(i)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            index=i;
            ad.show();
        }
    });

    View.OnClickListener clAdd =(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DialogFragment add = new AddLib();
            add.show(getFragmentManager(),"add");

        }
    });

}
