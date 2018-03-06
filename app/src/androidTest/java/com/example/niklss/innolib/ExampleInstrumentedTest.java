package com.example.niklss.innolib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.niklss.innolib.Activites.Login;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Librarian l = new Librarian("Lib", "librarian", "Inno", 12, "89123453", 1);
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.addB("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiseron, Ronald L. Rivest and Clifford Stein", 3, 0, 255, 3, "2009", "MIT press", "ee", 0);
        /*db.addB("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm",2,0,212,"First edition","2003","Addison-Wesley Professional","qwe",1);
        db.addB("The Mythical Man-month","Brooks,Jr., Frederick P",1,1,2324,"Second edition","1995","Addison-Wesley Longman Publishing Co., Inc.","kk",0);
        db.addAV("Null References: The Billion Dollar Mistake","Tony Hoare",1,1212);
        db.addAV("Information Entropy","Claude Shannon",1,234);
        db.createUser("Sergey","Afonso","30001","Via Margutta, 3",2);
        db.createUser("Navida","Teixeria","30002","Via Sacra, 13",1);
        db.createUser("Elvira","Espindola","30003","Via del Corso, 22",1);*/
        db.deleteBook(3);
        ArrayList<Books> b = db.getListOfBooks();
        for (int i = 0; i < b.size(); i++) {
            System.out.println(b.get(i).getTitleBook() + " " + b.get(i).getCountOfBooks());
        }
    }
}
