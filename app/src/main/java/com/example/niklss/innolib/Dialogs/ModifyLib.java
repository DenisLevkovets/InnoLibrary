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
import android.widget.RadioGroup;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;

/**
 * Created by user on 02.04.2018.
 */

@SuppressLint("ValidFragment")
public class ModifyLib extends DialogFragment {
    DataBaseHelper db;
    EditText name;
    EditText surname;
    EditText address;
    EditText number;
    RadioButton first;
    RadioButton second;
    RadioButton theird;
    RadioGroup rg;
    int status=-1;
    int sid;
    String sname;
    String ssurname;
    String saddress;
    String snumber;


    public ModifyLib(String lname, String lsurname, String laddress, String lnumber, int lstatus,int lid){
        sname=lname;
        ssurname=lsurname;
        saddress=laddress;
        snumber=lnumber;
        status=lstatus;
        sid=lid;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_addlibrarians, null);
        builder.setView(v);
        name=(EditText) v.findViewById(R.id.name);
        surname=(EditText) v.findViewById(R.id.surname);
        address=(EditText) v.findViewById(R.id.address);
        number=(EditText) v.findViewById(R.id.number);

        name.setText(sname);
        surname.setText(ssurname);
        address.setText(saddress);
        number.setText(snumber);

        first=(RadioButton) v.findViewById(R.id.first);
        second=(RadioButton) v.findViewById(R.id.second);
        theird=(RadioButton) v.findViewById(R.id.theird);

        if(status==5) first.toggle();
        else if(status==6) second.toggle();
        else if(status==7) theird.toggle();

        rg=(RadioGroup) v.findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.first:
                        status=5;
                        break;
                    case R.id.second:
                        status=6;
                        break;
                    case R.id.theird:
                        status=7;
                        break;

                }
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
                .setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        db.updateUser(sid,name.getText().toString(),surname.getText().toString(),
                                address.getText().toString(),number.getText().toString(),status);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyLib.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
