package com.example.niklss.innolib.Activites;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.niklss.innolib.Dialogs.AddAVM;
import com.example.niklss.innolib.Dialogs.AddArticle;
import com.example.niklss.innolib.Dialogs.AddBook;
import com.example.niklss.innolib.R;

/**
 * Created by user on 24.04.2018.
 */

public class Search extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        FloatingActionButton fb=(FloatingActionButton) findViewById(R.id.search);
        fb.setOnClickListener(clSearch);
    }

    View.OnClickListener clSearch=(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder choise=new AlertDialog.Builder(Search.this).setTitle("What do you want to search?");
            String[] data={"Books","Articles","AVMs"};
            choise.setItems(data, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            DialogFragment book=new AddBook("Find",1);
                            book.show(getFragmentManager(),"find");
                            break;
                        case 1:
                            DialogFragment article=new AddArticle("Find",1);
                            article.show(getFragmentManager(),"find");
                            break;
                        case 2:
                            DialogFragment avm=new AddAVM("Find",1);
                            avm.show(getFragmentManager(),"find");
                            break;
                    }

                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Search.this,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    });
}