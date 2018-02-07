package com.example.niklss.innolib.Tests;

import com.example.niklss.innolib.Activites.Login;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;

/**
 * Created by Niklss on 06.02.2018.
 */

public class TC5 {
    Patron p1;
    Patron p2;
    Patron p3;
    Librarian l;
    Books b;

    public void main(String args[]) {
        p1 = new Patron("Ivan", "Ivanov", "Innopolis", "112", 0, 1);
        p2 = new Patron("Alex", "Petrov", "Innopolis", "333", 0, 2);
        p3 = new Patron("Vasiliy", "Sidorov", "Innopolis", "333", 0, 3);
        l = new Librarian("Librarian", "Librarianov", "Innopolis", 3232, "2", 2);
        b = new Books("DSA", "Cormen", 2, 0, "", 5, 100, 23, "2017", "Alex", "IT", 1);

        System.out.println(b.getCountOfBooks());
        p1.checkOut(b);
        System.out.println(b.getCountOfBooks());
        p2.checkOut(b);
        System.out.println(b.getCountOfBooks());
        p3.checkOut(b);
        System.out.println(p3.getListOfBooks().get(p3.getListOfBooks().size() - 1).getAuthorsOfBook());
    }
}
