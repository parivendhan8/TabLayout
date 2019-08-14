package com.example.cbe_teclwsp026.tab;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG_TABLENAME = "history";
    public static final String TAG_DATABASENAME = "history.db";
    public static final String TAG_LATITUDE = "latitude";
    public static final String TAG_LONGITUDE = "longitude";
    public static final String TAG_TIME = "time";
    public static final String TAG_ID = "id";
    public static final String TAG_SEARCH = "search";


    static final int DB_VERSION = 3;


    private static final String CREATE_TABLE = "create table " + TAG_TABLENAME +
            "(" + TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
              TAG_SEARCH + " TEXT, " + TAG_TIME + " TEXT " + " ); " ;

    public DatabaseHelper(Context context) {
        super(context, TAG_DATABASENAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TAG_TABLENAME);
        onCreate(db);

    }


    public boolean insert( String search)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
//        contentValue.put(TAG_LATITUDE, lat);
//        contentValue.put(TAG_LONGITUDE, lon);
//        contentValue.put(TAG_TIME, date);
//        String current= DateFormat.getDateTimeInstance().format(new Date());

        SimpleDateFormat dateFormat =  new SimpleDateFormat("yy-MM-dd HH:mm a");
        String currentDateTime = dateFormat.format(new Date());

        contentValue.put(TAG_SEARCH, search);
        contentValue.put(TAG_TIME, currentDateTime);
        long result = db.insert(TAG_TABLENAME, null, contentValue);

        if (result == -1){

            return false;
        }
        return true;
    }


//    public void delete(String id)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.delete("location", "id=?", new String[] {id} );
//
//    }

    public Cursor get_single_latlng()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor single_latlng= db.rawQuery("select * from "+TAG_TABLENAME, null);
        return single_latlng ;
    }


    public ArrayList<Modal_History> getAlllatlng() {
        ArrayList<Modal_History> array_list = new ArrayList<Modal_History>();


        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from "+ TAG_TABLENAME , null );
//        res.moveToFirst();

//        while(res.isAfterLast() ==  false)

        while (res.moveToNext())
        {
            Modal_History modalHistory= new Modal_History();

            SimpleDateFormat format= new SimpleDateFormat();

            modalHistory.setSearch(res.getString(res.getColumnIndex(TAG_SEARCH)));
            modalHistory.setTime(res.getString(res.getColumnIndex(TAG_TIME)));
            modalHistory.setId(res.getString(res.getColumnIndex(TAG_ID)));

            array_list.add(modalHistory);

//            res.moveToNext();
        }
        return array_list;
    }

    public Integer Delete_item(Integer id)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        return db.delete(TAG_TABLENAME, "id=?", new String[]{String.valueOf(id)});
    }

    public  void deleteAll()
    {
        SQLiteDatabase db=  this.getWritableDatabase();
//       db.execSQL();
        db.delete(TAG_TABLENAME, null, null);
    }





}
