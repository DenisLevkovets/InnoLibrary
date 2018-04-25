package com.example.niklss.innolib.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.04.2018.
 */
@SuppressLint("ValidFragment")
public class AddArticle extends DialogFragment {
    ArrayList<Articles> articles;
    ArrayAdapter<String> adapter;
    DataBaseHelper db;
    EditText title;
    EditText authors;
    EditText issue;
    EditText date;
    EditText jtitle;
    EditText editor;
    EditText count;
    EditText keywords;
    EditText price;
    RadioButton reference;
    int ref=0;
    String buttonText;
    int action;


    public AddArticle(String button, int action){
        buttonText=button;
        this.action=action;
    }
    @SuppressLint("ValidFragment")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addarticle , null);
        builder.setView(v);

        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        count=(EditText) v.findViewById(R.id.count);
        keywords=(EditText) v.findViewById(R.id.keywords);
        price=(EditText) v.findViewById(R.id.price);
        jtitle=(EditText) v.findViewById(R.id.jtitle);
        editor=(EditText) v.findViewById(R.id.editor);
        date=(EditText) v.findViewById(R.id.date);
        issue=(EditText) v.findViewById(R.id.issue);
        reference=(RadioButton) v.findViewById(R.id.reference);
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=(ref+1)%2;
            }
        });

        try {
            db = new DataBaseHelper(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder
                // Add action buttons
                .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(action==0) {
                            db.addArt(title.getText().toString(), authors.getText().toString(), jtitle.getText().toString(), issue.getText().toString(),
                                    date.getText().toString(), editor.getText().toString(), Integer.parseInt(count.getText().toString()),
                                    ref, keywords.getText().toString(), Integer.parseInt(price.getText().toString()));
                        }else if(action==1){
                            try {
                                articles=db.getUser().searchArticle(title.getText().toString(), authors.getText().toString(), jtitle.getText().toString(), issue.getText().toString(),
                                        date.getText().toString(), editor.getText().toString(),
                                        ref, keywords.getText().toString(), price.getText().toString(),getContext());


                                String[] arr;
                                if (articles != null) {
                                    arr = new String[articles.size()];
                                    for (int i = 0; i < articles.size(); i++) {
                                        arr[i] = db.getArticleInfoShort(articles.get(i));
                                    }
                                    adapter = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, arr);
                                    ((ListView)getActivity().findViewById(R.id.list)).setAdapter(adapter);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddArticle.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
