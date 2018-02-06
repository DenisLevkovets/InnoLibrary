package com.example.niklss.innolib.Classes;

/**
 * Created by Niklss on 06.02.2018.
 */

public class Books {
    private int isForUser;
    private String titleBook;
    private String[] authorsOfBook;
    private String dateOfCreationOfBook;
    private boolean isBestSeller;
    private int countOfBooks;
    private UserCard user;
    private String accessDue;

    Books(String title, String[] authors,String date, int forUser, boolean isBestSeller, int count){
        this.titleBook = title;
        this.authorsOfBook = authors;
        this.dateOfCreationOfBook = date;
        this.isForUser = forUser;
        this.isBestSeller = isBestSeller;
        this.countOfBooks = count;
    }

    public String getAccessDue() {
        return accessDue;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public int getIsForUser() {
        return isForUser;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String[] getAuthorsOfBook() {
        return authorsOfBook;
    }

    public String getDateOfCreationOfBook() {
        return dateOfCreationOfBook;
    }

    public boolean getIsBestSeller() {
        return isBestSeller;
    }

    public int getCountOfBooks() {
        return countOfBooks;
    }

    public void setCountOfBooks(int countOfBooks) {
        this.countOfBooks = countOfBooks;
    }

    public void setAccessDue(String accessDue) {
        this.accessDue = accessDue;
    }
}
