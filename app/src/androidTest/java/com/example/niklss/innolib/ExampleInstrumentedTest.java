package com.example.niklss.innolib;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.niklss.innolib.Classes.Admin;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Librarian1;
import com.example.niklss.innolib.Classes.Librarian2;
import com.example.niklss.innolib.Classes.Librarian3;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.Classes.UserCard;
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
//
//    @Test
//    public void Test3() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        db.clearDataBase();
//
//        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);
//
//        String[] info1 = {"Eugenia", "Rama", "", ""};
//        admin.addLibrarian(info1, 1, appContext);
//        String[] info2 = {"Luie", "Ramos", "", ""};
//        admin.addLibrarian(info2, 2, appContext);
//        String[] info3 = {"Ramon", "Valdez", "", ""};
//        admin.addLibrarian(info3, 3, appContext);
//
//        ArrayList<UserCard> lib = db.getListOfLibrarians();
//        Librarian1 lib1 = new Librarian1(lib.get(0));
//        Librarian2 lib2 = new Librarian2(lib.get(1));
//        Librarian3 lib3 = new Librarian3(lib.get(2));
//        lib1.addBook();
//    }

    @Test
    public void Test4() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        assertThat(users.size(), is(5));
        assertThat(books.size(), is(3));
    }

    @Test
    public void Test5() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        book1.setCountOfBooks(book1.getCountOfBooks() - 1);

        lib3.modifyBook(book1, appContext);

        assertThat(db.getListOfBooks().get(0).getCountOfBooks(), is(2));
    }

//    @Test
//    public void Test6() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        db.clearDataBase();
//
//        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);
//
//        String[] info1 = {"Eugenia", "Rama", "", ""};
//        String[] info2 = {"Luie", "Ramos", "", ""};
//        String[] info3 = {"Ramon", "Valdez", "", ""};
//        admin.addLibrarian(info1, 1, appContext);
//        admin.addLibrarian(info2, 2, appContext);
//        admin.addLibrarian(info3, 3, appContext);
//        ArrayList<UserCard> lib = db.getListOfLibrarians();
//        Librarian1 lib1 = new Librarian1(lib.get(0));
//        Librarian2 lib2 = new Librarian2(lib.get(1));
//        Librarian3 lib3 = new Librarian3(lib.get(2));
//
//        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
//        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
//        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
//        lib2.addBook(d1, appContext);
//        lib2.addBook(d2, appContext);
//        lib2.addBook(d3, appContext);
//        ArrayList<Books> books = db.getListOfBooks();
//        Books book1 = books.get(0);
//        Books book2 = books.get(1);
//        Books book3 = books.get(2);
//
//        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
//        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
//        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
//        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
//        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};
//
//        lib2.addPatron(p1, appContext);
//        lib2.addPatron(p2, appContext);
//        lib2.addPatron(p3, appContext);
//        lib2.addPatron(s, appContext);
//        lib2.addPatron(v, appContext);
//
//        ArrayList<Patron> users = db.getListOfUsers();
//
//        Patron patron1 = users.get(0);
//        Patron patron2 = users.get(1);
//        Patron patron3 = users.get(2);
//        Patron student = users.get(3);
//        Patron visitor = users.get(4);
//
//        patron1.checkOut(book3.getBookId(), appContext);
//        patron2.checkOut(book3.getBookId(), appContext);
//        student.checkOut(book3.getBookId(), appContext);
//        visitor.checkOut(book3.getBookId(), appContext);
//        patron3.checkOut(book3.getBookId(), appContext);
//
//        lib1.placeAnOutStandingRequest();
//    }

    @Test
    public void Test7() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        patron1.checkOut(book3.getBookId(), appContext);
        patron2.checkOut(book3.getBookId(), appContext);
        student.checkOut(book3.getBookId(), appContext);
        visitor.checkOut(book3.getBookId(), appContext);
        patron3.checkOut(book3.getBookId(), appContext);

        lib3.placeAnOutStandingRequest(book3.getBookId(), book3.getTypeOfMaterial(), appContext);

        assertThat(db.showQueue().size(), is(0));
    }

    @Test
    public void Test8() throws Exception {
//        Context appContext = InstrumentationRegistry.getTargetContext();
//        DataBaseHelper db = new DataBaseHelper(appContext);
//        db.clearDataBase();
//
//        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);
//
//        String[] info1 = {"Eugenia", "Rama", "", ""};
//        String[] info2 = {"Luie", "Ramos", "", ""};
//        String[] info3 = {"Ramon", "Valdez", "", ""};
//        admin.addLibrarian(info1, 1, appContext);
//        admin.addLibrarian(info2, 2, appContext);
//        admin.addLibrarian(info3, 3, appContext);
//        ArrayList<UserCard> lib = db.getListOfLibrarians();
//        Librarian1 lib1 = new Librarian1(lib.get(0));
//        Librarian2 lib2 = new Librarian2(lib.get(1));
//        Librarian3 lib3 = new Librarian3(lib.get(2));
//
//        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
//        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
//        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
//        lib2.addBook(d1, appContext);
//        lib2.addBook(d2, appContext);
//        lib2.addBook(d3, appContext);
//        ArrayList<Books> books = db.getListOfBooks();
//        Books book1 = books.get(0);
//        Books book2 = books.get(1);
//        Books book3 = books.get(2);
//
//        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
//        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
//        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
//        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
//        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};
//
//        lib2.addPatron(p1, appContext);
//        lib2.addPatron(p2, appContext);
//        lib2.addPatron(p3, appContext);
//        lib2.addPatron(s, appContext);
//        lib2.addPatron(v, appContext);
//
//        ArrayList<Patron> users = db.getListOfUsers();
//
//        Patron patron1 = users.get(0);
//        Patron patron2 = users.get(1);
//        Patron patron3 = users.get(2);
//        Patron student = users.get(3);
//        Patron visitor = users.get(4);
//
//        patron1.checkOut(book3.getBookId(), appContext);
//        patron2.checkOut(book3.getBookId(), appContext);
//        student.checkOut(book3.getBookId(), appContext);
//        visitor.checkOut(book3.getBookId(), appContext);
//        patron3.checkOut(book3.getBookId(), appContext);
//
//        lib1.placeAnOutStandingRequest();
//        assertThat(admin.checkLog(appContext), is(""));
    }

    @Test
    public void Test9() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        patron1.checkOut(book3.getBookId(), appContext);
        patron2.checkOut(book3.getBookId(), appContext);
        student.checkOut(book3.getBookId(), appContext);
        visitor.checkOut(book3.getBookId(), appContext);
        patron3.checkOut(book3.getBookId(), appContext);

        lib3.placeAnOutStandingRequest(book3.getBookId(), book3.getTypeOfMaterial(), appContext);

        assertThat(admin.checkLog(appContext), is(""));
    }



    @Test
    public void Test10() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        ArrayList<Books> searchBook = visitor.searchBook("Introduction to Algorithms", "", 0, "", "", "", "", "", 0, appContext);

        assertThat(searchBook.get(0).getTitleBook(), is("Introduction to Algorithms"));
    }

    @Test
    public void Test11() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        ArrayList<Books> searchBook = visitor.searchBook("Algorithms", "", 0, "", "", "", "", "", 0, appContext);

        assertThat(searchBook.get(0).getTitleBook(), is("Introduction to Algorithms"));
        assertThat(searchBook.get(1).getTitleBook(), is("Algorithms + Data Structures = Programs"));
    }

