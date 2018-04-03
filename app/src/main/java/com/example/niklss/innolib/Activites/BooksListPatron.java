package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class BooksListPatron extends Activity   {
    AlertDialog.Builder ad;
    DataBaseHelper db;
    ArrayList<Books>  book;
    ArrayList<Articles> article;
    ArrayList<AV> Av;


    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogpatron);


        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }



        ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();  //now we will fill names by our own, but then we will use database methods
        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> artricles = new ArrayList<String>();
        ArrayList<String> AVs = new ArrayList<String>();

        ArrayList<String> booksfine = new ArrayList<String>();
        ArrayList<String> artriclesfine = new ArrayList<String>();
        ArrayList<String> AVsfine = new ArrayList<String>();

        book= db.returnListOfUsersBook(4);

        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).isOverDue()==true) booksfine.add(db.getShortInformation(book.get(i)));
            else books.add(db.getShortInformation(book.get(i)));
        }

        article = db.getListOfArticles();
        for (int i = 0; i < article.size(); i++) {
            if(article.get(i).isOverDue()==true ) artriclesfine.add(db.getArticleInfoShort(article.get(i)));
            else artricles.add(db.getArticleInfoShort(article.get(i)));
        }

        Av = db.getListOfAV();
        for (int i = 0; i < Av.size(); i++) {
            if(Av.get(i)==null) AVsfine.add(db.getAVInfoShort(Av.get(i)));
            else AVs.add(db.getAVInfoShort(Av.get(i)));
        }


        groups.add(books);
        groups.add(artricles);
        groups.add(AVs);

        ExpListAdapter adapter = new ExpListAdapter(getApplicationContext(), groups);
        listView.setAdapter(adapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(getApplicationContext(),"okay",Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 0:
                        clickBook(book.get(i1).getBookId());
                        ad.setMessage(db.getFullInformation(book.get(i1))).show();
                        break;
                    case 1:
                        clickArticle(article.get(i1).getArticleId());
                        ad.setMessage(db.getArticleInfoFull(article.get(i1))).show();
                        break;
                    case 2:
                        clickAv(Av.get(i1).getAvId());
                        ad.setMessage(db.getAVInfoFull(Av.get(i1))).show();
                        break;
                }
                return false;
            }
        });
    }


    public void clickBook(final int id){
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Book");

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });


        ad.setCancelable(true);
    }

    public void clickAv(final int id){
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("AV");

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });


        ad.setCancelable(true);
    }





    public void clickArticle(final int id){
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Article");

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });


        ad.setCancelable(true);
    }




}
