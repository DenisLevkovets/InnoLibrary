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

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.04.2018.
 */

@SuppressLint("ValidFragment")
public class AddBook extends DialogFragment {
    ArrayList<Books> b;
    ArrayAdapter<String> adapter;

    DataBaseHelper db;
    EditText title;
    EditText authors;
    EditText edition;
    EditText date;
    EditText editor;
    EditText price;
    EditText count;
    EditText keywords;
    RadioButton bestseller;
    RadioButton reference;
    int ref=0;
    int bs=0;
    String buttonText;
    int action;
    public AddBook(String button,int action){
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
        View v = inflater.inflate(R.layout.dialog_addbook , null);

        builder.setView(v);

        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        edition=(EditText) v.findViewById(R.id.edition);
        date=(EditText) v.findViewById(R.id.date);
        editor=(EditText) v.findViewById(R.id.editor);
        bestseller=(RadioButton) v.findViewById(R.id.bestseller);
        price=(EditText) v.findViewById(R.id.price);
        count=(EditText) v.findViewById(R.id.count);
        keywords=(EditText) v.findViewById(R.id.keywords);
        reference=(RadioButton) v.findViewById(R.id.reference);
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=(ref+1)%2;
            }
        });
        bestseller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bs=(bs+1)%2;
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
                            db.addB(title.getText().toString(), authors.getText().toString(), Integer.parseInt(count.getText().toString()),
                                    ref, Integer.parseInt(price.getText().toString()), Integer.parseInt(edition.getText().toString()),
                                    date.getText().toString(), editor.getText().toString(), keywords.getText().toString(), bs);
                        } else if(action==1){
                            try {
                                String[] arr;
                                b=db.getUser().searchBook(title.getText().toString(), authors.getText().toString(),ref,price.getText().toString(),
                                         edition.getText().toString(),date.getText().toString(),
                                        editor.getText().toString(), keywords.getText().toString(), bs, getContext());

                                if (b != null) {
                                    arr = new String[b.size()];
                                    for (int i = 0; i < b.size(); i++) {
                                        arr[i] = db.getShortInformation(b.get(i));
                                    }
                                    adapter = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, arr);
                                    ((ListView)getActivity().findViewById(R.id.list)).setAdapter(adapter);
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddBook.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