//    @Test
    public void Test12() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataBaseHelper db = new DataBaseHelper(appContext);
        db.clearDataBase();

        Admin admin = new Admin("Albert", "Solovyov", "Address", 0, "Num", 8);

        String[] info1 = {"Eugenia", "Rama", "", ""};
        String[] info2 = {"Luie", "Ramos", "", ""};
        String[] info3 = {"Ramon", "Valdez", "", ""};
        admin.addLibrarian(info1, 1, appContext);
        admin.addLibrarian(info2, 2, appContext);
        admin.addLibrarian(info3, 3, appContext);
        ArrayList<UserCard> lib = db.getListOfLibrarians();
        Librarian1 lib1 = new Librarian1(lib.get(0));
        Librarian2 lib2 = new Librarian2(lib.get(1));
        Librarian3 lib3 = new Librarian3(lib.get(2));

        String[] d1 = {"Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein", "3", "0", "5000", "3", "2009", "MIT Press", "Algorithms, Data Structures, Complexity, Computational Theory", "0"};
        String[] d2 = {"Algorithms + Data Structures = Programs", "Niklaus Wirth", "3", "0", "5000", "1", "1978", "Prentice Hall PTR", "Algorithms, Data Structures, Search Algorithms, Pascal", "0"};
        String[] d3 = {"The Art of Computer Programming", "Donald E. Knuth", "3", "0", "5000", "3", "1997", "Addison Wesley Longman Publishing Co., Inc.", "Algorithms, Combinatorial Algorithms, Recursion", "0"};
        lib2.addBook(d1, appContext);
        lib2.addBook(d2, appContext);
        lib2.addBook(d3, appContext);
        ArrayList<Books> books = db.getListOfBooks();
        Books book1 = books.get(0);
        Books book2 = books.get(1);
        Books book3 = books.get(2);

        String[] p1 = {"Sergey", "Afonso", "30001", "Via Margutta, 3", "4"};
        String[] p2 = {"Nadia", "Teixeira", "30002", "Via Sacra, 13", "4"};
        String[] p3 = {"Elvira", "Espindola", "30003", "Via del Corso, 22", "4"};
        String[] s = {"Andrey", "Velo", "30004", ": Avenida Mazatlan 250", "0"};
        String[] v = {"Veronika", "Rama", "30005", ": Stret Atocha, 27", "3"};

        lib2.addPatron(p1, appContext);
        lib2.addPatron(p2, appContext);
        lib2.addPatron(p3, appContext);
        lib2.addPatron(s, appContext);
        lib2.addPatron(v, appContext);

        ArrayList<Patron> users = db.getListOfUsers();

        Patron patron1 = users.get(0);
        Patron patron2 = users.get(1);
        Patron patron3 = users.get(2);
        Patron student = users.get(3);
        Patron visitor = users.get(4);

        ArrayList<Books> searchBook = visitor.searchBook("", "", 0, "", "", "", "", "Algorithms", 0, appContext);

        assertThat(searchBook.get(0).getTitleBook(), is("Introduction to Algorithms"));
        assertThat(searchBook.get(1).getTitleBook(), is("Algorithms + Data Structures = Programs"));
        assertThat(searchBook.get(2).getTitleBook(), is("The Art of Computer Programming"));
    }

    @Test
    public void Test13() throws Exception {
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
