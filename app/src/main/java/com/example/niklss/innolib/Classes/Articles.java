package com.example.niklss.innolib.Classes;

/**
 * Created by Niklss on 31.03.2018.
 */

public class Articles {

    private String title;
    private String authors;
    private String jtitle;
    private String issue;
    private String date;
    private String editor;
    private int avId;
    private int countAv;
    private UserCard user;
    private int daysLeft;

    public void Articles(String title, String authors, String jtitle, String issue, String date, String editor, int avId, int countAv) {
        this.title = title;
        this.authors = authors;
        this.jtitle = jtitle;
        this.issue = issue;
        this.date = date;
        this.editor = editor;
        this.avId = avId;
        this.countAv = countAv;
    }

    public void Articles(String[] av) {
        this.title = av[0];
        this.authors = av[1];
        this.jtitle = av[2];
        this.issue = av[3];
        this.date = av[4];
        this.editor = av[5];
        this.avId = Integer.parseInt(av[6]);
        this.countAv = Integer.parseInt(av[7]);
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

    public int getAvId() {
        return avId;
    }

    public int getCountAv() {
        return countAv;
    }

    public void setCountAv(int countAv) {
        this.countAv = countAv;
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
}
