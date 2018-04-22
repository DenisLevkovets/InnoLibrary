package com.example.niklss.innolib.Classes;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by user on 02.02.2018.
 * It should content the patrons functions and difference between teachers and student
 */

public class Patron extends UserCard {
    private ArrayList<Integer> wasRenewedBook = new ArrayList<>();
    private ArrayList<Integer> wasRenewedArticle = new ArrayList<>();
    private ArrayList<Integer> wasRenewedAv = new ArrayList<>();
    private PrintWriter out = new PrintWriter(new File("Log"));

    public Patron(ArrayList<String> a) throws FileNotFoundException {
        super(a.get(0), a.get(1), a.get(2), Integer.parseInt(a.get(3)), a.get(4), Integer.parseInt(a.get(5)));
    }

    public Patron(String name, String secondName, String address, String num, int isStatus, int id) throws FileNotFoundException {
        super(name, secondName, address, id, num, isStatus);
    }

    public Patron(String[] a) throws FileNotFoundException {
        super(a);
    }

    private void searchDoc(String anyName) {

    }

    private int month(String mm){
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String checkOut(int id, Context context) throws IOException {
        String output;
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

                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, book.getDaysLeft());

                    String[] date = cal.getTime().toString().split(" ");
                    String t = date[2] + "." + month(date[1]) + "." + date[5];
                    db.updateTimeChecker(this.getuId(), book.getBookId(), t, book.getTypeOfMaterial(), 0);
                    output = "You can take the book";

                } else {
                    output = "You are in queue for this book";
                    db.standInQueue(this, book.getBookId(), book.getTypeOfMaterial());
                }
            } else {
                output = "You already have this book";
            }
        } else {
            output = "Isn't your type";
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "chechoutBook " + id);
        out.close();
        return output;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String checkOutAV(int id, Context context) throws IOException {
        String output;
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
                db.updateAV(av.getAvId(), av.getTitle(), av.getAuthors(), av.getCountAv(), av.getKeywords(), av.getPrice());

                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, av.getDaysLeft());

                String[] date = cal.getTime().toString().split(" ");
                String t = date[2] + "." + month(date[1]) + "." + date[5];
                db.updateTimeChecker(this.getuId(), av.getAvId(), t, av.getTypeOfMaterial(), 0);
                output = "You can take the AV";
            } else {
                output = "You are in queue for this AV material";
                db.standInQueue(this, av.getAvId(), av.getTypeOfMaterial());
            }
        } else {
            output = "You already have this AV material";
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "chechoutAV " + id);
        out.close();
        return output;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String checkOutArticle(int id, Context context) throws IOException {
        String output;
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
                db.updateAV(art.getArticleId(), art.getTitle(), art.getAuthors(), art.getCountArticle(), art.getKeywords(), art.getPrice());

                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, art.getDaysLeft());

                String[] date = cal.getTime().toString().split(" ");
                String t = date[2] + "." + month(date[1]) + "." + date[5];
                db.updateTimeChecker(this.getuId(), art.getArticleId(), t, art.getTypeOfMaterial(), 0);
                output = "You can take the Article";
            } else {
                output = "You are in queue for this article";
                db.standInQueue(this, art.getArticleId(), art.getTypeOfMaterial());
            }
        } else {
            output = "You already have this article";
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "chechoutArticle " + id);
        out.close();
        return output;
    }

    public ArrayList<Books> getAvailiableBooks(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfBooks();
    }

    public ArrayList<AV> getAvailiableAV(Context context) throws IOException{
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfAV();
    }

    public ArrayList<Articles> getAvailiableAricles(Context context) throws IOException{
        DataBaseHelper db = new DataBaseHelper(context);
        return db.getListOfArticles();
    }

    private boolean hasCopy(Context context, int id, int type) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.hasBook(this.getuId(), id, type);
    }

    public void renewBook(Books book, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(book.getBookId(), book.getTypeOfMaterial())) {
            if (this.getUsersType() == 3) {
                book.setDaysLeft(7);
                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, book.getDaysLeft());

                String[] date = cal.getTime().toString().split(" ");
                String t = date[2] + "." + month(date[1]) + "." + date[5];
                db.updateTimeChecker(this.getuId(), book.getBookId(), t, book.getTypeOfMaterial(), 1);
            } else {
                if (db.wasRenewed(this.getuId(), book.getBookId(), book.getTypeOfMaterial())) {
                    System.out.println("You cant renew this book again");
                } else {
                    book.setDaysLeft(14 + db.daysLeft(this.getuId(), book.getBookId(), book.getTypeOfMaterial()));
                    this.wasRenewedBook.add(book.getBookId());
                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, book.getDaysLeft());

                    String[] date = cal.getTime().toString().split(" ");
                    String t = date[2] + "." + month(date[1]) + "." + date[5];
                    db.updateTimeChecker(this.getuId(), book.getBookId(), t, book.getTypeOfMaterial(), 1);
                }
            }


        } else {
            System.out.println("Someone already waits for this book");
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "renewBook " + book.getBookId());
        out.close();
    }

    public void renewArticle(Articles article, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(article.getArticleId(), article.getTypeOfMaterial())) {
            if (this.getUsersType() == 3) {
                article.setDaysLeft(7);
                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, article.getDaysLeft());

                String[] date = cal.getTime().toString().split(" ");
                String t = date[2] + "." + month(date[1]) + "." + date[5];
                db.updateTimeChecker(this.getuId(), article.getArticleId(), t, article.getTypeOfMaterial(), 1);
            } else {
                if (db.wasRenewed(this.getuId(), article.getArticleId(), article.getTypeOfMaterial())) {
                    System.out.println("You cant renew this book again");
                } else {
                    article.setDaysLeft(14 + db.daysLeft(this.getuId(), article.getArticleId(), article.getTypeOfMaterial()));
                    this.wasRenewedArticle.add(article.getArticleId());
                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, article.getDaysLeft());

                    String[] date = cal.getTime().toString().split(" ");
                    String t = date[2] + "." + month(date[1]) + "." + date[5];
                    db.updateTimeChecker(this.getuId(), article.getArticleId(), t, article.getTypeOfMaterial(), 1);
                }
            }

        } else {
            System.out.println("Someone already waits for this article");
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "renewArticle " + article.getArticleId());
        out.close();
    }

    public void renewAv(AV av, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        if (db.noOneInQueue(av.getAvId(), av.getTypeOfMaterial())) {
            if (this.getUsersType() == 3) {
                av.setDaysLeft(7);
                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_MONTH, av.getDaysLeft());

                String[] date = cal.getTime().toString().split(" ");
                String t = date[2] + "." + month(date[1]) + "." + date[5];
                db.updateTimeChecker(this.getuId(), av.getAvId(), t, av.getTypeOfMaterial(), 1);
            } else {
                if (db.wasRenewed(this.getuId(), av.getAvId(), av.getTypeOfMaterial())) {
                    System.out.println("You cant renew this book again");
                } else {
                    av.setDaysLeft(14 + + db.daysLeft(this.getuId(), av.getAvId(), av.getTypeOfMaterial()));
                    this.wasRenewedAv.add(av.getAvId());
                    Calendar cal = new GregorianCalendar();
                    cal.add(Calendar.DAY_OF_MONTH, av.getDaysLeft());

                    String[] date = cal.getTime().toString().split(" ");
                    String t = date[2] + "." + month(date[1]) + "." + date[5];
                    db.updateTimeChecker(this.getuId(), av.getAvId(), t, av.getTypeOfMaterial(), 1);
                }
            }
        } else {
            System.out.println("Someone already waits for this AV material");
        }
        out.println(Calendar.DATE + " " + this.getuName() + " " + this.getSecondName() + " " + this.getuId() + "renewAV " + av.getAvId());
        out.close();
    }

    public ArrayList<Books> getListOfUsersBook(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.returnListOfUsersBook(this.getuId());
    }

    public ArrayList<Articles> getListOfUsersArticles(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.returnListOfUsersArticles(this.getuId());
    }

    public ArrayList<AV> getListOfUsersAv(Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        return db.returnListOfUsersAv(this.getuId());
    }

    private void returnDoc(int id) {

    }
}