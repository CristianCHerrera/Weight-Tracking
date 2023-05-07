package com.example.project2final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class WeightDBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "weightdatabase";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "mydata";
    private static final String ID_COL = "id";
    private static final String DATE_COL = "date";
    private static final String WEIGHT_COL = "weight";

    // creating a constructor for our database handler.
    public WeightDBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase wDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DATE_COL + " TEXT,"
                + WEIGHT_COL + " TEXT)";
    }


    public void addNewData(String userDate, String userWeight) {


        SQLiteDatabase wDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(DATE_COL, userDate);
        values.put(WEIGHT_COL, userWeight);


        // pass values to table
        wDatabase.insert(TABLE_NAME, null, values);
        wDatabase.close();
    }

    public ArrayList<GetAndSet> readData() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorData = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<GetAndSet> dataArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorData.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                dataArrayList.add(new GetAndSet(cursorData.getString(1), cursorData.getString(2)));

            } while (cursorData.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorData.close();
        return dataArrayList;
    }


    public void updateData(String originalDate, String userDate, String userWeight) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(DATE_COL, userDate);
        values.put(WEIGHT_COL, userWeight);

        db.update(TABLE_NAME, values, "date=?", new String[]{originalDate});
        db.close();
    }

    public void deleteData(String userDate) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "date=?", new String[]{userDate});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase wDatabase, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        wDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(wDatabase);
    }
}
