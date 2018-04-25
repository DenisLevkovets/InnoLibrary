package com.example.niklss.innolib.Classes;


import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Librarian1 extends UserCard {
    public Librarian1(String name, String secondName, String adress, int id, String num, int status) throws FileNotFoundException {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian1(String[] a) throws FileNotFoundException {
        super(a);
    }

    public Librarian1(UserCard a) throws FileNotFoundException {
        super(a.getuName(), a.getSecondName(), a.getuAddress(), a.getuId(), a.getuNumber(), a.getUsersType());
    }

    public void modifyBook(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.updateBookData(book);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " modifiedBook " + book.getBookId());
    }

    public void modifyArticle(Articles article, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.updateArticleData(article);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " modifiedArticle " + article.getArticleId());
    }

    public void modifyAv(AV av, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        db.updateAvData(av);
        String[] buf = cal.getTime().toString().split(" ");
        String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
        db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " modifiedAV " + av.getAvId());
    }

    public void modifyPatron(Patron user, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Calendar cal = new GregorianCalendar();
        if (user.getUsersType() < 5) {
            db.updateUserData(user);
            String[] buf = cal.getTime().toString().split(" ");
            String time = buf[2] + "." + month(buf[1]) + "." + buf[5];
            db.out(time + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + " modifiedPatron " + user.getuId());
        }
    }

//    public void search(String input){
//        AutomatonQuery query = new AutomatonQuery();
//    }

    int month(String mm) {
        switch (mm) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return 0;
        }
    }
}
