package com.example.amiiboapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Amiibo.db";
    public static final String TABLE_NAME = "amiibo_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "IMAGE";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "SERIES";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();//video said this will be removed and changed
    }

    public void queryData(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void insertData(byte[] image, String name, String series){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES (NULL,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindBlob(1, image);
        statement.bindString(2, name);
        statement.bindString(3, series);
    }

    public boolean inserData1(byte[] image, String name, String series){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, image);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, series);
        long result = database.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean updateData(String id, byte[] image, String name, String series){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, image);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, series);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;

    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" BLOB, "+COL_3+" TEXT, "+COL_4+" TEXT)");//different from video
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
