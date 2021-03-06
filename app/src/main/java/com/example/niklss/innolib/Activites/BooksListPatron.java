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

import java.io.FileNotFoundException;
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


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booklistpatron);


        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }



        ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();  //now we will fill names by our own, but then we will use database methods
        ArrayList<String> checkouted = new ArrayList<String>();
        ArrayList<String> inqueue = new ArrayList<String>();
        ArrayList<String> fine = new ArrayList<String>();


        try {
            book= db.returnListOfUsersBook(db.getUser().getuId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < book.size(); i++) {
            if(book.get(i).isOverDue()==true) fine.add(db.getShortInformation(book.get(i)));
            else checkouted.add(db.getShortInformation(book.get(i)));
        }

        try {
            article = db.returnListOfUsersArticles(db.getUser().getuId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < article.size(); i++) {
            if(article.get(i).isOverDue()==true ) fine.add(db.getArticleInfoShort(article.get(i)));
            else checkouted.add(db.getArticleInfoShort(article.get(i)));
        }

        try {
            Av = db.returnListOfUsersAv(db.getUser().getuId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Av.size(); i++) {
            if(Av.get(i)==null) fine.add(db.getAVInfoShort(Av.get(i)));
            else checkouted.add(db.getAVInfoShort(Av.get(i)));
        }

        ArrayList<Books> booksfine= null;
        ArrayList<Articles> articlesfine=null;
        ArrayList<AV> avfine=null;
        try {
            booksfine = db.waitingListOfBooks(db.getUser().getuId());
            articlesfine=db.waitingListOfArticles(db.getUser().getuId());
            avfine=db.waitingListOfAV(db.getUser().getuId());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < booksfine.size(); i++) {
            fine.add(db.getShortInformation(booksfine.get(i)));
        }
        for (int i = 0; i < articlesfine.size(); i++) {
            fine.add(db.getArticleInfoShort(articlesfine.get(i)));
        }
        for (int i = 0; i < avfine.size(); i++) {
            fine.add(db.getAVInfoShort(avfine.get(i)));
        }


        groups.add(checkouted);
        groups.add(inqueue);
        groups.add(fine);

        ExpListAdapterBooks adapter = new ExpListAdapterBooks(getApplicationContext(), groups);
        listView.setAdapter(adapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(getApplicationContext(),"okay",Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 0:
                        if(i1<book.size()){

                            clickCheckouted(book.get(i1),null,null,0);
                            ad.setMessage(db.getFullInformation(book.get(i1))).show();
                        }
                        else if(i1<book.size()+article.size()){

                            clickCheckouted(null,article.get(i1-book.size()),null,1);
                            ad.setMessage(db.getArticleInfoFull(article.get(i1-book.size()))).show();
                        }
                        else{

                            clickCheckouted(null,null,Av.get(i1-book.size()-article.size()),2);
                            ad.setMessage(db.getAVInfoFull(Av.get(i1-book.size()-article.size()))).show();
                        }
                        break;
                    case 1:
                        clickQueue(article.get(i1).getArticleId());
                        ad.setMessage(db.getArticleInfoFull(article.get(i1))).show();
                        break;
                    case 2:
                        clickFine(Av.get(i1).getAvId());
                        ad.setMessage(db.getAVInfoFull(Av.get(i1))).show();
                        break;
                }
                return false;
            }
        });
    }


    public void clickCheckouted(Books book, Articles article, AV avm , int type) {
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Checkouted");

        ad.setPositiveButton("Renew", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (type){
                    case 0:
                        try {
                            db.getUser().renewBook(book,BooksListPatron.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1:
                        try {
                            db.getUser().renewArticle(article,BooksListPatron.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            db.getUser().renewAv(avm,BooksListPatron.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
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

    public void clickQueue(final int id){
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Queue");

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });


        ad.setCancelable(true);
    }





    public void clickFine(final int id){
        ad = new AlertDialog.Builder(BooksListPatron.this).setTitle("Fine");

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });


        ad.setCancelable(true);
    }






}
