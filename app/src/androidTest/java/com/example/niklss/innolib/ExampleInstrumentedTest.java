package com.example.niklss.innolib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.niklss.innolib.Classes.Admin;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

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
    public void Test1() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        String res = db.createUser("Name", "Surname", "Numb", "Address", 8);
        assertThat(res, is("Cant create an admin"));
    }

    @Test
    public void Test2() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        String[] info2 = {"Luie", "Ramos", "", ""};
        admin.addLibrarian(info2, 2, appContext);
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info3, 3, appContext);

        assertThat(db.getListOfLibrarians().size(), is(3));
    }

    @Test
    public void Test3() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        Patron p1 = db.getListOfUsers().get(0);
        Books b1 = db.getListOfBooks().get(0);
        Books b2 = db.getListOfBooks().get(1);

        p1.checkOut(b1.getBookId(), appContext);
        p1.checkOut(b2.getBookId(), appContext);

        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
        assertThat(b.get(0).getFine(), is(0));
        assertThat(b.get(0).getDaysLeft(), is(59));
    }

//    @Test
//    public void Test4() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test5() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test6() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test7() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test8() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test9() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test10() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test11() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test12() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test13() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }
//    @Test
//    public void Test14() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        Patron p1 = db.getListOfUsers().get(0);
//        Books b1 = db.getListOfBooks().get(0);
//        Books b2 = db.getListOfBooks().get(1);
//
//        p1.checkOut(b1.getBookId(), appContext);
//        p1.checkOut(b2.getBookId(), appContext);
//
//        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
//        assertThat(b.get(0).getFine(), is(0));
//        assertThat(b.get(0).getDaysLeft(), is(59));
//    }

}
