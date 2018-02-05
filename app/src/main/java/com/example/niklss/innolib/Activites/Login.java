package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.niklss.innolib.Classes.TC1;
import com.example.niklss.innolib.DataBase.Base;
import com.example.niklss.innolib.DataBase.DbRepository;
import com.example.niklss.innolib.R;

import java.io.IOException;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        DbRepository db=new DbRepository(Login.this);
        db.createUser("Denis","Levkovets","1111","v", 0);
        Base base=new Base(Login.this  );
        try {
            base.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button bt=(Button) findViewById(R.id.button);
        EditText name=(EditText) findViewById(R.id.editText);
        EditText password=(EditText) findViewById(R.id.editText2);
        bt.setOnClickListener(click);
    }

    View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Login.this, InnoLib.class);
            startActivity(intent);
        }
    };
}
