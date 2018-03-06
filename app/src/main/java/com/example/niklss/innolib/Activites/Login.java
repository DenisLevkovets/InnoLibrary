package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bt=(Button) findViewById(R.id.button);
        name=(EditText) findViewById(R.id.editText);
        EditText password=(EditText) findViewById(R.id.editText2);

        //FSDKJGLKJHSDLGJ
        bt.setOnClickListener(click);

        DataBaseHelper db = new DataBaseHelper(this);
        db.addB("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiseron, Ronald L. Rivest and Clifford Stein", 3, 0, 255, 3, "2009", "MIT press", "ee", 0);
        System.out.println(db.getListOfBooks().get(4).getPrice());


    }

    View.OnClickListener click=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            if (String.valueOf(name.getText()).equals("librarian")){
                Toast.makeText(Login.this,"Good",Toast.LENGTH_LONG).show();
                intent = new Intent(Login.this, InnoLibLib.class);

            }
            else {
                Toast.makeText(Login.this,name.getText() + String.valueOf(name.getText().length()),Toast.LENGTH_LONG).show();
                intent = new Intent(Login.this, InnoLibPatron.class);
            }
            startActivity(intent);
        }
    };
}
