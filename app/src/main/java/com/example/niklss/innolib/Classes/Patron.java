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
                    if (getUsersType() == 3) {
                        book.setDaysLeft(7);
                    } else if (book.getIsBestSeller() == 1) {
                        book.setDaysLeft(14);
                    } else if (getUsersType() == 1 || getUsersType() == 2 || getUsersType() == 4) {
                        book.setDaysLeft(28);
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

        if (!hasCopy(context, av.getAvId(), av.getTypeOfMaterial())) {
            if (av.getCountAv() > 0) {
                av.setCountAv(av.getCountAv() - 1);
                if (getUsersType() == 3) {
                    av.setDaysLeft(7);
                } else {
                    av.setDaysLeft(14);
                }
                db.updateAV(av.getAvId(), av.getTitle(), av.getAuthors(), av.getCountAv());
                db.updateTimeChecker(this.getuId(), av.getAvId(), av.getDaysLeft(), av.getTypeOfMaterial());
            } else {
                System.out.println("You are in queue for this AV material");
                db.standInQueue(this, av);
            }
        } else {
            System.out.println("You already have this AV material");
        }
    }

    public void checkOutArticle(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Articles art = new Articles(db.getArticleMaterial(id));

        if (!hasCopy(context, art.getArticleId(), art.getTypeOfMaterial())) {
            if (art.getCountArticle() > 0) {
                art.setCountArticle(art.getCountArticle() - 1);
                if (getUsersType() == 3) {
                    art.setDaysLeft(7);
                } else {
                    art.setDaysLeft(14);
                }
                db.updateAV(art.getArticleId(), art.getTitle(), art.getAuthors(), art.getCountArticle());
                db.updateTimeChecker(this.getuId(), art.getArticleId(), art.getDaysLeft(), art.getTypeOfMaterial());
            } else {
                System.out.println("You are in queue for this article");
                db.standInQueue(this, art);
            }
        } else {
            System.out.println("You already have this article");
        }
    }

    public ArrayList<Books> getAvailiableBooks(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfBooks();
    }

//    public ArrayList<AV> getAvailiableAV(Context context) throws IOException{
//        DataBaseHelper db = new DataBaseHelper(context);
//        return db.
//    }

    private boolean hasCopy(Context context, int id, int type) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.hasBook(this.getuId(), id, type);
    }

    private void renewBook(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(this.getuId(), book.getBookId(), book.getTypeOfMaterial())) {
            if (this.getUsersType() == 3){
                book.setDaysLeft(7);
            }
            else {
                book.setDaysLeft(14);
            }
            db.updateTimeChecker(this.getuId(), book.getBookId(), book.getDaysLeft(), 0);
        }
    }

    private void renewArticle(Articles article, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(this.getuId(), article.getArticleId(), article.getTypeOfMaterial())) {
            if (this.getUsersType() == 3){
                article.setDaysLeft(7);
            }
            else {
                article.setDaysLeft(14);
            }
            db.updateTimeChecker(this.getuId(), article.getArticleId(), article.getDaysLeft(), article.getTypeOfMaterial());
        }
    }

    private void renewAv(AV av, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(this.getuId(), av.getAvId(), av.getTypeOfMaterial())) {
            if (this.getUsersType() == 3){
                av.setDaysLeft(7);
            }
            else {
                av.setDaysLeft(14);
            }
            db.updateTimeChecker(this.getuId(), av.getAvId(), av.getDaysLeft(), av.getTypeOfMaterial());
        }
    }

//    private void addBookToList(Context context) throws IOException {
//        DataBaseHelper db = new DataBaseHelper(context);
//         = db.returnListOfUsersBook(this.getuId());
//    }

    private void returnDoc(int id) {

    }
}