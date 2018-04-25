package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Admin;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian3;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    EditText name;
    EditText password;
    DataBaseHelper db=null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bt = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        bt.setOnClickListener(click);
        try {
            DataBaseHelper db = new DataBaseHelper(getApplicationContext());
            Patron p = new Patron(db.getArrayUser(1));
            p.checkOut(2,getApplicationContext());
            ArrayList<Books> b = new ArrayList<>();
            b=db.returnListOfUsersBook(1);
            for (int i = 0; i <b.size() ; i++) {
                System.out.println(b.get(i).getTitleBook());
            }
            System.out.println("?////");
            p.checkOut(1,getApplicationContext());
            b=db.returnListOfUsersBook(1);
            for (int i = 0; i <b.size() ; i++) {
                System.out.println(b.get(i).getTitleBook());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent;
            if (String.valueOf(name.getText()).equals("librarian")) {
                intent = new Intent(Login.this, InnoLibLib.class);

            } else {
                db.Login(name.getText().toString(),password.getText().toString());
                intent = new Intent(Login.this, InnoLibPatron.class);
            }
            startActivity(intent);
        }
    };
}
