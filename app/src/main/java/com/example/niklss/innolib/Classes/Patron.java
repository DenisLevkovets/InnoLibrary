package com.example.niklss.innolib.Classes;

import android.content.Context;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

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

    public void checkOut(int id, Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        Books book = new Books(db.getArrayBook(id));

        if (getUsersType() >= book.getIsForUser()){
            if (book.getCountOfBooks() > 0 && !hasCopy(context, book.getBookId())) {
                book.setCountOfBooks(book.getCountOfBooks() - 1);
//                this.addBookToTheList(book);
//                book.setUser(this);
                if (getUsersType() == 1) {
                    book.setAccessDue("28");
                } else if (book.getIsBestSeller() == 1) {
                    book.setAccessDue("14");
                }
                else{
                    book.setAccessDue("21");
                }
                db.updateBookData(book);
                db.updateTimeChecker(this.getuId(), book.getBookId(), Integer.parseInt(book.getAccessDue()), 0);
            }
        }

        else{
            System.out.println("Not available");
        }
    }

    public ArrayList<Books> getAvailiableBooks(Context context){
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfBooks(this.getUsersType());
    }

    private boolean hasCopy(Context context, int id) {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.hasBook(this.getuId(), id);
    }

    private void returnDoc(int id){

    }


}
