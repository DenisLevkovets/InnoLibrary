package com.example.niklss.innolib.Activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.niklss.innolib.R;

/**
 * Created by user on 04.02.2018.
 */

public class Login extends Activity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
<<<<<<< HEAD



=======
>>>>>>> 892a6f1a3e1234937369bdaef29ecb2e65b5e478
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
