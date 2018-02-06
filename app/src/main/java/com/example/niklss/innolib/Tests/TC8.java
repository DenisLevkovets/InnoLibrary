package com.example.niklss.innolib.Tests;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;

/**
 * Created by solo1 on 06.02.2018.
 */

public class TC8 {
  static Patron f;
  static Patron s;
  static Librarian l;

  static Books b;

    public static void main(String[] args) {
       f = new Patron("Ivan","Ivanov", "Innopolis","112",1, 1);
       s = new Patron("Alex","Petrov", "Innopolis","333",0, 2);
       l = new Librarian("Librarian","Librarianov","Innopolis",3232, "2", 2);
       b = new Books("DSA","Cormen", 11,0, "",5, 100, 23, "2017", "Alex", "IT", 1);
       s.checkOut(b);

       System.out.println(b.getAccessDue());
    }

}
