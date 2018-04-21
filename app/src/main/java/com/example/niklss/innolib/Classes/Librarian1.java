package com.example.niklss.innolib.Classes;


import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.IOException;

public class Librarian1 extends UserCard {

    public Librarian1(String name, String secondName, String adress, int id, String num, int status) {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian1(String[] a) {
        super(a);
    }

    public void modifyBook(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateBookData(book);
    }
//
//    public void modifyArticle(Articles article, Context context) throws IOException {
//        DataBaseHelper db = new DataBaseHelper(context);
//        db.updateArticleData(article);
//    }
//
//    public void modifyAv(AV av, Context context) throws IOException {
//        DataBaseHelper db = new DataBaseHelper(context);
//        db.updateAvData(av);
//    }

    public void modifyPatron(Patron user, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (user.getUsersType() < 5) {
            db.updateUserData(user);
        }
    }
}
