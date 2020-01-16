package com.example.mindenamirecept;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Receptek.db";
    public static final String TABLE_NAME = "receptek";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "RECEPTNEV";
    public static final String COL_3 = "RECEPTKAT";
    public static final String COL_4 = "RECEPTHOZZ1";
    public static final String COL_5 = "RECEPTHOZZ2";
    public static final String COL_6 = "RECEPTHOZZ3";
    public static final String COL_7 = "RECEPTHOZZ4";
    public static final String COL_8 = "RECEPTHOZZ5";
    public static final String COL_9 = "RECEPTKESZITES";

    public dbhelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTables = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " VARCHAR(128) NOT NULL," +
                COL_3 + " VARCHAR(16) NOT NULL," +
                COL_4 + " VARCHAR(64) , " +
                COL_5 + " VARCHAR(64) , " +
                COL_6 + " VARCHAR(64) , " +
                COL_7 + " VARCHAR(64) , " +
                COL_8 + " VARCHAR(64) , " +
                COL_9 + " VARCHAR(1024) NOT NULL )";

        sqLiteDatabase.execSQL(createTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean adatRogzites(String receptNev,
                                String receptKat,
                                String receptHozz1,
                                String receptHozz2,
                                String receptHozz3,
                                String receptHozz4,
                                String receptHozz5,
                                String receptKeszites)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, receptNev);
        contentValues.put(COL_3, receptKat);
        contentValues.put(COL_4, receptHozz1);
        contentValues.put(COL_5, receptHozz2);
        contentValues.put(COL_6, receptHozz3);
        contentValues.put(COL_7, receptHozz4);
        contentValues.put(COL_8, receptHozz5);
        contentValues.put(COL_9, receptKeszites);

        long eredmeny = database.insert(TABLE_NAME, null, contentValues);

        if (eredmeny == -1)
            return false;       //sikertelen felvétel esetén false eredményt kapunk
        else
            return true;        //sikeres felvétel esetén true eredményt kapunk
    }

    public Cursor adatLekerdezesEloetelekdb()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor eredmeny = database.rawQuery("SELECT "+ COL_2+" FROM " + TABLE_NAME +" WHERE " +COL_3+ " LIKE 'Előételek' LIMIT 1", null);
        return eredmeny;
    }




}


//+" WHERE " + COL_3 + " LIKE Előételek LIMIT 1



