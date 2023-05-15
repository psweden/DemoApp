package com.demo.demoapp.model.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private static final String DB_NAME = "userdb";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "myusers";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "nameofattribute";
    private static final String PHONE_NUMBER_COL = "phonenumberplusdtmf";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PHONE_NUMBER_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new user to our sqlite database.
    public void addNewUser(String nameofattribute, String phonenumberplusdtmf) {
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, nameofattribute);
        values.put(PHONE_NUMBER_COL, phonenumberplusdtmf);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String getRecordPost(int id){
        String row_values = "";
        SQLiteDatabase db = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        String size = String.valueOf(count);

        Cursor cur = db.rawQuery("SELECT nameofattribute, phonenumberplusdtmf  FROM " + TABLE_NAME + " WHERE id = " + id, null);
        if(cur.getCount() != 0){
            cur.moveToFirst();
            do{
                row_values = "";

                for(int i = 0 ; i < cur.getColumnCount(); i++){
                    row_values = row_values + " || " + cur.getString(i);
                }
            }while (cur.moveToNext());
        }
        return row_values;
    }

    public String getDBSize()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        String size = String.valueOf(count);
        return size;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
