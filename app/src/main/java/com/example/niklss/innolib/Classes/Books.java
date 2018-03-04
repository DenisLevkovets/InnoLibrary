package com.example.niklss.innolib.Classes;

/**
 * Created by Niklss on 06.02.2018.
 */

public class Books {

    private String titleBook;
    private String authorsOfBook;
    private int bookId;
    private int isBestSeller;
    private int countOfBooks;
    private int reference;
    private int price;
    private String edition;
    private String wasPublished;
    private UserCard user;
    private int daysLeft;
    private String keywords;


    public Books(String title, String authors, int reference,int price, String edition, String wasPublished, int count, int isBestSeller, int id, String keywords ) {
        titleBook=title;
        authorsOfBook=authors;
        this.reference=reference;
        this.price=price;
        this.edition=edition;
        this.wasPublished=wasPublished;
        countOfBooks=count;
        this.isBestSeller=isBestSeller;
        bookId=id;
        this.keywords=keywords;
    }



    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }


    public String getTitleBook() {
        return titleBook;
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

    public int getReference(){ return reference;}

    public String getEdition() {
        return edition;
    }

    public void setDaysLeft(int daysLeft){ this.daysLeft=daysLeft;}

    public int getDaysLeft(){return daysLeft;}

}
