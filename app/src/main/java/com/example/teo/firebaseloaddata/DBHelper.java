package com.example.teo.firebaseloaddata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.IntRange;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by teo on 09/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ID.db";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //Bảng lưu nội dung trang báo
        db.execSQL(
                "create table Id " +
                        "(id int primary key)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Id");
        onCreate(db);
    }

    //insertItemNews
    public boolean insertId(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        db.insert("Id", null, contentValues);
        return true;
    }


    //Delete row Item News
    public boolean deleteItemNews(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        db.delete("Dd", "id = ?", new String[] { Integer.toString(id) });
        return true;
    }

    //Check exists itemNews
    public boolean checkIdExistsRow(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Id where id=" +id+ "",null);
        if (res.getCount() == 0){
            return true;
        }
        return false;
    }

    /*public ArrayList<String> loadData(int start, int end){
        ArrayList<String> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Id where id>=" +start+" and id<=" +end+ "", null);
        while(res.isAfterLast() == false){
            data.add(res.getString(res.getColumnIndex("id")));
            res.moveToNext();
        }
        return null;
    };*/

    public ArrayList<String> getAllId(int start, int end) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM Id WHERE id >= " + start + " AND id <= "+ end + " ORDER BY id DESC", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("id")));
            res.moveToNext();
        }
        return array_list;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "Id");
        return numRows;
    }
}
