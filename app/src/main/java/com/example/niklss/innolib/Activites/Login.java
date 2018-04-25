package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    EditText name;
    EditText password;
    TextView tx;
    DataBaseHelper db=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bt = (Button) findViewById(R.id.button);
        name = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        tx=(TextView) findViewById(R.id.text);
        bt.setOnClickListener(click);

        try {
            db=new DataBaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int status=0;
            Log.i("strange","verystrange");
            try {
                status=db.Login(name.getText().toString(),password.getText().toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Intent intent=null;
            if (status>=5 && status<=7) {
                tx.setText("");
                intent = new Intent(Login.this, InnoLibLib.class);
                startActivity(intent);

            } else if (status==8){
                tx.setText("");
                intent = new Intent(Login.this,InnoLibAdmin.class);
                startActivity(intent);
            }
            else if(status>=0 && status<=4) {
                tx.setText("");
                intent = new Intent(Login.this, InnoLibPatron.class);
                startActivity(intent);
            }
            else if(status==-1){
                tx.setText("Wrong login or password!");
            }

        }
    };
}
