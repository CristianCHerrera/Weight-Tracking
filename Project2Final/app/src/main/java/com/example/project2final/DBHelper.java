package com.example.project2final;

import  android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase myDatabase) { //Creation of database
        myDatabase.execSQL("create Table users(username Text primary key, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int oldVersion, int newVersion) { //Creates new database upon
        myDatabase.execSQL("drop Table if exists users");
    }

    public boolean insertUser(String username, String password) { //Inserts user data into database
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDatabase.insert("users", null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean verifyUser(String username){ //Checks if inputted username isn't already in database
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select * from users where username = ?", new String[] {username} );
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean verifyUserAndPassword(String username, String password){ //Checks if username and password are in database
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
