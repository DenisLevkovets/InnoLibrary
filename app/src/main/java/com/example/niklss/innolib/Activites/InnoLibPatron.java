package com.example.niklss.innolib.Activites;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.niklss.innolib.DataBase.DataBaseHelper;
import com.example.niklss.innolib.R;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by user on 02.02.2018.
 */

public class InnoLibPatron extends AppCompatActivity {

    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_patron);
        Button search = (Button) findViewById(R.id.search);
        Button catalog = (Button) findViewById(R.id.catalog);
        Button blist = (Button) findViewById(R.id.blist);
        blist.setOnClickListener(clBList);
        catalog.setOnClickListener(clCatalog);
        search.setOnClickListener(clSearch);

        try {
            db=new DataBaseHelper(InnoLibPatron.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            notification();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if(db.checkOutstandingAV(db.getUser().getuId()).size()!=0){
                notification1();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



        View.OnClickListener clCatalog = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InnoLibPatron.this, CatalogsPatron.class);
                startActivity(intent);
            }
        };

        View.OnClickListener clBList = ( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InnoLibPatron.this, BooksListPatron.class);
                startActivity(intent);
            }
        });

        View.OnClickListener clSearch=(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InnoLibPatron.this,Search.class);
                startActivity(intent);
            }
        });


        public void notification() throws FileNotFoundException{
            Intent notificationIntent = new Intent(this, CatalogsPatron.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            Resources res = this.getResources();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.inn)
                    .setContentTitle("Напоминание")
                    .setContentText("Number of fine books: "+(db.getCountOfOverDueBooks(db.returnListOfUsersBook(db.getUser().getuId()))
                            +db.getCountOfOverDueArticles(db.returnListOfUsersArticles(db.getUser().getuId()
                            +db.getCountOfOverDueAV(db.returnListOfUsersAv(db.getUser().getuId())))))+".")
                    .setAutoCancel(true);


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(101, builder.build());
        }

    public void notification1() throws FileNotFoundException{
        Intent notificationIntent = new Intent(this, CatalogsPatron.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = this.getResources();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.inn)
                .setContentTitle("Напоминание")
                .setContentText("Your order were declined")
                .setAutoCancel(true);


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(102, builder.build());
    }
}

