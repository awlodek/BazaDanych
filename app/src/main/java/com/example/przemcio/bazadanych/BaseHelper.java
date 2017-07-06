package com.example.przemcio.bazadanych;

/**
 * Created by Przemcio on 2017-07-06.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luke on 01.06.16.
 */
public class BaseHelper extends SQLiteOpenHelper{
    public static final String database_name = "BazaTelefonów";
    public static final String database_table = "Telefony";
    public BaseHelper(Context context) {
        super(context, database_name, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ database_table + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, BRAND TEXT, YEAR INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+database_table);
        onCreate(db);
    }
    public boolean addData(String name, String brand, String year, String cb){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("brand",brand);
        cv.put("year",year);
        cv.put("id", cb);
        long b = db.insert(database_table,null,cv);
        if ( b == -1 ) return false;
        else return true;

    }
    public SQLiteCursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteCursor kursor = (SQLiteCursor) db.rawQuery("SELECT * FROM "+database_table,null);
        return kursor;
    }
    public boolean delateData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (db.delete(database_table,"ID = ?", new String[]{ id }) >0 )
            return true;
        else
            return false;

    }
}
