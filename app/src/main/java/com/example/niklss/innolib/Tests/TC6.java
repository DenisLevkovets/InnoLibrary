package com.example.niklss.innolib.Tests;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;

/**
 * Created by Niklss on 06.02.2018.
 */

public class TC6 {
    Patron p1;
    Librarian l;
    Books b;

    public void main(String args[]) {
        p1 = new Patron("Ivan", "Ivanov", "Innopolis", "112", 0, 1);
        l = new Librarian("Librarian", "Librarianov", "Innopolis", 3232, "2", 2);
        b = new Books("DSA", "Cormen", 1337, 0, "", 5, 100, 23, "2017", "Alex", "IT", 1);

        System.out.println(b.getCountOfBooks());
        p1.checkOut(b);
        System.out.println(b.getCountOfBooks());
        p1.checkOut(b);
        System.out.println(p1.getListOfBooks().size());
    }
}
