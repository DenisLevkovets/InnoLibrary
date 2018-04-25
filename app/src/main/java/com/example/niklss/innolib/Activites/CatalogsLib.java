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
import com.example.niklss.innolib.Dialogs.AddAVM;
import com.example.niklss.innolib.Dialogs.AddArticle;
import com.example.niklss.innolib.Dialogs.AddBook;
import com.example.niklss.innolib.Dialogs.ModifyAV;
import com.example.niklss.innolib.Dialogs.ModifyArticle;
import com.example.niklss.innolib.Dialogs.ModifyBook;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class CatalogsLib extends Activity   {
    AlertDialog.Builder ad;
    AlertDialog.Builder ask;

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
        try {
            if(db.getUser().getUsersType()==5) fb.hide();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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
                try {
                    switch (i) {
                        case 0:
                            clickBook(book.get(i1));
                            ad.setMessage(db.getFullInformation(book.get(i1))).show();
                            break;
                        case 1:
                            clickArticle(article.get(i1));
                            ad.setMessage(db.getArticleInfoFull(article.get(i1))).show();
                            break;
                        case 2:
                            clickAv(av.get(i1));
                            ad.setMessage(db.getAVInfoFull(av.get(i1))).show();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
    }



    /////////////////////////////////////////////////////////////////////////////////////////////

    public void clickBook(Books book) throws FileNotFoundException {
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("Book");
        ad.setCancelable(true);

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                DialogFragment modify=new ModifyBook(book.getTitleBook(),book.getAuthorsOfBook(),book.getEdition(),
                        book.getDateOfCreationOfBook(),book.getPublished_by(),book.getKeywords(),book.getPrice(),book.getReference(),
                        book.getIsBestSeller(),book.getBookId());
                modify.show(getFragmentManager(),"modify");
            }
        });
        if(db.getUser().getUsersType()>5) {
            ad.setNegativeButton("Out. Req.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.outstanding_request(book.getBookId(), 0);
                    Toast.makeText(CatalogsLib.this, "Accepted", Toast.LENGTH_SHORT).show();
                }
            });
        }
        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteBook(book.getBookId());
                Toast.makeText(CatalogsLib.this,"Deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void clickAv(AV av) throws FileNotFoundException {
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("AVM");
        ad.setCancelable(true);

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DialogFragment modify=new ModifyAV(av.getTitle(),av.getAuthors(),av.getCountAv(),av.getKeywords(),av.getPrice(),av.getAvId());
                modify.show(getFragmentManager(),"modify");
            }
        });
        if(db.getUser().getUsersType()>5) {
            ad.setNegativeButton("Out. Req.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.outstanding_request(av.getAvId(), 2);
                    Toast.makeText(CatalogsLib.this, "Accepted", Toast.LENGTH_SHORT).show();

                }
            });
        }
        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteAV(av.getAvId());
                Toast.makeText(CatalogsLib.this,"Deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }



 //////////////////////////////////////////////////////////////////////////////////////////////////

    public void clickArticle(Articles article) throws FileNotFoundException {
        ad = new AlertDialog.Builder(CatalogsLib.this).setTitle("Article");
        ad.setCancelable(true);

        ad.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DialogFragment modify=new ModifyArticle(article.getTitle(),article.getAuthors(),article.getJtitle(),article.getIssue(),
                        article.getDate(),article.getEditor(),article.getKeywords(),article.getPrice(),article.getReference(),article.getCountArticle(),article.getArticleId());
                modify.show(getFragmentManager(),"modify");
            }
        });
        if (db.getUser().getUsersType() > 5) {

            ad.setNegativeButton("Out. Req.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.outstanding_request(article.getArticleId(), 1);
                    Toast.makeText(CatalogsLib.this, "Accept", Toast.LENGTH_SHORT).show();
                }
            });
        }
        ad.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteArticle(article.getArticleId());
                Toast.makeText(CatalogsLib.this,"Deleted",Toast.LENGTH_SHORT).show();
            }
        });

    }






/////////////////////////////////////////////////////////////////////////////////////////


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

           ask.setCancelable(true).setItems(data, (DialogInterface.OnClickListener) switchh).show();

        }
    });

    DialogInterface.OnClickListener switchh=(new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case 0:
                    DialogFragment book=new AddBook("Create",0);
                    book.show(getFragmentManager(),"a");
                    break;
                case 1:
                    DialogFragment article=new AddArticle("Create",0);
                    article.show(getFragmentManager(),"b");
                    break;
                case 2:
                    DialogFragment avm=new AddAVM("Create",0);
                    avm.show(getFragmentManager(),"c");
                    break;
            }
        }
    });

}
