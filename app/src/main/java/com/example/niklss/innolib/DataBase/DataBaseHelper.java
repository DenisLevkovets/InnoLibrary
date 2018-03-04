package com.example.niklss.innolib.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.Classes.UserCard;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


//this class is needed to work with the database
//to copy a database file and connect to it
public class DataBaseHelper extends SQLiteOpenHelper {

    // путь к базе данных вашего приложения
    private static String DB_PATH = "/data/data/com.example.niklss.innolib/databases/";
    private static String DB_NAME = "Libary.db";
    private SQLiteDatabase myDataBase;
    private final Context mContext;

    /**
     * Конструктор
     * Принимает и сохраняет ссылку на переданный контекст для доступа к ресурсам приложения
     *
     * @param context - usually activity
     */

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 3);
        this.mContext = context;
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //ничего не делать - база уже есть
        } else {
            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Проверяет, существует ли уже эта база, чтобы не копировать каждый раз при запуске приложения
     *
     * @return true если существует, false если не существует
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //база еще не существует
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
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
        //открываем БД
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
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

    public String[] getArrayUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] user = new String[6];
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("user_id"))) {
            user[0] = mCur.getString(mCur.getColumnIndex("First_name"));
            user[1] = mCur.getString(mCur.getColumnIndex("Last_name"));
            user[2] = mCur.getString(mCur.getColumnIndex("address"));
            user[3] = mCur.getString(mCur.getColumnIndex("user_id"));
            user[4] = mCur.getString(mCur.getColumnIndex("phone"));
            user[5] = mCur.getString(mCur.getColumnIndex("status"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();


        return user;
    }

    public ArrayList<Patron> getListOfUsers() {
        ArrayList<Patron> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> user = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (!mCur.getString(mCur.getColumnIndex("status")).equals("2")) {
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
        String mQuery = "SELECT title, author, available_copies,  book_id, last_date_of_taking, type, price, edition, date, published_by, keywords, is_bestseller From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] book = new String[12];
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
            book[4] = mCur.getString(mCur.getColumnIndex("last_date_of_taking"));
            book[5] = mCur.getString(mCur.getColumnIndex("type"));
            book[6] = mCur.getString(mCur.getColumnIndex("price"));
            book[7] = mCur.getString(mCur.getColumnIndex("edition"));
            book[8] = mCur.getString(mCur.getColumnIndex("date"));
            book[9] = mCur.getString(mCur.getColumnIndex("published_by"));
            book[10] = mCur.getString(mCur.getColumnIndex("keywords"));
            book[11] = mCur.getString(mCur.getColumnIndex("is_bestseller"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return book;
    }

    public ArrayList<Books> getListOfBooks(int status) {
        ArrayList<Books> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, author, available_copies,  book_id, last_date_of_taking, type, price, edition, date, published_by, keywords, is_bestseller From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] book = new String[12];
        while (!mCur.isAfterLast()) {
            if (status >= mCur.getInt(mCur.getColumnIndex("type"))) {
                book[0] = mCur.getString(mCur.getColumnIndex("title"));
                book[1] = mCur.getString(mCur.getColumnIndex("author"));
                book[2] = mCur.getString(mCur.getColumnIndex("available_copies"));
                book[3] = mCur.getString(mCur.getColumnIndex("book_id"));
                book[4] = mCur.getString(mCur.getColumnIndex("last_date_of_taking"));
                book[5] = mCur.getString(mCur.getColumnIndex("type"));
                book[6] = mCur.getString(mCur.getColumnIndex("price"));
                book[7] = mCur.getString(mCur.getColumnIndex("edition"));
                book[8] = mCur.getString(mCur.getColumnIndex("date"));
                book[9] = mCur.getString(mCur.getColumnIndex("published_by"));
                book[10] = mCur.getString(mCur.getColumnIndex("keywords"));
                book[11] = mCur.getString(mCur.getColumnIndex("is_bestseller"));
                Books b = new Books(book);
                list.add(b);
            }
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
        cv.put("last_date_of_taking", book.getLastDate());
        cv.put("type", book.getIsForUser());
        cv.put("price", book.getPrice());
        cv.put("edition", book.getEdition());
        cv.put("date", book.getDateOfCreationOfBook());
        cv.put("published_by", book.getPublished_by());
        cv.put("keywords", book.getKeywords());
        cv.put("is_bestseller", book.getIsBestSeller());
        return db.update("Books", cv, "book_id = ?", new String[]{Integer.toString(book.getBookId())});
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

    public boolean hasBook(int user_id, int book_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (user_id == mCur.getInt(mCur.getColumnIndex("user_id")) && book_id == mCur.getInt(mCur.getColumnIndex("book_id"))){
                return true;
            }
        }
        return false;
    }

    public void updateTimeChecker(int user_id, int book_id, int time, int type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        cv.put("book_id", book_id);
        cv.put("time", time);
        cv.put("type", type);
        db.insert("time_checker", null, cv);
    }

    public ArrayList<Books> returnListOfUsersBook(int uId){
        ArrayList<Books> book = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id"))){
                Books b = new Books(this.getArrayBook(mCur.getInt(mCur.getColumnIndex("book_id"))));
                book.add(b);
            }
        }
        return book;
    }
}