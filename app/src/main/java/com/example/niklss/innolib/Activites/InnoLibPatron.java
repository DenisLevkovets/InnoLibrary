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

        try {
            db=new DataBaseHelper(InnoLibPatron.this);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        notification();
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


        public void notification(){
            Intent notificationIntent = new Intent(this, CatalogsPatron.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            Resources res = this.getResources();

            // до версии Android 8.0 API 26
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

            builder.setContentIntent(contentIntent)
                    // обязательные настройки
                    .setSmallIcon(R.drawable.inn)
                    //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                    .setContentTitle("Напоминание")
                    //.setContentText(res.getString(R.string.notifytext))
                    .setContentText("Number of fine books: "+(db.getCountOfOverDueBooks(db.returnListOfUsersBook(db.getUser().getuId()))
                            +db.getCountOfOverDueArticles(db.returnListOfUsersArticles(db.getUser().getuId()
                            +db.getCountOfOverDueAV(db.returnListOfUsersAv(db.getUser().getuId())))))+". Fine: ")
                    .setAutoCancel(true); // Текст уведомления
                    // необязательные настройки


            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // Альтернативный вариант
            // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(101, builder.build());
        }
}

