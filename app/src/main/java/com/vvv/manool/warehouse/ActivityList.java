package com.vvv.manool.warehouse;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityList extends AppCompatActivity {

    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DataBaseHelper helper = new DataBaseHelper(this);
        db = helper.getWritableDatabase();



//
        ContentValues cv = new ContentValues();
//        //cv.put("_id", user.id);
        cv.put("art", 258);
        cv.put("name", "name1");
        cv.put("note", "note1");
//
//
//
        long rowId = db.insert("tbl_list", null, cv);
//




       // MyList myList = new MyList();
        // c отбором Cursor cursor = db.query("tbl_list", new String[]{"id","art","name","note"}, "_id=" + userId, null, null, null, null);
        Cursor cursor = db.query("tbl_list", new String[]{"id",
                "art",
                "name",
                "note"}, null, null, null, null, null);

        MyList myList=new MyList();
//
//        if (cursor.moveToFirst()) {
//            do {
                myList.id = cursor.getLong(cursor.getColumnIndex("id"));
                myList.art = cursor.getLong(cursor.getColumnIndex("art"));
                myList.name = cursor.getString(cursor.getColumnIndex("name"));
                myList.note = cursor.getString(cursor.getColumnIndex("note1"));
//            }while (cursor.moveToNext());
//        }
        cursor.close();


    }
}
