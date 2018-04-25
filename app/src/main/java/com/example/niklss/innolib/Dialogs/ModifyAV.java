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
import android.widget.Toast;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;

/**
 * Created by user on 04.04.2018.
 */

@SuppressLint("ValidFragment")
public class ModifyAV extends DialogFragment {
    int idd;
    DataBaseHelper db;
    EditText title;
    EditText authors;
    EditText count;
    EditText keywords;
    EditText price;

    String stitle;
    String sauthors;
    String skeywords;
    int icount;
    int iprice;

    public ModifyAV(String atitle, String aauthors, int acount, String akeywords, int aprice, int aid){
        stitle=atitle;
        sauthors=aauthors;
        icount=acount;
        skeywords=akeywords;
        iprice=aprice;
        idd=aid;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addavm , null);
        builder.setView(v);

        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        count=(EditText) v.findViewById(R.id.count);
        keywords=(EditText) v.findViewById(R.id.keywords);
        price=(EditText) v.findViewById(R.id.price);


        title.setText(stitle);
        authors.setText(sauthors);
        count.setText(String.valueOf(icount));
        keywords.setText(skeywords);
        price.setText(String.valueOf(iprice));


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
                        db.updateAV(idd,title.getText().toString(),authors.getText().toString(),Integer.parseInt(count.getText().toString()),
                                keywords.getText().toString(),Integer.parseInt(price.getText().toString()));
                        Toast.makeText(getContext(),"Modified",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyAV.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
