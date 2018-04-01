package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.Classes.UserCard;
import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bt = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        EditText password = (EditText) findViewById(R.id.editText2);
        bt.setOnClickListener(click);

//        DataBaseHelper db = null;
//        try {
//            db = new DataBaseHelper(this.getApplicationContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        db.createUser("1", "1", "1", "1", 1);
//        Patron one = db.getListOfUsers().get(db.getListOfUsers().size() - 1);
//        db.addB("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiseron, Ronald L. Rivest and Clifford Stein", 3, 0, 255, 3, "2009", "MIT press", "ee", 0);
//
//        one.checkOut(db.getListOfBooks().get(0).getBookId(), this.getApplicationContext());
//        one.checkOut(db.getListOfBooks().get(1).getBookId(), this.getApplicationContext());
//        Log.e("Mistake", "ff");
//
//        ArrayList<Books> a = db.returnListOfUsersBook(one.getuId());
//
//        for (int i = 0; i < a.size(); i++) {
//            Log.e("Books", a.get(i).getTitleBook());
//        }


    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            if (String.valueOf(name.getText()).equals("librarian")) {
                Toast.makeText(Login.this, "Good", Toast.LENGTH_LONG).show();
                intent = new Intent(Login.this, InnoLibLib.class);

            } else {
                Toast.makeText(Login.this, name.getText() + String.valueOf(name.getText().length()), Toast.LENGTH_LONG).show();
                intent = new Intent(Login.this, InnoLibPatron.class);
            }
            startActivity(intent);
        }
    };
}
