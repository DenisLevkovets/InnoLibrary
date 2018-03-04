package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 * First activity
 */

public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bt = (Button) findViewById(R.id.button);
        EditText name = (EditText) findViewById(R.id.editText);
        EditText password = (EditText) findViewById(R.id.editText2);

        bt.setOnClickListener(click);

        DataBaseHelper db = new DataBaseHelper(Login.this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = db.getArrayUser(4);
        Patron a = new Patron(arr);

        ArrayList<Books> m = a.getAvailiableBooks(this);
        System.out.println(m.get(0).getCountOfBooks());
        a.checkOut(m.get(0).getBookId(), this);
        m = a.getAvailiableBooks(this);
        System.out.println(m.get(0).getCountOfBooks());


    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Login.this, InnoLib.class);
            startActivity(intent);
        }
    };
}
