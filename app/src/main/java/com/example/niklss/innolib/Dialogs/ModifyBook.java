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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;

/**
 * Created by user on 02.04.2018.
 */

@SuppressLint("ValidFragment")
public class ModifyBook extends DialogFragment {
    DataBaseHelper db;
    int bid;
    EditText title;
    EditText authors;
    EditText edition;
    EditText date;
    EditText editor;
    EditText keywords;
    EditText price;
    EditText count;
    RadioButton reference;
    RadioButton bstslr;
    int ref=0;
    int bst=0;


    String stitle;
    String sauthors;
    int sedition;
    String sdate;
    String seditor;
    String skeywords;
    int iprice;
    int iref;
    int ibst;
    int icount;


    public ModifyBook(String atitle, String aauthors, int aedition, String adate, String aeditor, String akeywords, int aprice, int aref, int abst, int aid){
        stitle=atitle;
        sauthors=aauthors;
        sedition=aedition;
        sdate=adate;
        seditor=aeditor;
        skeywords=akeywords;
        iprice=aprice;
        iref=aref;
        ibst=abst;
        bid=aid;
    }
    @SuppressLint("ValidFragment")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addbook, null);
        builder.setView(v);
        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        edition=(EditText) v.findViewById(R.id.edition);
        date=(EditText) v.findViewById(R.id.date);
        editor=(EditText) v.findViewById(R.id.editor);
        keywords=(EditText) v.findViewById(R.id.keywords);
        price=(EditText) v.findViewById(R.id.price);
        count=(EditText) v.findViewById(R.id.count);
        reference=(RadioButton) v.findViewById(R.id.reference);
        bstslr=(RadioButton) v.findViewById(R.id.bestseller);

        if(iref==1) reference.toggle();
        if (ibst==1) bstslr.toggle();
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=(iref+1)%2;
            }
        });

        bstslr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bst=(ibst+1)%2;
            }
        });


        title.setText(stitle);
        authors.setText(sauthors);
        edition.setText(String.valueOf(sedition));
        date.setText(sdate);
        editor.setText(seditor);
        keywords.setText(skeywords);
        price.setText(String.valueOf(iprice));
        count.setText(String.valueOf(icount));


        try {
            db = new DataBaseHelper(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder
                // Add action buttons
                .setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        db.updateBook(bid,title.getText().toString(),authors.getText().toString(),Integer.parseInt(count.getText().toString()),
                                ref, Integer.parseInt(price.getText().toString()),Integer.parseInt(edition.getText().toString()),
                                date.getText().toString(),editor.getText().toString(),keywords.getText().toString(),bst);
                        Toast.makeText(getContext(),"Modified",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyBook.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
