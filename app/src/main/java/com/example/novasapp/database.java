package com.example.novasapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {

    public static final String PUPPY_TABLE = "puppy_table";
    public static final String ID = "ID";
    public static final String ACTIVITY_TYPE = "ACTIVITY_TYPE";
    public static final String NOTES = "NOTES";
    public static final String YEAR = "YEAR";
    public static final String MONTH = "MONTH";
    public static final String DATE = "DATE";
    public static final String HOUR = "HOUR";
    public static final String MINUTE = "MINUTE";
    public static final String WEIGHT = "WEIGHT";


    public database(@Nullable Context context) {
        super(context, "puppy.db", null, 1);
    }

    // This is called the first time a database is accessed. There should be code in here to create a new database.
    @Override
    public void onCreate(SQLiteDatabase db) {

        //String createTableStatement = "CREATE TABLE " + PUPPY_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACTIVITY_TYPE + " TEXT, " + NOTES + " TEXT, " + YEAR + " INT, " + MONTH + " INT, " + DATE + " INT, " + HOUR + " INT, " + MINUTE + " INT, " + WEIGHT + " INT)";

        String createTableStatement = "CREATE TABLE " + PUPPY_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACTIVITY_TYPE + " TEXT, " + YEAR + " INT, " + MONTH + " INT, " + DATE + " INT, " + HOUR + " INT, " + MINUTE + " INT, " + WEIGHT + " INT)";

        db.execSQL(createTableStatement);
    }

    // This is called if the database version number changes. It prevents previous users apps from crashing when you develop a new database version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*
        if (i > i1) {
            db.execSQL("ALTER TABLE " + PUPPY_TABLE + " ADD COLUMN new_column INTEGER DEFAULT 0");
        }

         */
    }

    public boolean addOne(eventObject event) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ACTIVITY_TYPE,event.getActivity_type());
        //cv.put(NOTES,event.getNotes());
        cv.put(YEAR,event.getYear());
        cv.put(MONTH,event.getMonth());
        cv.put(DATE,event.getDate());
        cv.put(HOUR,event.getHour());
        cv.put(MINUTE,event.getMinute());
        //cv.put(WEIGHT,event.getWeight());

        long insert = db.insert(PUPPY_TABLE,null,cv);
        db.close();

        return insert != -1;

    }

    public void editOne(eventObject eventOrig, eventObject eventNew, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if(eventNew.getActivity_type().equals("none")){
            cv.put(ACTIVITY_TYPE,eventOrig.getActivity_type());
        } else {
            cv.put(ACTIVITY_TYPE,eventNew.getActivity_type());
        }

        if(eventNew.getYear() == -1){
            cv.put(YEAR,eventOrig.getYear());
        } else {
            cv.put(YEAR,eventNew.getYear());
        }

        if(eventNew.getMonth() == -1){
            cv.put(MONTH,eventOrig.getMonth());
        } else {
            cv.put(MONTH,eventNew.getMonth());
        }

        if(eventNew.getDate() == -1){
            cv.put(DATE,eventOrig.getDate());
        } else {
            cv.put(DATE,eventNew.getDate());
        }

        if(eventNew.getHour() == -1){
            cv.put(HOUR,eventOrig.getHour());
        } else {
            cv.put(HOUR,eventNew.getHour());
        }

        if(eventNew.getHour() == -1){
            cv.put(MINUTE,eventOrig.getMinute());
        } else {
            cv.put(MINUTE,eventNew.getMinute());
        }

        /*
        if(eventNew.getNotes().equals("none")){
            cv.put(NOTES,eventOrig.getNotes());
        } else {
            cv.put(NOTES,eventNew.getNotes());
        }
        */

        /*
        if(eventNew.getWeight() == -1){
            cv.put(WEIGHT,eventOrig.getWeight());
        } else {
            cv.put(WEIGHT,eventNew.getWeight());
        }
        */

        db.update(PUPPY_TABLE, cv, ID+" = ?", new String[]{Integer.toString(id)});
        db.close();

    }

    public Boolean deleteOne(int event_id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(PUPPY_TABLE,ID+" = ?",new String[]{Integer.toString(event_id)});
        return true;
    }

    public ArrayList<eventObject> getAllEvents() {

        ArrayList<eventObject> lst = new ArrayList<>();

        String qString = "SELECT * FROM " + PUPPY_TABLE;
        SQLiteDatabase db = database.this.getReadableDatabase();
        Cursor c = db.rawQuery(qString, null);

        if(c.moveToFirst()){

            do {


                int id = c.getInt(0);
                String activity_id = c.getString(1);
                //String notes = c.getString(1);
                int yr = c.getInt(2);
                int mnth = c.getInt(3);
                int dt = c.getInt(4);
                int hr = c.getInt(5);
                int min = c.getInt(6);
                int wt = c.getInt(7);

                //puppyModel pupSub = new puppyModel(id,activity_id,notes,yr,mnth,dt,hr,min,wt);
                eventObject pupSub = new eventObject(id,activity_id,yr,mnth,dt,hr,min,wt);
                lst.add(pupSub);

            } while ( c.moveToNext() );

        } else {
            // The list is empty so no items are added to the table.
        }

        c.close();
        db.close();

        return lst;

    }


}
