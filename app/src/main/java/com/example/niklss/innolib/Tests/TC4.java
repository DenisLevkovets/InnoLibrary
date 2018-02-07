package com.example.niklss.innolib.Tests;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;

/**
 * Created by user on 06.02.2018.
 */

public class TC4 {
    public static void main(String[] args) {
        Books book=new Books("ItP", "Somebody", "May 2016", 0, true,1);
        Patron f= new Patron("Bertran Mayer","address", "123456789", 1 );
        Patron s= new Patron("Denis Levkovets", "Innopolis", "987654321", 0);

        f.checkOut();
    }
}
