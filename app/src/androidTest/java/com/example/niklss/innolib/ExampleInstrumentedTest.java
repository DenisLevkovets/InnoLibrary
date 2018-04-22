package com.example.niklss.innolib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.DataBase.DataBaseHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.PrintWriter;
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
        Patron p1 = db.getListOfUsers().get(0);
        Books b1 = db.getListOfBooks().get(0);
        Books b2 = db.getListOfBooks().get(1);

        p1.checkOut(b1.getBookId(), appContext);
        p1.checkOut(b2.getBookId(), appContext);

        ArrayList<Books> b = p1.getListOfUsersBook(appContext);
        assertThat(b.get(0).getFine(), is(0));
        assertThat(b.get(0).getDaysLeft(), is(59));
    }

}
