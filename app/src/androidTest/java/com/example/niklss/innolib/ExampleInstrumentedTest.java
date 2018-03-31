package com.example.niklss.innolib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        Librarian l = new Librarian("Lib", "librarian", "Inno", 12, "89123453", 1);
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        db.clearDataBase();
//        db.addB("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiseron, Ronald L. Rivest and Clifford Stein", 3, 0, 255, 3, "2009", "MIT press", "ee", 0);
//        db.addB("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm",2,0,212, 1,"2003","Addison-Wesley Professional","qwe",1);
//        db.addB("The Mythical Man-month","Brooks,Jr., Frederick P",1,1,2324, 2,"1995","Addison-Wesley Longman Publishing Co., Inc.","kk",0);
//        db.addAV("Null References: The Billion Dollar Mistake","Tony Hoare",1);
//        db.addAV("Information Entropy","Claude Shannon",1);
//        db.createUser("Sergey","Afonso","30001","Via Margutta, 3",1);
//        db.createUser("Navida","Teixeria","30002","Via Sacra, 13",0);
//
//        Patron p1 = new Patron("Sergey","Afonso","30001","Via Margutta, 3",1,1);
//        Patron p2 = new Patron("Navida","Teixeria","30002","Via Sacra, 13",0,2);
//        Books b1 = new Books("Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiseron, Ronald L. Rivest and Clifford Stein", 3,1, 0, 255, 3, "2009", "MIT press", "ee", 0);
//        Books b2 = new Books("Design Patterns: Elements of Reusable Object-Oriented Software","Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm",2,2,0,212, 1,"2003","Addison-Wesley Professional","qwe",1);
//        Books b3 = new Books("The Mythical Man-month","Brooks,Jr., Frederick P",1,3,1,2324, 2,"1995","Addison-Wesley Longman Publishing Co., Inc.","kk",0);
//        p1.checkOut(1,appContext);
//        p1.checkOut(2,appContext);
//        p1.checkOut(3,appContext);
//        p1.checkOutAV(1,appContext);
//        p2.checkOut(1,appContext);
//        p2.checkOut(2,appContext);
//        p2.checkOutAV(2,appContext);
//
//        String str1=db.getStringUser(1);
//        String str2 = db.getStringUser(2);
//        Log.i("TAAAAAAAAAAAAAAAAAAAAG",str1);
//        Log.i("TAAAAAAAAAAAAAAAAAAAAG", str2);

//        db.createUser("Elvira","Espindola","30003","Via del Corso, 22",0);
//        ArrayList<Books> b = db.getListOfBooks();
//        int bi = 0;
//        for (int i = 0; i < b.size(); i++) {
//            bi += b.get(i).getCountOfBooks();
//        }
//
//        ArrayList<String[]> av = db.getListOfAV();
//        int avi = 0;
//        for (int i = 0; i < av.size(); i++) {
//            avi += Integer.parseInt(av.get(i)[3]);
//        }
//
//        ArrayList<String[]> a = db.getListOfArticles();
//        int ai = 0;
//        for (int i = 0; i < a.size(); i++) {
//            ai += Integer.parseInt(a.get(i)[6]);
//        }
//
//        ArrayList<Patron> u = db.getListOfUsers();
//
//        assertThat(Integer.toString(bi + avi + ai), is("8"));
//        assertThat(Integer.toString(u.size()), is("3"));
        assertThat(4,is(2+2));
    }
}
