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
public class ModifyArticle extends DialogFragment {
    DataBaseHelper db;
    int idd;
    EditText title;
    EditText authors;
    EditText jtitle;
    EditText issue;
    EditText date;
    EditText editor;
    EditText keywords;
    EditText price;
    EditText count;
    RadioButton reference;
    int ref=0;


    String stitle;
    String sauthors;
    String sjtitle;
    String sissue;
    String sdate;
    String seditor;
    String skeywords;
    int iprice;
    int iref;
    int icount;


    public ModifyArticle(String atitle, String aauthors, String ajtitle, String aissue, String adate, String aeditor, String akeywords, int aprice, int aref, int acount, int aid){
        stitle=atitle;
        sauthors=aauthors;
        sjtitle=ajtitle;
        sdate=adate;
        sissue=aissue;
        seditor=aeditor;
        skeywords=akeywords;
        icount=acount;
        iprice=aprice;
        iref=aref;
        idd=aid;
    }
    @SuppressLint("ValidFragment")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addarticle, null);
        builder.setView(v);
        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        jtitle=(EditText) v.findViewById(R.id.jtitle);
        issue=(EditText) v.findViewById(R.id.issue);
        date=(EditText) v.findViewById(R.id.date);
        editor=(EditText) v.findViewById(R.id.editor);
        keywords=(EditText) v.findViewById(R.id.keywords);
        price=(EditText) v.findViewById(R.id.price);
        count=(EditText) v.findViewById(R.id.count);
        reference=(RadioButton) v.findViewById(R.id.reference);
        if (iref==1) reference.toggle();
        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref=(iref+1)%2;
            }
        });


        title.setText(stitle);
        authors.setText(sauthors);
        jtitle.setText(sjtitle);
        issue.setText(sissue);
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
                        db.updateArticle(idd,title.getText().toString(),authors.getText().toString(),jtitle.getText().toString(),issue.getText().toString(),
                                date.getText().toString(),editor.getText().toString(),Integer.parseInt(count.getText().toString()),iref,keywords.getText().toString(),Integer.parseInt(price.getText().toString()));
                        Toast.makeText(getContext(),"Modified",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyArticle.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
