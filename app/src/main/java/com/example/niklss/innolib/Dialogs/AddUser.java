package com.example.niklss.innolib.Dialogs;

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

public class AddUser extends DialogFragment {
    DataBaseHelper db;
    EditText name;
    EditText surname;
    EditText address;
    EditText number;
    RadioButton ta;
    RadioButton student;
    RadioButton prof;
    RadioButton vp;
    RadioButton instr;
    RadioGroup rg;
    int status=-1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_adduser, null);
        builder.setView(v);
        name=(EditText) v.findViewById(R.id.name);
        surname=(EditText) v.findViewById(R.id.surname);
        address=(EditText) v.findViewById(R.id.address);
        number=(EditText) v.findViewById(R.id.number);
        ta=(RadioButton) v.findViewById(R.id.ta);
        student=(RadioButton) v.findViewById(R.id.student);
        prof=(RadioButton) v.findViewById(R.id.prof);
        instr=(RadioButton) v.findViewById(R.id.instr);
        vp=(RadioButton) v.findViewById(R.id.vp);

        rg=(RadioGroup) v.findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.student:
                        status=0;
                        break;
                    case R.id.instr:
                        status=1;
                        break;
                    case R.id.ta:
                        status=2;
                        break;
                    case R.id.vp:
                        status=3;
                        break;
                    case R.id.prof:
                        status=4;
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
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        AlertDialog.Builder cr=new AlertDialog.Builder(getContext()).setTitle("New user").setMessage("Login: "+name.getText().toString()+""+surname.getText().toString()+"\n"+
                                "Password: "+ db.createUser(name.getText().toString(),surname.getText().toString(),
                                number.getText().toString(),address.getText().toString(),status));
                        cr.show();


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddUser.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
