package com.example.taza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "A2026XI.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "fullname TEXT, " +
                        "nisn TEXT UNIQUE NOT NULL, " +
                        "email TEXT UNIQUE NOT NULL, " +
                        "username TEXT UNIQUE NOT NULL, " +
                        "phone TEXT NOT NULL, " +
                        "password TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // ==============================
    // REGISTER
    // ==============================
    public boolean registerUser(
            String fullname,
            String nisn,
            String email,
            String username,
            String phone,
            String password
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("fullname", fullname);
        values.put("nisn", nisn);
        values.put("email", email);
        values.put("username", username);
        values.put("phone", phone);
        values.put("password", password);

        long result = db.insert("users", null, values);

        db.close();

        return result != -1;
    }

    // ==============================
    // LOGIN
    // ==============================
    public boolean checkUser(String nisn, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE nisn = ? AND password = ?",
                new String[]{nisn, password}
        );

        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return exists;
    }
}