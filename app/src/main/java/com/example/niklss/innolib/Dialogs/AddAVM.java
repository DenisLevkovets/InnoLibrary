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

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.04.2018.
 */
@SuppressLint("ValidFragment")
public class AddAVM extends DialogFragment {
    ArrayList<AV> av;
    ArrayAdapter<String> adapter;
    DataBaseHelper db;
    EditText title;
    EditText authors;
    EditText count;
    EditText keywords;
    EditText price;
    String buttonText;
    int action;


    public AddAVM(String button, int action){
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
        View v = inflater.inflate(R.layout.dialog_addavm , null);
        builder.setView(v);

        title=(EditText) v.findViewById(R.id.title);
        authors=(EditText) v.findViewById(R.id.authors);
        count=(EditText) v.findViewById(R.id.count);
        keywords=(EditText) v.findViewById(R.id.keywords);
        price=(EditText) v.findViewById(R.id.price);


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
            if (action==0) {
                db.addAV(title.getText().toString(), authors.getText().toString(), Integer.parseInt(count.getText().toString()),
                        keywords.getText().toString(), Integer.parseInt(price.getText().toString()));
            }else if(action==1){
                try {
                    av=db.getUser().searchAv(title.getText().toString(), authors.getText().toString(),
                            keywords.getText().toString(), price.getText().toString(),getContext());
                    String[] arr;
                    if (av != null) {
                        arr = new String[av.size()];
                        for (int i = 0; i < av.size(); i++) {
                            arr[i] = db.getAVInfoShort(av.get(i));
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
            AddAVM.this.getDialog().cancel();
        }
    });
        return builder.create();
}
}
