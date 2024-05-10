package com.example.grantha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseLibrary extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 4;

    //books
    private static final String TABLE_NAME = "MyLibrary";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_page";

    //members
    private static final String TABLE_MEMBERS = "Members";
    private static final String COLUMN_CARD_NO = "CARD_NO";
    private static final String COLUMN_FIRST_NAME = "First_Name";
    private static final String COLUMN_LAST_NAME = "Last_Name";
    private static final String COLUMN_MOBILE_NUMBER = "Mobile_Number";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_UNPAID_DUES = "Unpaid_Dues";


    //publishers
    private static final String TABLE_PUBLISHERS = "publishers";
    private static final String COLUMN_PUBLISHER_NAME = "Publisher_Name";
    private static final String COLUMN_PUBLISHER_ADDRESS = "Publisher_Address";
    private static final String COLUMN_PUBLISHER_NUMBER = "Publisher_Number";


    // Branches
    private static final String TABLE_BRANCHES = "Branches";
    private static final String COLUMN_BRANCH_ID = "Branch_ID";
    private static final String COLUMN_BRANCH_NAME = "Branch_Name";
    private static final String COLUMN_BRANCH_ADDRESS = "Address";


    // Book_Author table
    private static final String TABLE_BOOK_AUTHOR = "Book_Author";
    private static final String COLUMN_BOOK_ID = "BOOK_ID";
    private static final String COLUMN_AUTHOR_NAME = "AUTHOR_NAME";


    public DatabaseLibrary(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //table for books
        String queryBooks = "CREATE TABLE " + TABLE_NAME +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_PAGES + " TEXT)";
        db.execSQL(queryBooks);

        //members
        String queryMembers = "CREATE TABLE " + TABLE_MEMBERS +
                "(" + COLUMN_CARD_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FIRST_NAME + " TEXT," +
                COLUMN_LAST_NAME + " TEXT," +
                COLUMN_MOBILE_NUMBER + " TEXT," +
                COLUMN_ADDRESS + " TEXT," +
                COLUMN_UNPAID_DUES + " TEXT)";
        db.execSQL(queryMembers);

        //publishers

        String queryPublishers = "CREATE TABLE " + TABLE_PUBLISHERS +
                "(" + COLUMN_PUBLISHER_NAME + " TEXT PRIMARY KEY," +
                COLUMN_PUBLISHER_ADDRESS + " TEXT," +
                COLUMN_PUBLISHER_NUMBER + " TEXT)";
        db.execSQL(queryPublishers);

        // Branches
        String queryBranches = "CREATE TABLE " + TABLE_BRANCHES +
                "(" + COLUMN_BRANCH_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_BRANCH_NAME + " TEXT," +
                COLUMN_BRANCH_ADDRESS + " TEXT)";
        db.execSQL(queryBranches);

        // Book_Author table creation
        String queryBookAuthor = "CREATE TABLE " + TABLE_BOOK_AUTHOR +
                "(" + COLUMN_BOOK_ID + " TEXT," +
                COLUMN_AUTHOR_NAME + " TEXT," +
                "PRIMARY KEY(" + COLUMN_BOOK_ID + ", " + COLUMN_AUTHOR_NAME + ")," +
                "FOREIGN KEY(" + COLUMN_BOOK_ID + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_ID + "))";
        db.execSQL(queryBookAuthor);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUBLISHERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK_AUTHOR);
        onCreate(db);
    }

    // Add a book to the library
    void addBook(String title, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all books from the library
    Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    // Update a book in the library
    void updateData(String row_id, String title, String author, String pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long result = db.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to update book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a book from the library
    void deleteData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Add a member to the library
    void addMember(String firstName, String lastName, String mobileNumber, String address, String unpaidDues) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME, firstName);
        cv.put(COLUMN_LAST_NAME, lastName);
        cv.put(COLUMN_MOBILE_NUMBER, mobileNumber);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_UNPAID_DUES, unpaidDues);

        long result = db.insert(TABLE_MEMBERS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Member added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all members from the library
    Cursor readAllMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_MEMBERS, null, null, null, null, null, null);
    }

    // Update a member in the library
    void updateMember(String cardNo, String firstName, String lastName, String mobileNumber, String address, String unpaidDues) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME, firstName);
        cv.put(COLUMN_LAST_NAME, lastName);
        cv.put(COLUMN_MOBILE_NUMBER, mobileNumber);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_UNPAID_DUES, unpaidDues);

        long result = db.update(TABLE_MEMBERS, cv, COLUMN_CARD_NO + "=?", new String[]{cardNo});
        if (result == -1) {
            Toast.makeText(context, "Failed to update member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Member updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a member from the library
    void deleteMember(String cardNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_MEMBERS, COLUMN_CARD_NO + "=?", new String[]{cardNo});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Member deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Add publisher
    // Add a publisher
    void addPublisher(String Name, String Address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PUBLISHER_NAME, Name);
        cv.put(COLUMN_PUBLISHER_ADDRESS, Address);

        long result = db.insert(TABLE_PUBLISHERS, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add publisher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Publisher added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all publishers from the library
    Cursor readAllPublishers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PUBLISHERS, null, null, null, null, null, null);
    }

    // Update a publisher in the library
    void updatePublisher(String Name, String Address, String Number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PUBLISHER_NAME, Name);
        cv.put(COLUMN_PUBLISHER_ADDRESS, Address);
        cv.put(COLUMN_PUBLISHER_NUMBER, Number);

        long result = db.update(TABLE_PUBLISHERS, cv, COLUMN_PUBLISHER_NAME + "=?", new String[]{Name});
        if (result == -1) {
            Toast.makeText(context, "Failed to update publisher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Publisher updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a publisher from the library
    void deletePublisher(String Name) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_PUBLISHERS, COLUMN_PUBLISHER_NAME + "=?", new String[]{Name});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete publisher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Publisher deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    void addBranch(String branchName , String address ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANCH_NAME, branchName);
        cv.put(COLUMN_BRANCH_ADDRESS, address);

        long result = db.insert(TABLE_BRANCHES, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add branch", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Branch added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all branches from the library
    Cursor readAllBranches() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_BRANCHES, null, null, null, null, null, null);
    }

    // Update a branch in the library
    void updateBranch(String branchId, String branchName, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRANCH_NAME, branchName);
        cv.put(COLUMN_BRANCH_ADDRESS, address);

        long result = db.update(TABLE_BRANCHES, cv,COLUMN_BRANCH_ID +  "=?", new String[]{branchId});
        if (result == -1) {
            Toast.makeText(context, "Failed to update branch", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Branch updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a branch from the library
    void deleteBranch(String branchId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_BRANCHES, COLUMN_BRANCH_ID + "=?", new String[]{branchId});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete branch", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Branch deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Add book-author relationship
    void addAuthor(String bookId, String authorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_ID, bookId);
        cv.put(COLUMN_AUTHOR_NAME, authorName);

        long result = db.insert(TABLE_BOOK_AUTHOR, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add book-author relationship", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book-author relationship added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllAuthors() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_BOOK_AUTHOR, null, null, null, null, null, null);
    }

    // Update book-author relationship
    void updateBookAuthor(String bookId, String authorName, String newAuthorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_AUTHOR_NAME, newAuthorName);

        long result = db.update(TABLE_BOOK_AUTHOR, cv, COLUMN_BOOK_ID + "=? AND " + COLUMN_AUTHOR_NAME + "=?",
                new String[]{bookId, authorName});
        if (result == -1) {
            Toast.makeText(context, "Failed to update book-author relationship", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book-author relationship updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

  /*  // Delete book-author relationship
    void deleteAuthor(String bookId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_BOOK_AUTHOR, COLUMN_BOOK_ID + "=? AND " + COLUMN_AUTHOR_NAME + "=?",
                new String[]{bookId, authorName});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete book-author relationship", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Book-author relationship deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }*/
}







