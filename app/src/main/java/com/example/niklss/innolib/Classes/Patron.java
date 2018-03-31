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

    public Patron(String[] a){
        super(a);
    }

    private void searchDoc(String anyName){

    }

    public void checkOut(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        Books book = new Books(db.getArrayBook(id));

        if (getUsersType() >= book.getReference()){
            if (book.getCountOfBooks() > 0 && !hasCopy(context, book.getBookId(), 0)) {
                book.setCountOfBooks(book.getCountOfBooks() - 1);
//                this.addBookToTheList(book);
//                book.setUser(this);
                if (getUsersType() == 1) {
                    book.setDaysLeft(28);
                } else if (book.getIsBestSeller() == 1) {
                    book.setDaysLeft(14);
                }
                else{
                    book.setDaysLeft(21);
                }
                db.updateBookData(book);
                db.updateTimeChecker(this.getuId(), book.getBookId(), book.getDaysLeft(), 0);
            }
        }

        else{
            System.out.println("Not available");
        }
    }

    public void checkOutAV(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        ArrayList<String> av = db.getAVMaterial(id);

        if (Integer.parseInt(av.get(3)) > 0 && !hasCopy(context, Integer.parseInt(av.get(2)), 2)) {
            db.updateAV(Integer.parseInt(av.get(2)), av.get(0), av.get(1), Integer.parseInt(av.get(3)));
            db.updateTimeChecker(this.getuId(), Integer.parseInt(av.get(2)), 21, 2);
        } else {
            System.out.println("Not available");
        }
    }

    public void checkOutArticle(int id, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        ArrayList<String> av = db.getArticleMaterial(id);

        if (Integer.parseInt(av.get(6)) > 0 && !hasCopy(context, Integer.parseInt(av.get(7)), 1)) {
            db.updateArticle(Integer.parseInt(av.get(7)), av.get(0), av.get(1), av.get(2), av.get(3), av.get(4), av.get(5), Integer.parseInt(av.get(6)));
            db.updateTimeChecker(this.getuId(), Integer.parseInt(av.get(2)), 21, 1);
        } else {
            System.out.println("Not available");
        }
    }

    public ArrayList<Books> getAvailiableBooks(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfBooks();
    }

    private boolean hasCopy(Context context, int id, int type) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.hasBook(this.getuId(), id, type);
    }

    private void addBookToList(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        db.returnListOfUsersBook(this.getuId());
    }

    private void returnDoc(int id){

    }

}
