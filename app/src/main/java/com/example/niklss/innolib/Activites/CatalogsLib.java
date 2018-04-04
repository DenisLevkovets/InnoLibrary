package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.Dialogs.AddBook;
import com.example.niklss.innolib.Dialogs.ModifyArticle;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class CatalogsLib extends Activity   {
    AlertDialog.Builder ad;
    AlertDialog.Builder ask;
    AlertDialog.Builder add;

    DataBaseHelper db;
    ArrayList<Books>  book;
    ArrayList<Articles> article;
    ArrayList<AV> av;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cataloglib);


        try {
            db = new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }


        FloatingActionButton fb=(FloatingActionButton) findViewById(R.id.add);
        fb.setOnClickListener(clickFb);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.elv);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();  //now we will fill names by our own, but then we will use database methods
        ArrayList<String> books = new ArrayList<String>();
        ArrayList<String> artricles = new ArrayList<String>();
        ArrayList<String> AVs = new ArrayList<String>();

        book= db.getListOfBooks();
        for (int i = 0; i < book.size(); i++) {
                books.add(db.getShortInformation(book.get(i)));
        }

        article = db.getListOfArticles();
        for (int i = 0; i < article.size(); i++) {
            artricles.add(db.getArticleInfoShort(article.get(i)));
        }

        av = db.getListOfAV();
        for (int i = 0; i < av.size(); i++) {
            AVs.add(db.getAVInfoShort(av.get(i)));
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
                        break;
                    case 1:
                        clickArticle(article.get(i1).getArticleId());
                        break;
                    case 2:
                        clickAv(av.get(i1).getAvId());
                }
                ad.show();
                return false;
            }
        });
    }


    public void clickBook(final int id){
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("Book");

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Modify",Toast.LENGTH_SHORT) .show();
            }
        });

        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });

        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteBook(id);
            }
        });
        ad.setCancelable(true);
    }

    public void clickAv(final int id){
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("Book");

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Modify",Toast.LENGTH_SHORT) .show();
            }
        });

        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });

        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteAV(id);
            }
        });
        ad.setCancelable(true);
    }





    public void clickArticle(final int id){
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("Book");

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DialogFragment modify =new ModifyArticle(0);
            }
        });

        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });

        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteArticle(id);
            }
        });
        ad.setCancelable(true);
    }

    View.OnClickListener clickFb=(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String data[]={"Book","Article","AVM"};
           ask=new AlertDialog.Builder(CatalogsLib.this).setTitle("Which type?")
                   .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   });

           ask.setCancelable(true).setSingleChoiceItems(data,-1, (DialogInterface.OnClickListener) switchh).show();

        }
    });

    DialogInterface.OnClickListener switchh=(new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case 0:
                    DialogFragment book=new AddBook();
                    book.show(getFragmentManager(),"a");
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    });

}
