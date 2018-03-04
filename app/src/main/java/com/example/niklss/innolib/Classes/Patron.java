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

        if(hasCopy(book)==true){
            System.out.println("You already have this book");
        }
        else if(book.getCountOfBooks()<=0){
            System.out.println("There is no copies of this book");
        }
        else if (book.getReference() == 0){
            if (book.getCountOfBooks() > 0 && !hasCopy(book)) {
                book.setCountOfBooks(book.getCountOfBooks() - 1);
                this.addBookToTheList(book);
                book.setUser(this);
                if (book.getIsBestSeller() == 1) {
                    book.setDaysLeft(14);
                }
                else if (getUsersType() ==1){
                    book.setDaysLeft(28);
                }
                else{
                    book.setDaysLeft(21);
                }
            }
            System.out.println("You take book \""+book.getTitleBook()+"\" for " +book.getDaysLeft()+" days");
        }

        else{
            System.out.println("Not available");
        }
    }

    private boolean hasCopy(Books book) {
        for (int i = 0; i < this.getListOfBooks().size() - 1; i++) {
            if (book.getBookId() == this.getListOfBooks().get(i).getBookId()) {
                return true;
            }
        }
        return false;
    }


}
