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

import com.example.niklss.innolib.Classes.Books;
import com.example.niklss.innolib.Classes.Patron;
import com.example.niklss.innolib.Classes.UserCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

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
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        createDataBase();
    }

    /**
     * Создает пустую базу данных и перезаписывает ее нашей собственной базой
     */
    public void createDataBase() throws IOException {
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist)
        {
            this.getReadableDatabase();
            this.close();
            try
            {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            }
            catch (IOException mIOException)
            {
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

        return book;
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

    public void addArt(String title, String author, String jtitle, String issue, String date, String editor, int numbers) {
        SQLiteDatabase db = this.getWritableDatabase();


        String s = "('" + title + "', '" + author + "', '" + jtitle + "', '" + issue + "', '" + date + "','" + editor + "', " + numbers + ")";


        String addArticle = "INSERT INTO Articles (title,authors,jtitle,issue,editor,numbers) Values " + s;
        db.beginTransaction();
        db.execSQL(addArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();

    }

    public void addAV(String title, String authors, int numbers) {
        SQLiteDatabase db = this.getWritableDatabase();


        String s = "('" + title + "', '" + authors + "', " + numbers + ")";


        String addAV = "INSERT INTO AV (title,authors,numbers) Values " + s;
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

    public void updateArticle(int id, String title, String author, String jtitle, String issue, String date, String editor, int numbers) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateArticle = "Update Articles Set title = '" + title + "', authors = '" + author + "', jtitle = '" + jtitle + "', issue = '" + issue + "', date = '" + date + "', editor = '" + editor + "', numbers = " + numbers + " where id=" + id;
        db.beginTransaction();
        db.execSQL(updateArticle);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateAV(int id, String title, String author, int numbers) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateAV = "Update AV Set title = '" + title + "', authors = '" + author + "', numbers = " + numbers + " where id=" + id;
        db.beginTransaction();
        db.execSQL(updateAV);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public void updateUser(int user_id, String First_name, String Last_name, String address, String phone, int status, int number_books) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateUser = "Update Users Set First_name = '" + First_name + "', Last_name = '" + Last_name + "', address = '" + address + "', phone = " + phone + ", status =" + status + ", number_books=" + number_books + " where user_id=" + user_id;
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
        String mQuery = "SELECT title, author, available_copies, type, price, edition, date, published_by, keywords, is_bestseller From Books";
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

    public void updateTimeChecker(int user_id, int book_id, int time, int type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        cv.put("book_id", book_id);
        cv.put("time", time);
        cv.put("type", type);
        db.insert("time_checker", null, cv);
    }

    public ArrayList<Books> returnListOfUsersBook(int uId) {
        ArrayList<Books> book = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            if (uId == mCur.getInt(mCur.getColumnIndex("user_id"))) {
                Books b = new Books(this.getArrayBook(mCur.getInt(mCur.getColumnIndex("book_id"))));
                book.add(b);
            }
            mCur.moveToNext();
        }
        mCur.close();
        return book;
    }

    public String getShortInformation(Books book) {
        String a = "";
        a += book.getTitleBook() + " ";
        a += book.getAuthorsOfBook() + " ";
        a += book.getEdition() + " ";
        return a;
    }

    public String getFullInformation(Books book) {
        String a = "";
        a += book.getTitleBook() + " ";
        a += book.getAuthorsOfBook() + " ";
        a += book.getEdition() + " ";
        a += book.getDateOfCreationOfBook() + " ";
        a += book.getPublished_by() + " ";
        a += book.getCountOfBooks() + " ";
        a += book.getIsBestSeller() + " ";
        a += book.getPrice() + " ";

        return a;
    }

    public ArrayList<Patron> debtorUsers() {
        ArrayList<Patron> patron = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id, book_id, time, type From time_checker";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        while (!mCur.isAfterLast()) {
            Patron a = new Patron(this.getArrayUser(mCur.getInt(mCur.getColumnIndex("user_id"))));
            patron.add(a);
            mCur.moveToNext();
        }
        mCur.close();
        return patron;
    }

    public ArrayList<String> getAVMaterial(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, id, numbers From AV";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("id"))) {
                list.add(mCur.getString(mCur.getColumnIndex("title")));
                list.add(mCur.getString(mCur.getColumnIndex("authors")));
                list.add(mCur.getString(mCur.getColumnIndex("id")));
                list.add(mCur.getString(mCur.getColumnIndex("numbers")));
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return list;
    }

    public ArrayList<String> getArticleMaterial(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, jtitle, issue, date, editor, numbers, id From Articles";
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
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();
        return list;
    }

    public void clearDataBase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deletebook = "DELETE FROM BOOKS;";
        String deleteuser = "DELETE FROM USERS;";
        String deleteAV = "DELETE FROM AV;";
        String deleteArticles = "DELETE FROM ARTICLES;";
        String deleteTimeChecker = "DELETE FROM time_checker;";


        db.beginTransaction();
        db.execSQL(deletebook);
        db.execSQL(deleteuser);
        db.execSQL(deleteArticles);
        db.execSQL(deleteAV);
        db.execSQL(deleteTimeChecker);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    public ArrayList<String[]> getListOfAV() {
        ArrayList<String[]> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, id, numbers From AV";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] av = new String[4];
        while (!mCur.isAfterLast()) {
            av[0] = (mCur.getString(mCur.getColumnIndex("title")));
            av[1] = (mCur.getString(mCur.getColumnIndex("authors")));
            av[2] = (mCur.getString(mCur.getColumnIndex("id")));
            av[3] = (mCur.getString(mCur.getColumnIndex("numbers")));
            list.add(av);
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public ArrayList<String[]> getListOfArticles() {
        ArrayList<String[]> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT title, authors, jtitle, issue, date, editor, numbers, id From Articles";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        String[] av = new String[4];
        while (!mCur.isAfterLast()) {
            av[0] = (mCur.getString(mCur.getColumnIndex("title")));
            av[1] = (mCur.getString(mCur.getColumnIndex("authors")));
            av[2] = (mCur.getString(mCur.getColumnIndex("jtitle")));
            av[3] = (mCur.getString(mCur.getColumnIndex("issue")));
            av[4] = (mCur.getString(mCur.getColumnIndex("date")));
            av[5] = (mCur.getString(mCur.getColumnIndex("editor")));
            av[6] = (mCur.getString(mCur.getColumnIndex("numbers")));
            av[7] = (mCur.getString(mCur.getColumnIndex("id")));
            list.add(av);
            mCur.moveToNext();
        }
        mCur.moveToFirst();
        db.close();
        mCur.close();

        return list;
    }

    public boolean noOneInQueue(int id, int type){
        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery;
        mQuery = "SELECT document_id, document_type From Queue ";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<String> list = new ArrayList<> ();
        while (!mCur.isAfterLast()) {
            if (id == mCur.getInt(mCur.getColumnIndex("document_id")) && type == mCur.getInt(mCur.getColumnIndex("document_type"))) {
                list.add(mCur.getString(mCur.getColumnIndex("document_id")));
                break;
            }
            mCur.moveToNext();
        }

        mCur.close();

        return list.size()==0;

    }


    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void standInQueue(Patron patron, Books book){

        SQLiteDatabase db = this.getWritableDatabase();
        String mQuery = "SELECT user_id,user_type, document_id,document_type From Queue";
        Cursor mCur = db.rawQuery(mQuery, new String[]{});
        mCur.moveToFirst();
        ArrayList<int []> list = new ArrayList<>();
        while (!mCur.isAfterLast()) {
            int [] arr = new int [4];
            arr[0]=(mCur.getInt(mCur.getColumnIndex("user_id")));
            arr[1]=(mCur.getInt(mCur.getColumnIndex("user_type")));
            arr[2]=(mCur.getInt(mCur.getColumnIndex("document_id")));
            arr[3]=(mCur.getInt(mCur.getColumnIndex("document_type")));



            list.add(arr);

            mCur.moveToNext();
        }
        mCur.moveToFirst();

        mCur.close();
        int [] e = new int [4];
        e[0]=patron.getuId();
        e[1]=patron.getUsersType();
        e[2]=book.getBookId();
        e[3]=0;
        list.add(e);
        list.sort((a, b) -> Integer.compare(a[1], b[1]));

        db.execSQL("DELETE FROM Queue");
        for (int i = 0; i <list.size(); i++) {
            String cc = "INSERT INTO Queue (user_id,user_type,document_id,document_type) VALUES ("+list.get(i)[0]+", "+list.get(i)[1]+", "+list.get(i)[2]+", "+list.get(i)[3]+");";
            db.execSQL(cc);
        }

        db.close();
    }
}