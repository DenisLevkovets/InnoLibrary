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
import com.example.niklss.innolib.Dialogs.AddUser;
import com.example.niklss.innolib.Dialogs.ModifyUser;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 28.02.2018.
 */

public class UsersLib extends Activity {
    AlertDialog.Builder ad;
    DataBaseHelper db;
    int index;
    Patron patron;
    ArrayList<Patron> users;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userslib);
        ListView list=(ListView) findViewById(R.id.list);


        try {
            db= new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            users= db.getListOfUsers();
            patron=db.getUser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] arr=new String[users.size()];

        for (int i = 0; i < users.size(); i++) {
            arr[i]=users.get(i).getuName()+" "+users.get(i).getSecondName();
        }

        FloatingActionButton add= (FloatingActionButton) findViewById(R.id.add);
        if(patron.getUsersType()==5) add.hide();


        add.setOnClickListener(clAdd);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(UsersLib.this).setTitle("Action with User");

        if(patron.getUsersType()>=5) {
            ad.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Patron patron = users.get(index);
                    DialogFragment modify = new ModifyUser(patron.getuName(), patron.getSecondName(), patron.getuAddress(), patron.getuNumber(), patron.getUsersType(), patron.getuId());
                    modify.show(getFragmentManager(), "modify");
                }
            });
            if(patron.getUsersType()==7) {
                ad.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UsersLib.this, "It was deleted" + index, Toast.LENGTH_SHORT).show();
                        db.deletePatron(users.get(index).getuId());
                    }
                });
            }
        }
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
            try {
                ad.setMessage(db.getUserInfoFull(db.getListOfUsers().get(i)));
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
            DialogFragment add = new AddUser();
            add.show(getFragmentManager(),"add");

        }
    });

}
