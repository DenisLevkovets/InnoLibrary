package com.example.niklss.innolib.Classes;

import java.util.ArrayList;

/**
 * Created by Niklss on 31.03.2018.
 */

public class Articles  {
    private int typeOfMaterial = 1;

    private String title;
    private String authors;
    private String jtitle;
    private String issue;
    private String date;
    private String editor;
    private int articleId;
    private int countArticle;
    private UserCard user;
    private int daysLeft;
    private int reference;
    private String keywords;
    private int price;
    private boolean overDue;
    private int fine;

    public Articles(String title, String authors, String jtitle, String issue, String date, String editor, int countArticle, int articleId, int reference, String keywords, int price) {
        this.title = title;
        this.authors = authors;
        this.jtitle = jtitle;
        this.issue = issue;
        this.date = date;
        this.editor = editor;
        this.countArticle = countArticle;
        this.articleId = articleId;
        this.reference = reference;
        this.keywords = keywords;
        this.price = price;
    }

    public Articles(String[] av) {
        this.title = av[0];
        this.authors = av[1];
        this.jtitle = av[2];
        this.issue = av[3];
        this.date = av[4];
        this.editor = av[5];
        this.countArticle = Integer.parseInt(av[6]);
        this.articleId = Integer.parseInt(av[7]);
        this.reference = Integer.parseInt(av[8]);
        this.keywords = av[9];
        this.price = Integer.parseInt(av[10]);
    }

    public Articles(ArrayList<String> av) {
        this.title = av.get(0);
        this.authors = av.get(1);
        this.jtitle = av.get(2);
        this.issue = av.get(3);
        this.date = av.get(4);
        this.editor = av.get(5);
        this.countArticle = Integer.parseInt(av.get(6));
        this.articleId = Integer.parseInt(av.get(7));
        this.reference = Integer.parseInt(av.get(8));
        this.keywords = av.get(9);
        this.price = Integer.parseInt(av.get(10));
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getJtitle() {
        return jtitle;
    }

    public String getIssue() {
        return issue;
    }

    public String getDate() {
        return date;
    }

    public String getEditor() {
        return editor;
    }

    public int getArticleId() {
        return articleId;
    }

    public int getCountArticle() {
        return countArticle;
    }

    public void setCountArticle(int countAv) {
        this.countArticle = countAv;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getReference() {
        return reference;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getPrice() {
        return price;
    }

    public int getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public boolean isOverDue() {
        return overDue;
    }

    public void setOverDue(boolean overDue) {
        this.overDue = overDue;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
