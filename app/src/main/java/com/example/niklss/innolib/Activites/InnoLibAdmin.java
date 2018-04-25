package com.example.niklss.innolib.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.R;

/**
 * Created by user on 22.04.2018.
 */

public class InnoLibAdmin  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        Button libs = (Button) findViewById(R.id.libs);
        Button log = (Button) findViewById(R.id.log);

        libs.setOnClickListener(clLibs);
        log.setOnClickListener(clLog);

    }

    View.OnClickListener clLibs = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InnoLibAdmin.this, AdminLib.class);
            startActivity(intent);
        }
    };

    View.OnClickListener clLog = (new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(InnoLibAdmin.this,AdminLog.class);
            startActivity(intent);
        }
    });


}
