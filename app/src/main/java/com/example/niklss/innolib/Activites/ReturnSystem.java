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

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.03.2018.
 */

public class ReturnSystem extends Activity {
    AlertDialog.Builder ad;
    DataBaseHelper db;
    ArrayList<Patron> deptors;
    ArrayList<Books> books;
    ArrayList<Articles> articles;
    ArrayList<AV> avm;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returnsystem);
        ListView list=(ListView) findViewById(R.id.list);

        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            deptors =db.debtorUsers();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] arr=new String[deptors.size()];
        for (int i = 0; i < deptors.size(); i++) {
            arr[i]=db.getUserInfoShort(deptors.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        list.setAdapter(adapter);

        list.setOnItemClickListener(click);


        ad = new AlertDialog.Builder(ReturnSystem.this).setTitle("Choose document");
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


    }

    ListView.OnItemClickListener click = (new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            try {
                books=deptors.get(i).getListOfUsersBook(ReturnSystem.this);
                articles=deptors.get(i).getListOfUsersArticles(ReturnSystem.this);
                avm=deptors.get(i).getListOfUsersAv(ReturnSystem.this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String res[]=new String[books.size()+articles.size()+avm.size()];
            for (int j = 0; j < books.size(); j++) {
                res[j]=db.getShortInformation(books.get(j));
            }
            for (int j = books.size(); j < books.size()+articles.size(); j++) {
                res[j]=db.getArticleInfoShort(articles.get(j-books.size()));
            }
            for (int j = books.size()+articles.size(); j <books.size()+articles.size()+ avm.size(); j++) {
                res[j]=db.getAVInfoShort(avm.get(j-books.size()-articles.size()));
            }
            ad.setItems(res,switchh);
            ad.setCancelable(true);
            ad.show();
        }
    });

    DialogInterface.OnClickListener switchh=(new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            AlertDialog.Builder adb=new AlertDialog.Builder(ReturnSystem.this).setTitle("Return");
            if(i<books.size()){
                adb.setMessage(db.getShortInformation(books.get(i)));
            }
            else if(i<books.size()+articles.size()){
                adb.setMessage(db.getArticleInfoShort(articles.get(i-books.size())));
            }
            else{
                adb.setMessage(db.getAVInfoShort(avm.get(i-books.size()-articles.size())));
            }

            adb.setPositiveButton("Return", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int j) {
                    try {
                        if (i < books.size()) {
                            db.returnBook(db.getUser().getuId(), books.get(i).getBookId());
                            Toast.makeText(ReturnSystem.this, "Book was returned", Toast.LENGTH_SHORT).show();
                        } else if (i < books.size() + articles.size()) {
                            db.returnArticle(db.getUser().getuId(), articles.get(i - books.size()).getArticleId());
                            Toast.makeText(ReturnSystem.this, "Article was returned", Toast.LENGTH_SHORT).show();
                        } else {
                            db.returnAV(db.getUser().getuId(), avm.get(i - books.size() - articles.size()).getAvId());
                            Toast.makeText(ReturnSystem.this, "AVM was returned", Toast.LENGTH_SHORT).show();
                        }
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }


                }
            }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).show();
        }
    });
}
