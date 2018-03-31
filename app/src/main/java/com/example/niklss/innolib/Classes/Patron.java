package com.example.niklss.innolib.Classes;

import android.content.Context;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 * It should content the patrons functions and difference between teachers and student
 */

public class Patron extends UserCard {

    public Patron(ArrayList<String> a) {
        super(a.get(0), a.get(1), a.get(2), Integer.parseInt(a.get(3)), a.get(4), Integer.parseInt(a.get(5)));
    }

    public Patron(String name, String secondName, String address, String num, int isStatus, int id) {
        super(name, secondName, address, id, num, isStatus);
    }

    public Patron(String[] a) {
        super(a);
    }

    private void searchDoc(String anyName) {

    }

    public void checkOut(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Books book = new Books(db.getArrayBook(id));

        if (getUsersType() >= book.getReference()) {
            if (!hasCopy(context, book.getBookId(), 0)) {
                if (book.getCountOfBooks() > 0) {
                    book.setCountOfBooks(book.getCountOfBooks() - 1);
//                this.addBookToTheList(book);
//                book.setUser(this);
                    if (getUsersType() == 1) {
                        book.setDaysLeft(28);
                    } else if (book.getIsBestSeller() == 1) {
                        book.setDaysLeft(14);
                    } else {
                        book.setDaysLeft(21);
                    }
                    db.updateBookData(book);
                    db.updateTimeChecker(this.getuId(), book.getBookId(), book.getDaysLeft(), 0);
                } else {
                    System.out.println("You are in queue for this book");
                    db.standInQueue(this, book);
                }
            } else {
                System.out.println("You already have this book");
            }
        } else {
            System.out.println("Isn't your type");
        }
    }

    public void checkOutAV(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        AV av = new AV(db.getAVMaterial(id));

        if (av.getCountAv() > 0 && !hasCopy(context, av.getAvId(), 2)) {
            db.updateAV(av.getAvId(), av.getTitle(), av.getAuthors(), av.getCountAv());
            db.updateTimeChecker(this.getuId(), av.getAvId(), 21, 2);
        } else {
            System.out.println("Not available");
        }
    }

    public void checkOutArticle(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Articles art = new Articles(db.getArticleMaterial(id));

        if (art.getCountAv() > 0 && !hasCopy(context, art.getAvId(), 1)) {
            db.updateArticle(art.getAvId(), art.getTitle(), art.getAuthors(), art.getJtitle(), art.getIssue(), art.getDate(), art.getEditor(), art.getCountAv());
            db.updateTimeChecker(this.getuId(), art.getAvId(), 21, 1);
        } else {
            System.out.println("Not available");
        }
    }

    public ArrayList<Books> getAvailiableBooks(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfBooks();
    }

//    public ArrayList<AV> getAvailiableAV(Context context) throws IOException{
//        DataBaseHelper db = new DataBaseHelper(context);
//        return db.get
//    }

    private boolean hasCopy(Context context, int id, int type) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.hasBook(this.getuId(), id, type);
    }

    private void renewDoc(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(this.getuId(), book.getBookId())) {
            book.setDaysLeft(14);
            db.updateTimeChecker(this.getuId(), book.getBookId(), book.getDaysLeft(), 0);
        }
    }

    private void addBookToList(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.returnListOfUsersBook(this.getuId());
    }

    private void returnDoc(int id) {

    }
}