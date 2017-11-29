package com.vvv.manool.warehouse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tb_dvl on 29.11.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        //Создание базы данных
        super(context, "MyDB.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Создание таблиц
        db.execSQL("CREATE TABLE tbl_list" +
                " ( `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `art` INTEGER," +
                " `name` TEXT," +
                " `note` TEXT );");

        db.execSQL("CREATE TABLE tbl_sku" +
                " ( `id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                " `art` INTEGER," +
                " `name` TEXT," +
                " `barcode` TEXT," +
                " `price` REAL," +
                " `pic_link` TEXT );");

        db.execSQL("CREATE TABLE tbl_sku_list" +
                " ( `art_sku` INTEGER," +
                " `art_list` INTEGER," +
                " `ordered` INTEGER," +
                " `collected` INTEGER )");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        //Обновление базы данных
    }


}
