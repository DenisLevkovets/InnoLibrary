package com.example.niklss.innolib.Classes;


import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import org.apache.lucene.search.AutomatonQuery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

public class Librarian1 extends UserCard {
    private PrintWriter out = new PrintWriter(new File("Log"));

    public Librarian1(String name, String secondName, String adress, int id, String num, int status) throws FileNotFoundException {
        super(name, secondName, adress, id, num, status);
    }

    public Librarian1(String[] a) throws FileNotFoundException {
        super(a);
    }

    public void modifyBook(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateBookData(book);
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "modifiedBook " + book.getBookId());
        out.close();
    }

//    public void modifyArticle(Articles article, Context context) throws IOException {
//        DataBaseHelper db = new DataBaseHelper(context);
//        db.updateArticleData(article);
//        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "modifiedArticle " + article.getArticleId());
//        out.close();
//    }
//
//    public void modifyAv(AV av, Context context) throws IOException {
//        DataBaseHelper db = new DataBaseHelper(context);
//        db.updateAvData(av);
//        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "modifiedAV " + av.getAvId());
//        out.close();
//    }

    public void modifyPatron(Patron user, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (user.getUsersType() < 5) {
            db.updateUserData(user);
            out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "modifiedPatron " + user.getuId());
            out.close();
        }
    }

//    public void search(String input){
//        AutomatonQuery query = new AutomatonQuery();
//    }
}
