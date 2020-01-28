package com.yoekisoft.myapplication.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "notes_db";
    public static final String TABLE_NAME = "notes";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";
    public static final String ID = "id";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " VARCHAR,"
                    + COLUMN_EMAIL + " VARCHAR,"
                    + COLUMN_PHONE + " INT"
                    + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    void  addContact(ModelData modelDAta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, modelDAta.getName()); // Contact Name
        values.put(COLUMN_EMAIL, modelDAta.getEmail()); // Contact email
        values.put(COLUMN_PHONE, modelDAta.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection


    }

    ModelData getContact() {
        SQLiteDatabase db = this.getReadableDatabase();
        ModelData contact;
       // List<ModelData> itemIds = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT name,email FROM notes", new String[]{});
        if (cursor != null)
            cursor.moveToFirst();
        try{
        do {

            contact = new ModelData(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)), "");
            // return contact

        } while (cursor.moveToNext());}
        catch (CursorIndexOutOfBoundsException e){
            return new ModelData();
        }
        return contact;
    }

    void updateContact() {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "NAME");

        try {

            String[] args = new String[]{"sam"};
            db.update(TABLE_NAME, values, COLUMN_NAME + " = ?", args);

            db.close();



        } catch (SQLiteException e) {
            db.close();


        }
    }
    void deleteContact()
    {SQLiteDatabase db = this.getReadableDatabase();
        String[] args = new String[]{"sam"};
        db.delete(TABLE_NAME, COLUMN_NAME + " = ?", args);
    }
    public List<String> selectContact(){
        List<String> allStudents = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"name","email","phone"}, null, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                    allStudents.add(cursor.getString(cursor.getColumnIndex("name"))+"__"+   cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))+"__"+ cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));


//                allStudents.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        return allStudents;
    }
}
