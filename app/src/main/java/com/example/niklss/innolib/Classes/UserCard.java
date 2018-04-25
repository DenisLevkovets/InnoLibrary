package com.example.niklss.innolib.Classes;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.niklss.innolib.DataBase.DataBaseHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 02.02.2018.
 * UserCards with all information
 */

public class UserCard {
    private String uName;
    private String secondName;
    private String uAddress;
    private String uNumber;
    private int usersType;
    private int uId;


    public UserCard(String name, String secondName, String adress, int id, String num, int usersType) {
        this.uName = name;
        this.secondName = secondName;
        this.uAddress = adress;
        this.uNumber = num;
        this.usersType = usersType;
        this.uId = id;
    }

    public UserCard(String[] card) {
        this.uName = card[0];
        this.secondName = card[1];
        this.uAddress = card[2];
        this.uId = Integer.parseInt(card[3]);
        this.uNumber = card[4];
        this.usersType = Integer.parseInt(card[5]);
    }

    public UserCard(ArrayList<String> a) {
        this.uName = a.get(0);
        this.secondName = a.get(1);
        this.uAddress = a.get(2);
        this.uId = Integer.parseInt(a.get(3));
        this.uNumber = a.get(4);
        this.usersType = Integer.parseInt(a.get(5));
    }

    public int getuId() {
        return uId;
    }

    public String getuAddress() {
        return uAddress;
    }

    public String getuName() {
        return uName;
    }

    public String getuNumber() {
        return uNumber;
    }

    public int getUsersType() {
        return usersType;
    }

    public String getSecondName() {
        return secondName;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<Books> searchBook(String title, String author, int reference, String price, String edition, String dateOfCreation, String publishedBy, String keywords, int isBestSeller, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        ArrayList<Books> books = new ArrayList<>();

        ArrayList<Books> input = db.getListOfBooks();
        for (int i = 0; i < input.size(); i++) {
            boolean inThere = false;
            if (title != null && !title.equals("")){
                if (!input.get(i).getTitleBook().toLowerCase().contains(title.toLowerCase())){
                    continue;
                }
            }
            if (author != null && !author.equals("")){
                if (!input.get(i).getAuthorsOfBook().toLowerCase().contains(author.toLowerCase())){
                    continue;
                }
            }
            if (reference != input.get(i).getReference()){
                continue;
            }
            if (price != null && !price.equals("")){
                if (!Integer.toString(input.get(i).getPrice()).contains(price)){
                    continue;
                }
            }
            if (edition != null && !edition.equals("")){
                if (!Integer.toString(input.get(i).getEdition()).contains(edition)){
                    continue;
                }
            }
            if (dateOfCreation != null && !dateOfCreation.equals("")){
                if (!input.get(i).getDateOfCreationOfBook().toLowerCase().contains(dateOfCreation.toLowerCase())){
                    continue;
                }
            }
            if (publishedBy != null && !publishedBy.equals("")){
                if (!input.get(i).getPublished_by().toLowerCase().contains(publishedBy.toLowerCase())){
                    continue;
                }
            }
            if (keywords != null && !keywords.equals("")){
                String[] keysOfBook = input.get(i).getKeywords().toLowerCase().split(" ");
                String[] myKeys = keywords.toLowerCase().split(" ");
                for (int j = 0; j < myKeys.length; j++) {
                    for (int k = 0; k < keysOfBook.length; k++) {
                        if (keysOfBook[k].contains(myKeys[j])){
                            inThere = true;
                        }
                    }
                }
                if (!inThere){
                    continue;
                }
            }
            if (isBestSeller != input.get(i).getIsBestSeller()){
                continue;
            }

            books.add(input.get(i));
        }

        return books;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<Articles> searchArticle(String title, String author, String jtitle, String issue, String date, String editor, int reference, String keywords, String price, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        ArrayList<Articles> articles = new ArrayList<>();

        ArrayList<Articles> input = db.getListOfArticles();
        for (int i = 0; i < input.size(); i++) {
            boolean inThere = false;
            if (title != null && !title.equals("")){
                if (!input.get(i).getTitle().toLowerCase().contains(title.toLowerCase())){
                    continue;
                }
            }
            if (author != null && !author.equals("")){
                if (!input.get(i).getAuthors().toLowerCase().contains(author.toLowerCase())){
                    continue;
                }
            }
            if (reference != input.get(i).getReference()){
                continue;
            }
            if (price != null && !price.equals("")){
                if (!Integer.toString(input.get(i).getPrice()).contains(price)){
                    continue;
                }
            }
            if (editor != null && !editor.equals("")){
                if (!input.get(i).getEditor().toLowerCase().contains(editor.toLowerCase())){
                    continue;
                }
            }
            if (date != null && !date.equals("")){
                if (!input.get(i).getDate().toLowerCase().contains(date.toLowerCase())){
                    continue;
                }
            }
            if (jtitle != null && !jtitle.equals("")){
                if (!input.get(i).getJtitle().toLowerCase().contains(jtitle.toLowerCase())){
                    continue;
                }
            }
            if (keywords != null && !keywords.equals("")){
                String[] keysOfBook = input.get(i).getKeywords().toLowerCase().split(" ");
                String[] myKeys = keywords.toLowerCase().split(" ");
                for (int j = 0; j < myKeys.length; j++) {
                    for (int k = 0; k < keysOfBook.length; k++) {
                        if (keysOfBook[k].contains(myKeys[j])){
                            inThere = true;
                        }
                    }
                }
                if (!inThere){
                    continue;
                }
            }
            if (issue != null && !issue.equals("")){
                if (!input.get(i).getIssue().toLowerCase().contains(issue.toLowerCase())){
                    continue;
                }
            }

            articles.add(input.get(i));
        }

        return articles;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<AV> searchArticle(String title, String author, String keywords, String price, Context context) throws IOException {
        DataBaseHelper db = new DataBaseHelper(context);
        ArrayList<AV> av = new ArrayList<>();

        ArrayList<AV> input = db.getListOfAV();
        for (int i = 0; i < input.size(); i++) {
            boolean inThere = false;
            if (title != null && !title.equals("")){
                if (!input.get(i).getTitle().toLowerCase().contains(title.toLowerCase())){
                    continue;
                }
            }
            if (author != null && !author.equals("")){
                if (!input.get(i).getAuthors().toLowerCase().contains(author.toLowerCase())){
                    continue;
                }
            }
            if (price != null && !price.equals("")){
                if (!Integer.toString(input.get(i).getPrice()).contains(price)){
                    continue;
                }
            }
            if (keywords != null && !keywords.equals("")){
                String[] keysOfBook = input.get(i).getKeywords().toLowerCase().split(" ");
                String[] myKeys = keywords.toLowerCase().split(" ");
                for (int j = 0; j < myKeys.length; j++) {
                    for (int k = 0; k < keysOfBook.length; k++) {
                        if (keysOfBook[k].contains(myKeys[j])){
                            inThere = true;
                        }
                    }
                }
                if (!inThere){
                    continue;
                }
            }

            av.add(input.get(i));
        }
        return av;
    }
}
