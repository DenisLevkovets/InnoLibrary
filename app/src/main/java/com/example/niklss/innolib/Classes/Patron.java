package com.example.niklss.innolib.Classes;

/**
 * Created by user on 02.02.2018.
 * It should content the patrons functions and difference between teachers and student
 */

public class Patron extends UserCard {

    public Patron(String name, String secondName, String adress, String num, int isStatus, int id) {
        super(name, secondName, adress, id, num, isStatus);
    }

    private void searchDoc(String anyName){

    }

    public void checkOut(Books book) {

        if (getUsersType() >= book.getIsForUser()){
            if (book.getCountOfBooks() > 0 && !hasCopy(book)) {
                book.setCountOfBooks(book.getCountOfBooks() - 1);
                this.addBookToTheList(book);
                book.setUser(this);
                if (getUsersType() == 1) {
                    book.setAccessDue("4 weeks");
                } else if (book.getIsBestSeller() == 1) {
                    book.setAccessDue("2 weeks");
                }
                else{
                    book.setAccessDue("3 weeks");
                }
            }
        }

        else{
            System.out.println("Not available");
        }
    }

    private boolean hasCopy(Books book) {
        for (int i = 0; i < this.getListOfBooks().size() - 1; i++) {
            if (book.getBookId() == this.getListOfBooks().get(i).getBookId()) {
                System.out.println("You already have this book");
                return true;
            }
        }
        return false;
    }

    private void returnDoc(int id){

    }


}
