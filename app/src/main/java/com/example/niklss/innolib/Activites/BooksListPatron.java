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

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.util.ArrayList;

/**
 * Created by user on 02.03.2018.
 */

public class BooksListPatron extends Activity {
    AlertDialog.Builder ad;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklistpatron);
        ListView list=(ListView) findViewById(R.id.list);
<<<<<<< HEAD
        String[] arr= {"1","2","3"};
=======
        DataBaseHelper db = new DataBaseHelper(this);
        ArrayList<Books> shortt = db.getListOfBooks(4);

        String[] arr= new String[shortt.size()];
        arr2 = new String[shortt.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=db.getShortInformation(shortt.get(i));
            arr2[i]=db.getFullInformation(shortt.get(i));
        }
>>>>>>> 31d999b26ae036a1bfadc4f459a6f66d6793e5f6
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Title!");


        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BooksListPatron.this,"You didnt choose",Toast.LENGTH_SHORT).show();
            }
        });
        ad.setCancelable(true);

    }

    ListView.OnItemClickListener click = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ad.setMessage("Your book");
            ad.show();
        }
    });
}
