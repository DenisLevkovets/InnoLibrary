package com.example.niklss.innolib.DataBase;


import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.niklss.innolib.Classes.AV;
import com.example.niklss.innolib.Classes.Articles;
import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.Classes.UserCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static android.content.ContentValues.TAG;


//this class is needed to work with the database
//to copy a database file and connect to it
public class DataBaseHelper extends SQLiteOpenHelper {

    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.niklss.innolib/databases/";
    private static String DB_NAME = "Libary.db";
    private SQLiteDatabase myDataBase;
    private final Context mContext;


    public DataBaseHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        createDataBase();
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     */
    public void createDataBase() throws IOException {
        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     *
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    /**
     * Копирует базу из папки assets заместо созданной локальной БД
     * Выполняется путем копирования потока байтов.
     */
    private void copyDataBase() throws IOException {
        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DB_NAME);
        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;
        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);
        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {

        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        myDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Здесь можно добавить вспомогательные методы для доступа и получения данных из БД
    // вы можете возвращать курсоры через "return myDataBase.query(....)", это облегчит их использование
    // в создании адаптеров для ваших view

    public void createUser(String name, String secondName, String pNumber, String address, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("First_name", name);
        cv.put("Last_name", secondName);
        cv.put("phone", pNumber);
        cv.put("address", address);
        cv.put("status", status);
        db.insert("Users", null, cv);
    }

    public String getStringUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String user = "";
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("user_id"))) {
            user += mCur.getString(mCur.getColumnIndex("First_name")) + " ";
            user += mCur.getString(mCur.getColumnIndex("Last_name")) + " ";
            user += mCur.getString(mCur.getColumnIndex("address")) + " ";
            user += mCur.getString(mCur.getColumnIndex("user_id")) + " ";
            user += mCur.getString(mCur.getColumnIndex("phone")) + " ";
            user += mCur.getString(mCur.getColumnIndex("status"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();

        return user;
    }


    public void addB(String title, String author, int available_copies, int type, int price, int edition, String date, String published_by, String keywords, int is_bestseller) {
        SQLiteDatabase db = this.getWritableDatabase();
        String s = "('" + title + "', '" + author + "', " + available_copies + ", " + type + ", " + price + ", " + edition + ", '" + date + "', '" + published_by + "', '" + keywords + "', " + is_bestseller + ")";


        String addbook = "INSERT INTO Books (title,author,available_copies,type,price,edition,date,published_by,keywords,is_bestseller) Values " + s;
        db.beginTransaction();
        db.execSQL(addbook);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void addArt(String title, String author, String jtitle, String issue, String date, String editor, int numbers, int reference, String keywords, int price) {
        SQLiteDatabase db = this.getWritableDatabase();


        String s = "('" + title + "', '" + author + "', '" + jtitle + "', '" + issue + "', '" + date + "','" + editor + "', " + numbers + ", " + reference + ", '" + keywords + "', " + price + ")";


        String addArticle = "INSERT INTO Articles (title,authors,jtitle,issue,editor,numbers) Values " + s;
        db.beginTransaction();
        db.execSQL(addArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void addAV(String title, String authors, int numbers, String keywords, int price) {
        SQLiteDatabase db = this.getWritableDatabase();


        String s = "('" + title + "', '" + authors + "', " + numbers + ", '" + keywords + "', " + price + ")";


        String addAV = "INSERT INTO AV (title,authors,numbers,keywords,price) Values " + s;
        db.beginTransaction();
        db.execSQL(addAV);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteBook = "DELETE FROM Books WHERE book_id = " + id;
        db.beginTransaction();
        db.execSQL(deleteBook);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void deleteArticle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteArticle = "DELETE FROM Articles WHERE ID = " + id;
        db.beginTransaction();
        db.execSQL(deleteArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        //kkkk

    }

    public void deleteAV(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteAV = "DELETE FROM AV WHERE ID = " + id;
        db.beginTransaction();
        db.execSQL(deleteAV);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void deletePatron(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteUser = "DELETE FROM Users WHERE user_id = " + id;
        db.beginTransaction();
        db.execSQL(deleteUser);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateBook(int id, String title, String author, int available_copies, int type, int price, int edition, String date, String published_by, String keywords, int is_bestseller) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateBooks = "Update Books Set title = '" + title + "', author = '" + author + "', available_copies = " + available_copies + ", type = " + type + ", price = " + price + ", edition = " + edition + ", date = '" + date + "', published_by = '" + published_by + "', keywords = '" + keywords + "', is_bestseller = " + is_bestseller + " where id=" + id;
        db.beginTransaction();
        db.execSQL(updateBooks);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateArticle(int id, String title, String author, String jtitle, String issue, String date, String editor, int numbers, int reference, String keywords, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateArticle = "Update Articles Set title = '" + title + "', authors = '" + author + "', jtitle = '" + jtitle + "', issue = '" + issue + "', date = '" + date + "', editor = '" + editor + ", numbers = " + numbers + ", reference = " + reference + ", keywords = '" + keywords + "', price = " + price + " where id=" + id;
        db.beginTransaction();
        db.execSQL(updateArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateAV(int id, String title, String author, int numbers, String keywords, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateAV = "Update AV Set title = '" + title + "', authors = '" + author + "', numbers = " + numbers + ", keywords = '" + keywords + "', price = " + price + " where id=" + id;
        db.beginTransaction();
        db.execSQL(updateAV);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateUser(int user_id, String First_name, String Last_name, String address, String phone, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateUser = "Update Users Set First_name = '" + First_name + "', Last_name = '" + Last_name + "', address = '" + address + "', phone = " + phone + ", status =" + status + " where user_id=" + user_id;
        db.beginTransaction();
        db.execSQL(updateUser);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public String[] getArrayUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] user = new String[6];
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                user[0] = mCur.getString(mCur.getColumnIndex("First_name"));
                user[1] = mCur.getString(mCur.getColumnIndex("Last_name"));
                user[2] = mCur.getString(mCur.getColumnIndex("address"));
                user[3] = mCur.getString(mCur.getColumnIndex("user_id"));
                user[4] = mCur.getString(mCur.getColumnIndex("phone"));
                user[5] = mCur.getString(mCur.getColumnIndex("status"));
                break;
            }
            mCur.moveToNext();
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();


        return user;
    }

    public ArrayList<Patron> getListOfUsers() throws FileNotFoundException {
        ArrayList<Patron> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> user = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (mCur.getInt(mCur.getColumnIndex("status")) <= 4) {
                user.add(mCur.getString(mCur.getColumnIndex("First_name")));
                user.add(mCur.getString(mCur.getColumnIndex("Last_name")));
                user.add(mCur.getString(mCur.getColumnIndex("address")));
                user.add(mCur.getString(mCur.getColumnIndex("user_id")));
                user.add(mCur.getString(mCur.getColumnIndex("phone")));
                user.add(mCur.getString(mCur.getColumnIndex("status")));
                Patron a = new Patron(user);
                user.clear();
                list.add(a);
            }
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public String[] getArrayBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, author, available_copies, type, price, edition, date, published_by, keywords, is_bestseller, book_id From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] book = new String[11];
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("book_id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("book_id"))) {
            book[0] = mCur.getString(mCur.getColumnIndex("title"));
            book[1] = mCur.getString(mCur.getColumnIndex("author"));
            book[2] = mCur.getString(mCur.getColumnIndex("available_copies"));
            book[3] = mCur.getString(mCur.getColumnIndex("book_id"));
            book[4] = mCur.getString(mCur.getColumnIndex("type"));
            book[5] = mCur.getString(mCur.getColumnIndex("price"));
            book[6] = mCur.getString(mCur.getColumnIndex("edition"));
            book[7] = mCur.getString(mCur.getColumnIndex("date"));
            book[8] = mCur.getString(mCur.getColumnIndex("published_by"));
            book[9] = mCur.getString(mCur.getColumnIndex("keywords"));
            book[10] = mCur.getString(mCur.getColumnIndex("is_bestseller"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return book;
    }

    public String[] getArrayArticle(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, jtitle, issue, date, editor, numbers, id, reference, keywords, price From Articles";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] article = new String[11];
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("id"))) {
            article[0] = mCur.getString(mCur.getColumnIndex("title"));
            article[1] = mCur.getString(mCur.getColumnIndex("authors"));
            article[2] = mCur.getString(mCur.getColumnIndex("jtitle"));
            article[3] = mCur.getString(mCur.getColumnIndex("issue"));
            article[4] = mCur.getString(mCur.getColumnIndex("date"));
            article[5] = mCur.getString(mCur.getColumnIndex("editor"));
            article[6] = mCur.getString(mCur.getColumnIndex("numbers"));
            article[7] = mCur.getString(mCur.getColumnIndex("id"));
            article[8] = mCur.getString(mCur.getColumnIndex("reference"));
            article[9] = mCur.getString(mCur.getColumnIndex("keywords"));
            article[10] = mCur.getString(mCur.getColumnIndex("price"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return article;
    }

    public String[] getArrayAV(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, id, numbers, keywords, price From AV";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] av = new String[6];
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("id"))) {
            av[0] = mCur.getString(mCur.getColumnIndex("title"));
            av[1] = mCur.getString(mCur.getColumnIndex("authors"));
            av[2] = mCur.getString(mCur.getColumnIndex("id"));
            av[3] = mCur.getString(mCur.getColumnIndex("numbers"));
            av[4] = mCur.getString(mCur.getColumnIndex("keywords"));
            av[5] = mCur.getString(mCur.getColumnIndex("price"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return av;
    }

    public ArrayList<Books> getListOfBooks() {
        ArrayList<Books> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, author, available_copies,  book_id, type, price, edition, date, published_by, keywords, is_bestseller From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] book = new String[11];
        while (!mCur.isAfterLast()) {
            book[0] = mCur.getString(mCur.getColumnIndex("title"));
            book[1] = mCur.getString(mCur.getColumnIndex("author"));
            book[2] = mCur.getString(mCur.getColumnIndex("available_copies"));
            book[3] = mCur.getString(mCur.getColumnIndex("book_id"));
            book[4] = mCur.getString(mCur.getColumnIndex("type"));
            book[5] = mCur.getString(mCur.getColumnIndex("price"));
            book[6] = mCur.getString(mCur.getColumnIndex("edition"));
            book[7] = mCur.getString(mCur.getColumnIndex("date"));
            book[8] = mCur.getString(mCur.getColumnIndex("published_by"));
            book[9] = mCur.getString(mCur.getColumnIndex("keywords"));
            book[10] = mCur.getString(mCur.getColumnIndex("is_bestseller"));
            Books b = new Books(book);
            list.add(b);

            mCur.moveToNext();
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public int updateBookData(Books book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", book.getTitleBook());
        cv.put("author", book.getAuthorsOfBook());
        cv.put("available_copies", book.getCountOfBooks());
        cv.put("type", book.getReference());
        cv.put("price", book.getPrice());
        cv.put("edition", book.getEdition());
        cv.put("date", book.getDateOfCreationOfBook());
        cv.put("published_by", book.getPublished_by());
        cv.put("keywords", book.getKeywords());
        cv.put("is_bestseller", book.getIsBestSeller());
        return db.update("Books", cv, "book_id = ?", new String[]{Integer.toString(book.getBookId())});
    }


    public int updateArticleData(Articles article) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", article.getTitle());
        cv.put("authors", article.getAuthors());
        cv.put("jtitle", article.getJtitle());
        cv.put("issue", article.getIssue());
        cv.put("date", article.getDate());
        cv.put("editor", article.getEditor());
        cv.put("numbers", article.getCountArticle());
        cv.put("reference", article.getReference());
        cv.put("keywords", article.getKeywords());
        cv.put("price", article.getPrice());
        return db.update("Articles", cv, "id = ?", new String[]{Integer.toString(article.getArticleId())});
    }

    public int updateAvData(AV av) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", av.getTitle());
        cv.put("authors", av.getAuthors());
        cv.put("numbers", av.getCountAv());
        cv.put("keywords", av.getKeywords());
        cv.put("price", av.getPrice());
        return db.update("AV", cv, "id = ?", new String[]{Integer.toString(av.getAvId())});
    }

    public int updateUserData(UserCard user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("First_name", user.getuName());
        cv.put("Last_name", user.getSecondName());
        cv.put("phone", user.getuNumber());
        cv.put("address", user.getuAddress());
        cv.put("status", user.getUsersType());
        return db.update("Users", cv, "user_id = ?", new String[]{Integer.toString(user.getuId())});
    }

    public boolean hasBook(int user_id, int book_id, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (user_id == mCur.getInt(mCur.getColumnIndex("user_id")) && book_id == mCur.getInt(mCur.getColumnIndex("book_id")) && type == mCur.getInt(mCur.getColumnIndex("type"))) {
                return true;
            }
            mCur.moveToNext();
        }
        mCur.close();
        return false;
    }

    public void updateTimeChecker(int user_id, int book_id, String time, int type, int renewed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        cv.put("book_id", book_id);
        cv.put("time", time);
        cv.put("type", type);
        cv.put("renewed", renewed);
        db.insert("time_checker", null, cv);
    }

    public boolean wasRenewed(int user_id, int book_id, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type, renewed From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (user_id == mCur.getInt(mCur.getColumnIndex("user_id")) && book_id == mCur.getInt(mCur.getColumnIndex("book_id")) && type == mCur.getInt(mCur.getColumnIndex("type")) && 1 == mCur.getInt(mCur.getColumnIndex("renewed"))) {
                return true;
            }
            mCur.moveToNext();
        }
        return false;
    }

    private int findDifDays(String d1, String d2) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(d1);
            date2 = format.parse(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long difference = date1.getTime() - date2.getTime();
        long days = difference / (24 * 60 * 60 * 1000);
        return (int) days;
    }

    public ArrayList<Books> returnListOfUsersBook(int uId) {
        ArrayList<Books> book = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id")) && mCur.getInt(mCur.getColumnIndex("type")) == 0) {
                Books b = new Books(this.getArrayBook(mCur.getInt(mCur.getColumnIndex("book_id"))));
                String d1 = mCur.getString(mCur.getColumnIndex("time"));
                Calendar c = new GregorianCalendar();
                String d2 = c.get(Calendar.DAY_OF_MONTH) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.YEAR);
                b.setOverDue(false);
                b.setFine(0);
                b.setDaysLeft(findDifDays(d1, d2));
                if (findDifDays(d1, d2) < 0) {
                    b.setDaysLeft(0);
                    if (b.getPrice() < findDifDays(d1, d2) * -100) {
                        b.setFine(b.getPrice());
                    } else {
                        b.setFine(findDifDays(d1, d2) * -100);
                    }
                    b.setOverDue(true);
                }
                book.add(b);
            }
            mCur.moveToNext();
        }
        mCur.close();
        return book;
    }

    public ArrayList<Articles> returnListOfUsersArticles(int uId) {
        ArrayList<Articles> articles = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker;";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id")) && mCur.getInt(mCur.getColumnIndex("type")) == 1) {
                Articles a = new Articles(this.getArrayArticle(mCur.getInt(mCur.getColumnIndex("book_id"))));
                String d1 = mCur.getString(mCur.getColumnIndex("time"));
                Calendar c = new GregorianCalendar();
                String d2 = c.get(Calendar.DAY_OF_MONTH) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.YEAR);
                a.setOverDue(false);
                a.setFine(0);
                a.setDaysLeft(findDifDays(d1, d2));
                if (findDifDays(d1, d2) < 0) {
                    a.setDaysLeft(0);
                    if (a.getPrice() < findDifDays(d1, d2) * -100) {
                        a.setFine(a.getPrice());
                    } else {
                        a.setFine(findDifDays(d1, d2) * -100);
                    }
                    a.setOverDue(true);
                }
                articles.add(a);
            }
            mCur.moveToNext();
        }
        mCur.close();
        return articles;
    }

    public ArrayList<AV> returnListOfUsersAv(int uId) {
        ArrayList<AV> av = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker;";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id")) && mCur.getInt(mCur.getColumnIndex("type")) == 2) {
                AV a = new AV(this.getArrayAV(mCur.getInt(mCur.getColumnIndex("book_id"))));
                String d1 = mCur.getString(mCur.getColumnIndex("time"));
                Calendar c = new GregorianCalendar();
                String d2 = c.get(Calendar.DAY_OF_MONTH) + "." + c.get(Calendar.MONTH) + "." + c.get(Calendar.YEAR);
                a.setOverDue(false);
                a.setFine(0);
                a.setDaysLeft(findDifDays(d1, d2));
                if (findDifDays(d1, d2) < 0) {
                    a.setDaysLeft(0);
                    if (a.getPrice() < findDifDays(d1, d2) * -100) {
                        a.setFine(a.getPrice());
                    } else {
                        a.setFine(findDifDays(d1, d2) * -100);
                    }
                    a.setFine(findDifDays(d1, d2) * -100);
                    a.setOverDue(true);
                }
                av.add(a);
            }
            mCur.moveToNext();
        }
        mCur.close();
        return av;
    }

    public String getShortInformation(Books book) {
        String a = "";
        a += book.getTitleBook() + " ";
        a += book.getAuthorsOfBook() + " ";
        a += book.getEdition() + " ";
        return a;
    }

    public String getFullInformation(Books book) {
        String a = "Title: ";
        a += book.getTitleBook() + "\nAuthors:  ";
        a += book.getAuthorsOfBook() + "\nEdition: ";
        a += book.getEdition() + "\nDate: ";
        a += book.getDateOfCreationOfBook() + "\nPublished by: ";
        a += book.getPublished_by() + "\nCount: ";
        a += book.getCountOfBooks() + "\nIs bestseller: ";
        if (book.getIsBestSeller() == 0) a += "Yes";
        else a += "No";
        a += "\nPrice: ";
        a += book.getPrice() + " ";

        return a;
    }

    public ArrayList<Patron> debtorUsers() throws FileNotFoundException {
        ArrayList<Patron> patron = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            boolean contains = false;
            for (int i = 0; i < patron.size(); i++) {
                if (patron.get(i).getuId() == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                    contains = true;
                }
            }

            if (!contains) {
                Patron a = new Patron(this.getArrayUser(mCur.getInt(mCur.getColumnIndex("user_id"))));
                patron.add(a);
            }
            mCur.moveToNext();
        }
        mCur.close();
        return patron;
    }

    public ArrayList<String> getAVMaterial(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, id, numbers,keywords,price From AV";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("id"))) {
                list.add(mCur.getString(mCur.getColumnIndex("title")));
                list.add(mCur.getString(mCur.getColumnIndex("authors")));
                list.add(mCur.getString(mCur.getColumnIndex("id")));
                list.add(mCur.getString(mCur.getColumnIndex("numbers")));
                list.add(mCur.getString(mCur.getColumnIndex("keywords")));
                list.add(mCur.getString(mCur.getColumnIndex("price")));
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return list;
    }

    public ArrayList<String> getArticleMaterial(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, jtitle, issue, date, editor, numbers, id, reference,keywords,price From Articles";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("id"))) {
                list.add(mCur.getString(mCur.getColumnIndex("title")));
                list.add(mCur.getString(mCur.getColumnIndex("authors")));
                list.add(mCur.getString(mCur.getColumnIndex("jtitle")));
                list.add(mCur.getString(mCur.getColumnIndex("issue")));
                list.add(mCur.getString(mCur.getColumnIndex("date")));
                list.add(mCur.getString(mCur.getColumnIndex("editor")));
                list.add(mCur.getString(mCur.getColumnIndex("numbers")));
                list.add(mCur.getString(mCur.getColumnIndex("id")));
                list.add(mCur.getString(mCur.getColumnIndex("reference")));
                list.add(mCur.getString(mCur.getColumnIndex("keywords")));
                list.add(mCur.getString(mCur.getColumnIndex("price")));
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return list;
    }

    //
    public void clearDataBase() {
        SQLiteDatabase db = this.getWritableDatabase();
        /*String deletebook = "DELETE FROM BOOKS;";
        String deleteuser = "DELETE FROM USERS;";
        String deleteAV = "DELETE FROM AV;";*/
        /*String deleteArticles = "DELETE FROM ARTICLES;";*/
        String deleteTimeChecker = "DELETE FROM time_checker;";
        String deleteQueue = "DELETE FROM Queue;";
        String deleteLogin = "DELETE FROM Login;";
        String deleteUserID = "DELETE FROM UserId;";
        String deleteOutstanding = "DELETE FROM outstanding;";

        db.beginTransaction();
        db.execSQL(deleteLogin);
        db.execSQL(deleteUserID);
        db.execSQL(deleteOutstanding);
        db.execSQL(deleteTimeChecker);
        db.execSQL(deleteQueue);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public ArrayList<AV> getListOfAV() {
        ArrayList<AV> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, id, numbers,keywords,price From AV";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] av = new String[6];
        while (!mCur.isAfterLast()) {
            av[0] = (mCur.getString(mCur.getColumnIndex("title")));
            av[1] = (mCur.getString(mCur.getColumnIndex("authors")));
            av[2] = (mCur.getString(mCur.getColumnIndex("id")));
            av[3] = (mCur.getString(mCur.getColumnIndex("numbers")));
            av[4] = (mCur.getString(mCur.getColumnIndex("keywords")));
            av[5] = (mCur.getString(mCur.getColumnIndex("price")));
            list.add(new AV(av));
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public ArrayList<Articles> getListOfArticles() {
        ArrayList<Articles> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, jtitle, issue, date, editor, numbers, id, reference,keywords,price From Articles";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] av = new String[11];
        while (!mCur.isAfterLast()) {
            av[0] = (mCur.getString(mCur.getColumnIndex("title")));
            av[1] = (mCur.getString(mCur.getColumnIndex("authors")));
            av[2] = (mCur.getString(mCur.getColumnIndex("jtitle")));
            av[3] = (mCur.getString(mCur.getColumnIndex("issue")));
            av[4] = (mCur.getString(mCur.getColumnIndex("date")));
            av[5] = (mCur.getString(mCur.getColumnIndex("editor")));
            av[6] = (mCur.getString(mCur.getColumnIndex("numbers")));
            av[7] = (mCur.getString(mCur.getColumnIndex("id")));
            av[8] = (mCur.getString(mCur.getColumnIndex("reference")));
            av[9] = (mCur.getString(mCur.getColumnIndex("keywords")));
            av[10] = (mCur.getString(mCur.getColumnIndex("price")));
            list.add(new Articles(av));
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public Integer getCountOfOverDueBooks(ArrayList<Books> books) {
        int count = 0;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).isOverDue()) count++;
        }
        return count;
    }

    public Integer getCountOfOverDueArticles(ArrayList<Articles> articles) {
        int count = 0;
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).isOverDue()) count++;
        }
        return count;

    }

    public Integer getCountOfOverDueAV(ArrayList<AV> av) {
        int count = 0;
        for (int i = 0; i < av.size(); i++) {
            if (av.get(i).isOverDue()) count++;
        }
        return count;
    }

    public boolean noOneInQueue(int id, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery;
        mQuery = "SELECT document_id, document_type From Queue ";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("document_id")) && type == mCur.getInt(mCur.getColumnIndex("document_type"))) {
                list.add(mCur.getString(mCur.getColumnIndex("document_id")));
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();

        return list.size() == 0;
    }

    public UserCard getUser() throws FileNotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery;
        mQuery = "SELECT user_id From UserId ";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        Patron a = new Patron(getArrayUser(mCur.getInt(mCur.getColumnIndex("user_id"))));
        mCur.close();

        return a;
    }

    private void setUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from UserId");
        ContentValues cv = new ContentValues();
        cv.put("user_id", id);

        db.insert("UserId", null, cv);
    }

    public boolean Login(String login, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery;
        mQuery = "SELECT login, password, id  From Login";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (mCur.getString(mCur.getColumnIndex("login")).equals(login) && mCur.getString(mCur.getColumnIndex("password")).equals(password)) {
                setUser(mCur.getInt(mCur.getColumnIndex("id")));
                return true;
            }
        }
        mCur.close();
        return false;
    }
    //

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void standInQueue(Patron patron, int doc_id, int type_of_material) {

        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id,user_type, document_id,document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<int[]> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            int[] arr = new int[4];
            arr[0] = (mCur.getInt(mCur.getColumnIndex("user_id")));
            arr[1] = (mCur.getInt(mCur.getColumnIndex("user_type")));
            arr[2] = (mCur.getInt(mCur.getColumnIndex("document_id")));
            arr[3] = (mCur.getInt(mCur.getColumnIndex("document_type")));


            list.add(arr);

            mCur.moveToNext();
        }
        mCur.moveToFirst();

        mCur.close();
        int[] e = new int[4];
        e[0] = patron.getuId();
        e[1] = patron.getUsersType();
        e[2] = doc_id;
        e[3] = type_of_material;
        list.add(e);
        list.sort((a, b) -> Integer.compare(a[1], b[1]));

        db.execSQL("DELETE FROM Queue");
        for (int i = 0; i < list.size(); i++) {
            String cc = "INSERT INTO Queue (user_id,user_type,document_id,document_type) VALUES (" + list.get(i)[0] + ", " + list.get(i)[1] + ", " + list.get(i)[2] + ", " + list.get(i)[3] + ");";
            db.execSQL(cc);
        }

        db.close();
    }

    public String getArticleInfoShort(Articles article) {
        return article.getJtitle() + " " + article.getAuthors() + " " + article.getTitle();
    }

    public String getArticleInfoFull(Articles article) {
        return "Journal: " + article.getJtitle() + "\nAuthors: " + article.getAuthors() + "\nTitle: " + article.getTitle() + "\nIssue: "
                + article.getIssue() + "\nDate: " + article.getDate() + "\nEditor: " + article.getEditor() + "\nNumber: " + article.getCountArticle();
    }

    public String getAVInfoShort(AV av) {
        return av.getTitle() + " " + av.getAuthors();
    }

    public String getAVInfoFull(AV av) {
        return "Title: " + av.getTitle() + "\nAuthors: " + av.getAuthors() + "\nNumber: " + av.getCountAv();
    }

    public String getUserInfoShort(Patron user) {
        return user.getuName() + " " + user.getSecondName() + " " + user.getuNumber();
    }

    public String getUserInfoFull(Patron user) {
        return "Name: " + user.getuName() + "\nSurname: " + user.getSecondName() + "\nAddress: " + user.getuAddress() + "\nNumber: " + user.getuNumber();
    }

    public ArrayList<Books> waitingListOfBooks(int uId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Books> docs = new ArrayList<>();
        String mQuery = "SELECT user_id, user_type, document_id, document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                if (mCur.getInt(mCur.getColumnIndex("document_type")) == 0) {
                    Books b = new Books(getArrayBook(mCur.getInt(mCur.getColumnIndex("document_id"))));
                    docs.add(b);
                }


            }
            mCur.moveToNext();
        }

        mCur.close();

        return docs;

    }

    public ArrayList<Articles> waitingListOfArticles(int uId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Articles> docs = new ArrayList<>();
        String mQuery = "SELECT user_id, user_type, document_id, document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();

        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                if (mCur.getInt(mCur.getColumnIndex("document_type")) == 1) {
                    Articles b = new Articles(getArrayArticle(mCur.getInt(mCur.getColumnIndex("document_id"))));
                    docs.add(b);
                }


            }
            mCur.moveToNext();
        }

        mCur.close();

        return docs;

    }

    public ArrayList<AV> waitingListOfAV(int uId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<AV> docs = new ArrayList<>();
        String mQuery = "SELECT user_id, user_type, document_id, document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();

        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                if (mCur.getInt(mCur.getColumnIndex("document_type")) == 2) {
                    AV b = new AV(getArrayAV(mCur.getInt(mCur.getColumnIndex("document_id"))));
                    docs.add(b);
                }


            }
            mCur.moveToNext();
        }

        mCur.close();

        return docs;

    }

    public int daysLeft(int user_id, int doc_id, int doc_type) {
        SQLiteDatabase db = this.getWritableDatabase();
        String date = null;
        String date_time = null;
        int daysLeft;
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();

        while (!mCur.isAfterLast()) {
            if (user_id == mCur.getInt(mCur.getColumnIndex("user_id")) && doc_id == mCur.getInt(mCur.getColumnIndex("book_id")) && doc_type == mCur.getInt(mCur.getColumnIndex("type"))) {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                date = mCur.getString(mCur.getColumnIndex("time"));


                Date date1 = new Date(System.currentTimeMillis());

                SimpleDateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
                date_time = formatter1.format(date1);
                daysLeft = findDifDays(date, date_time);

            }
            mCur.moveToNext();
        }
        daysLeft = findDifDays(date, date_time);

        mCur.close();
        return daysLeft;
    }

    public void outstanding_request(int document_id, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("DELETE FROM Queue WHERE document_id = " + document_id + " and document_type = " + type + ";");
        db.execSQL("INSERT INTO outstanding (document_id,document_type) Values" + "(" + document_id + ", " + type + ")");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();


    }

    public boolean findOutstandingBook(int book_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT document_id, document_type From outstanding";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        boolean a = false;
        while (!mCur.isAfterLast()) {
            if (book_id == mCur.getInt(mCur.getColumnIndex("document_id")) && mCur.getInt(mCur.getColumnIndex("document_type")) == 0) {
                a = true;
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return a;

    }

    public ArrayList<Books> checkOutstandingBooks(int user_id) {
        ArrayList<Books> b = new ArrayList<>();

        ArrayList<Books> books = new ArrayList<>();
        b = returnListOfUsersBook(user_id);

        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) != null) {
                if (findOutstandingBook(b.get(i).getBookId())) {
                    books.add(b.get(i));
                }
            }
        }
        return books;
    }

    public boolean findOutstandingArticle(int article_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT document_id, document_type From outstanding";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        boolean a = false;
        while (!mCur.isAfterLast()) {
            if (article_id == mCur.getInt(mCur.getColumnIndex("document_id")) && mCur.getInt(mCur.getColumnIndex("document_type")) == 1) {
                a = true;
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return a;

    }

    public ArrayList<Articles> checkOutstandingArticles(int user_id) {
        ArrayList<Articles> a = new ArrayList<>();

        ArrayList<Articles> articles = new ArrayList<>();
        a = returnListOfUsersArticles(user_id);

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != null) {
                if (findOutstandingArticle(a.get(i).getArticleId())) {
                    articles.add(a.get(i));
                }
            }
        }
        return articles;
    }

    public boolean findOutstandingAV(int av_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT document_id, document_type From outstanding";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        boolean a = false;
        while (!mCur.isAfterLast()) {
            if (av_id == mCur.getInt(mCur.getColumnIndex("document_id")) && mCur.getInt(mCur.getColumnIndex("document_type")) == 2) {
                a = true;
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return a;

    }

    public ArrayList<AV> checkOutstandingAV(int user_id) {
        ArrayList<AV> a = new ArrayList<>();

        ArrayList<AV> av = new ArrayList<>();
        a = returnListOfUsersAv(user_id);

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != null) {
                if (findOutstandingArticle(a.get(i).getAvId())) {
                    av.add(a.get(i));
                }
            }
        }
        return av;
    }

    public void returnBook(int user_id, int book_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("DELETE FROM time_checker WHERE book_id = " + book_id + " and type = " + 0 + " and user_id = " + user_id + ";");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Books b = new Books(getArrayBook(book_id));
        returnBookerr(b, book_id);
    }

    public void returnBookerr(Books b, int book_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String updateBooks = "Update Books Set title = '" + b.getTitleBook() + "', author = '" + b.getAuthorsOfBook() + "', available_copies = " + (b.getCountOfBooks() + 1) + ", type = " + b.getReference() + ", price = " + b.getPrice() + ", edition = " + b.getEdition() + ", date = '" + b.getDateOfCreationOfBook() + "', published_by = '" + b.getPublished_by() + "', keywords = '" + b.getKeywords() + "', is_bestseller = " + b.getIsBestSeller() + " where id=" + book_id;
        db.execSQL(updateBooks);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void returnArticle(int user_id, int article_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("DELETE FROM time_checker WHERE book_id = " + article_id + " and type = " + 1 + " and user_id = " + user_id + ";");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Articles a = new Articles(getArrayArticle(article_id));
        returnArticleerr(a, article_id);
    }

    public void returnArticleerr(Articles a, int article_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String updateArticle = "Update Articles Set title = '" + a.getTitle() + "', authors = '" + a.getAuthors() + "', jtitle = '" + a.getJtitle() + "', issue = '" + a.getIssue() + "', date = '" + a.getDate() + "', editor = '" + a.getEditor() + ", numbers = " + (a.getCountArticle() + 1) + ", reference = " + a.getReference() + ", keywords = '" + a.getKeywords() + "', price = " + a.getPrice() + " where id=" + article_id;
        db.execSQL(updateArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void returnAV(int user_id, int av_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        db.execSQL("DELETE FROM time_checker WHERE book_id = " + av_id + " and type = " + 2 + " and user_id = " + user_id + ";");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        AV a = new AV(getArrayAV(av_id));
        returnAVerr(a, av_id);
    }

    public void returnAVerr(AV a, int av_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String updateAV = "Update AV Set title = '" + a.getTitle() + "', authors = '" + a.getAuthors() + "', numbers = " + (a.getCountAv() + 1) + ", keywords = '" + a.getKeywords() + "', price = " + a.getPrice() + " where id=" + av_id;
        db.execSQL(updateAV);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public ArrayList<String> showQueue() throws FileNotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> people = new ArrayList<>();
        String mQuery = "SELECT user_id, user_type, document_id, document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();

        while (!mCur.isAfterLast()) {

            Patron b = new Patron(getArrayUser(mCur.getInt(mCur.getColumnIndex("user_id"))));

            people.add(b.getuName() + " " + mCur.getInt(mCur.getColumnIndex("document_id")) + " " + mCur.getInt(mCur.getColumnIndex("document_type")));


            mCur.moveToNext();
        }

        mCur.close();

        return people;

    }

    public ArrayList<Patron> usersForBook(int id, int type) throws FileNotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Patron> people = new ArrayList<>();
        String mQuery = "SELECT user_id, user_type, document_id, document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();

        while (!mCur.isAfterLast()) {
            if (mCur.getInt(mCur.getColumnIndex("document_id")) == id && mCur.getInt(mCur.getColumnIndex("document_type")) == type) {
                people.add(new Patron(getArrayUser(mCur.getInt(mCur.getColumnIndex("user_id")))));
            }
            mCur.moveToNext();
        }
        mCur.close();
        return people;
    }

    public void out(String output) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        FileWriter out = new FileWriter(new File(mContext.getFilesDir().getPath() + "Log"), true);
        out.write(output + "\n");
        out.close();
    }

    public String inp() throws FileNotFoundException {
        SQLiteDatabase db = this.getWritableDatabase();
        Scanner in = new Scanner(new File(mContext.getFilesDir().getPath() + "Log"));
        String output = "";
        while (in.hasNextLine()) {
            output += in.nextLine() + "\n";
        }
        in.close();
        return output;
    }
}