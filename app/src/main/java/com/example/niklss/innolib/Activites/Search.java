package com.example.niklss.innolib.Activites;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.Dialogs.AddAVM;
import com.example.niklss.innolib.Dialogs.AddArticle;
import com.example.niklss.innolib.Dialogs.AddBook;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Search extends AppCompatActivity {
    DataBaseHelper db;
    ListView lv;
    AlertDialog.Builder ad;
    int type=-1;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        try {
            db=new DataBaseHelper(Search.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lv=(ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(clList);



        FloatingActionButton fb=(FloatingActionButton) findViewById(R.id.search);
        fb.setOnClickListener(clSearch);
    }

    View.OnClickListener clSearch=(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder choise=new AlertDialog.Builder(Search.this).setTitle("What do you want to search?");
            String[] data={"Books","Articles","AVMs"};
            AlertDialog show = choise.setItems(data, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            case 0:
                                DialogFragment book = new AddBook("Find", 1);
                                book.show(getFragmentManager(), "find");
                                type=0;

                                break;
                            case 1:
                                DialogFragment article = new AddArticle("Find", 1);
                                article.show(getFragmentManager(), "find");
                                type=1;

                                break;
                            case 2:
                                DialogFragment avm = new AddAVM("Find", 1);
                                avm.show(getFragmentManager(), "find");
                                type=2;

                                break;
                        }
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Search.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    });

    AdapterView.OnItemClickListener clList=(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            if(type==0){clickBook(1);}
            else if(type==1){clickArticle(1);}
            else if(type==2){clickAv(1);}
        }
    });

    public void clickBook(final int id) {
        ad = new AlertDialog.Builder(Search.this).setTitle("Book");
        ad.setCancelable(true);

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Patron patron = null;
                try {
                    patron = db.getUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    patron.checkOut(id, Search.this);
//                    String res=patron.checkOut(id,CatalogsPatron.this);
//                    Toast.makeText(CatalogsPatron.this,res,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void clickAv(final int id){
        ad = new AlertDialog.Builder(Search.this).setTitle("AVM");
        ad.setCancelable(true);

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Patron patron= null;
                try {
                    patron = db.getUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    patron.checkOutAV(id,Search.this);
//                    String res=patron.checkOutAV(id,CatalogsPatron.this);
//                    Toast.makeText(CatalogsPatron.this,res,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT) .show();
            }
        });



    }





    public void clickArticle(final int id) {
        ad = new AlertDialog.Builder(Search.this).setTitle("Article");
        ad.setCancelable(true);

        ad.setPositiveButton("Checkout", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Patron patron = null;
                try {
                    patron = db.getUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    patron.checkOutArticle(id, Search.this);
//                    String res=patron.checkOutArticle(id,CatalogsPatron.this);
//                    Toast.makeText(CatalogsPatron.this,res,Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ad.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }

}