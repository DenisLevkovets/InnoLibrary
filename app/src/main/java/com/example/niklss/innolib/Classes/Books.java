package com.example.niklss.innolib.Classes;

/**
 * Created by Niklss on 06.02.2018.
 * Class for using UserCards
 */

public class Books {
    private int isForUser;
    private String titleBook;
    private String authorsOfBook;
    private int bookId;
    private String dateOfCreationOfBook;
    private int isBestSeller;
    private int countOfBooks;
    private UserCard user;
    private String accessDue;
    private int price;
    private int edition;
    private String lastDate;
    private String published_by;
    private String keywords;

    public Books(String title, String authors, int count, int id, String last_date, int forUser, int price, int edition, String date, String published, String keywords, int isBestSeller) {
        this.titleBook = title;
        this.authorsOfBook = authors;
        this.countOfBooks = count;
        this.bookId = id;
        this.lastDate = last_date;
        this.isForUser = forUser;
        this.price = price;
        this.edition = edition;
        this.dateOfCreationOfBook = date;
        this.published_by = published;
        this.keywords = keywords;
        this.isBestSeller = isBestSeller;
    }

    public Books (String bookInf) {
        String[] a = bookInf.split(" ");
        this.titleBook = a[0];
        this.authorsOfBook = a[1];
        this.countOfBooks = Integer.parseInt(a[2]);
        this.bookId = Integer.parseInt(a[3]);
        this.lastDate = a[4];
        this.isForUser = Integer.parseInt(a[5]);
        this.price = Integer.parseInt(a[6]);
        this.edition = Integer.parseInt(a[7]);
        this.dateOfCreationOfBook = a[8];
        this.published_by = a[9];
        this.keywords = a[10];
        this.isBestSeller = Integer.parseInt(a[11]);
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

    public String getAuthorsOfBook() {
        return authorsOfBook;
    }

    public String getDateOfCreationOfBook() {
        return dateOfCreationOfBook;
    }

    public int getIsBestSeller() {
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

    public int getBookId() {
        return bookId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getEdition() {
        return edition;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getPublished_by() {
        return published_by;
    }

    public String getKeywords() {
        return keywords;
    }
}
