package com.example.niklss.innolib.Classes;

/**
 * Created by Niklss on 06.02.2018.
 */

public class Books {
    private int typeOfMaterial = 0;

    private int reference;
    private String titleBook;
    private String authorsOfBook;
    private int bookId;
    private String dateOfCreationOfBook;
    private int isBestSeller;
    private int countOfBooks;
    private UserCard user;
    private int price;
    private int edition;
    private String published_by;
    private String keywords;
    private int daysLeft;

    public Books(String title, String authors, int count, int id, int forUser, int price, int edition, String date, String published, String keywords, int isBestSeller) {
        this.isBestSeller = isBestSeller;
        this.keywords = keywords;
        this.published_by = published;
        this.bookId = id;
        this.titleBook = title;
        this.authorsOfBook = authors;
        this.dateOfCreationOfBook = date;
        this.reference = forUser;
        this.countOfBooks = count;
        this.price = price;
        this.edition = edition;
    }

    public Books (String[] bookInf) {
        this.titleBook = bookInf[0];
        this.authorsOfBook = bookInf[1];
        this.countOfBooks = Integer.parseInt(bookInf[2]);
        this.bookId = Integer.parseInt(bookInf[3]);
        this.reference = Integer.parseInt(bookInf[4]);
        this.price = Integer.parseInt(bookInf[5]);
        this.edition = Integer.parseInt(bookInf[6]);
        this.dateOfCreationOfBook = bookInf[7];
        this.published_by = bookInf[8];
        this.keywords = bookInf[9];
        this.isBestSeller = Integer.parseInt(bookInf[10]);
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public int getReference() {
        return reference;
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

    public String getPublished_by() {
        return published_by;
    }

    public String getKeywords() {
        return keywords;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getTypeOfMaterial() {
        return typeOfMaterial;
    }
}
