package com.example.niklss.innolib.Tests;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;

/**
 * Created by solo1 on 06.02.2018.
 */

public class TC10 {
    static Patron p;
    static Librarian l;
    static Books b1;
    static Books b2;

    public static void main(String[] args) {
        p = new Patron("Alex","Petrov", "Innopolis","333",0, 2);
        l = new Librarian("Librarian","Librarianov","Innopolis",3232, "2", 2);

        Books b1 = new Books("DSA","Cormen", 11,0, "",5, 100, 23, "2017", "Alex", "IT", 1);
        Books b2 = new Books("DSA2","Cormen", 6,1, "",5, 100, 23, "2017", "Alex", "IT", 1);

        p.checkOut(b2);
        p.checkOut(b1);
        System.out.println(b1.getAccessDue());
    }
}
