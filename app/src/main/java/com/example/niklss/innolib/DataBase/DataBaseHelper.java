package com.example.niklss.innolib.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.niklss.innolib.Classes.Books;
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
        mCur.close();


        return user;
    }

    public ArrayList<UserCard> getListOfUsers() {
        ArrayList<UserCard> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT First_name,Last_name, address,  user_id, phone, status From Users";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String user = "";
        while (!mCur.isAfterLast()) {
            user += mCur.getString(mCur.getColumnIndex("First_name")) + " ";
            user += mCur.getString(mCur.getColumnIndex("Last_name")) + " ";
            user += mCur.getString(mCur.getColumnIndex("address")) + " ";
            user += mCur.getString(mCur.getColumnIndex("user_id")) + " ";
            user += mCur.getString(mCur.getColumnIndex("phone")) + " ";
            user += mCur.getString(mCur.getColumnIndex("status"));
            UserCard a = new UserCard(user);
            user = "";
            list.add(a);
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public String getStringBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, author, available_copies,  book_id, last_date_of_taking, type, price, edition, date, published_by, keywords From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String book = "";
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("book_id"))) {
                break;
            }
            mCur.moveToNext();
        }

        if (!mCur.isAfterLast() && id == mCur.getInt(mCur.getColumnIndex("book_id"))) {
            book += mCur.getString(mCur.getColumnIndex("title")) + " ";
            book += mCur.getString(mCur.getColumnIndex("author")) + " ";
            book += mCur.getString(mCur.getColumnIndex("available_copies")) + " ";
            book += mCur.getString(mCur.getColumnIndex("book_id")) + " ";
            book += mCur.getString(mCur.getColumnIndex("last_date_of_taking")) + " ";
            book += mCur.getString(mCur.getColumnIndex("type")) + " ";
            book += mCur.getString(mCur.getColumnIndex("price")) + " ";
            book += mCur.getString(mCur.getColumnIndex("edition")) + " ";
            book += mCur.getString(mCur.getColumnIndex("date")) + " ";
            book += mCur.getString(mCur.getColumnIndex("published_by")) + " ";
            book += mCur.getString(mCur.getColumnIndex("keywords"));
        } else {
            return null;
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return book;
    }

    public ArrayList<Books> getListOfBooks() {
        ArrayList<Books> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, author, available_copies,  book_id, last_date_of_taking, type, price, edition, date, published_by, keywords From Books";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String book = "";
        while (!mCur.isAfterLast()) {
            book += mCur.getString(mCur.getColumnIndex("title")) + " ";
            book += mCur.getString(mCur.getColumnIndex("author")) + " ";
            book += mCur.getString(mCur.getColumnIndex("available_copies")) + " ";
            book += mCur.getString(mCur.getColumnIndex("book_id")) + " ";
            book += mCur.getString(mCur.getColumnIndex("last_date_of_taking")) + " ";
            book += mCur.getString(mCur.getColumnIndex("type")) + " ";
            book += mCur.getString(mCur.getColumnIndex("price")) + " ";
            book += mCur.getString(mCur.getColumnIndex("edition")) + " ";
            book += mCur.getString(mCur.getColumnIndex("date")) + " ";
            book += mCur.getString(mCur.getColumnIndex("published_by")) + " ";
            book += mCur.getString(mCur.getColumnIndex("keywords"));
            Books b = new Books(book);
            book = "";
            list.add(b);
            mCur.moveToNext();
        }

        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }
}