package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class CatalogsLib extends Activity   {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cataloglib);
        DataBaseHelper db=null;

        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();  //now we will fill names by our own, but then we will use database methods
        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
        ArrayList<String> children3 = new ArrayList<String>();

        ArrayList<Books> book= db.getListOfBooks();
        for (int i = 0; i < book.size(); i++) {
                books.add(db.getShortInformation(book.get(i)));
        }

        groups.add(books);
        children2.add("Article_1");
        children2.add("Article_2");
        children2.add("Article_3");
        groups.add(children2);
        children3.add("AVM_1");
        groups.add(children3);

        ExpListAdapter adapter = new ExpListAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);
    }
}
