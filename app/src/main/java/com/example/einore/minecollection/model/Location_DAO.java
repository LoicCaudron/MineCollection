package com.example.einore.minecollection.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Location_DAO  {

    public static final String name = "Mineral_Gestion_DB";
    public static final int version = 1;

    private SQLiteDatabase db;
    private MineralGestion_DB_SQLite mineral_gestion;

    public Location_DAO(){}

    public Location_DAO(Context context){
        mineral_gestion = new MineralGestion_DB_SQLite(context, name, null, version);
    }

    private static final String table_location = "table_location";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_CITY = "CITY";
    private static final int NUM_COL_CITY  = 1;
    private static final String COL_AREA = "AREA";
    private static final int NUM_COL_AREA = 2;
    private static final String COL_COUNTRY = "COUNTRY";
    private static final int NUM_COL_COUNTRTY = 3;

    public void openForWrite() {
        db = mineral_gestion.getWritableDatabase();
    }

    public void openForRead() {
        db = mineral_gestion.getReadableDatabase();
    }

    public void close(){ db.close(); }

    public SQLiteDatabase getBdd() {
        return db;
    }


    // Override methods that come from class DAO

    public long insert(Location object) {
        ContentValues content = new ContentValues();
        content.put(COL_CITY, object.getLocation_city());
        content.put(COL_AREA, object.getLocation_area());
        content.put(COL_COUNTRY, object.getLocation_Country());

        return db.insert(table_location, null, content);
    }


    public int update(int id, Location object) {
        ContentValues content = new ContentValues();
        content.put(COL_CITY, object.getLocation_city());
        content.put(COL_AREA, object.getLocation_area());
        content.put(COL_COUNTRY, object.getLocation_Country());

        return db.update(table_location, content, COL_ID + " = " + id, null);
    }

    public int remove(int id) {
        return db.delete(table_location, COL_ID + " = "  + id, null);
    }


    public Location getObject(String city) {
        //Cursor c = db.query(table_location, new String[] {COL_ID, COL_CITY, COL_AREA,COL_COUNTRY},
        //        COL_CITY + " LIKE \"" + city + " \"", null, null, null, COL_CITY);

        Cursor c = db.rawQuery( "SELECT * FROM " + table_location + " WHERE " +
                COL_CITY + "=?", new String[] { city } );

        return cursorToObject(c);
    }


    public Location cursorToObject(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Location location = new Location();
        location.setLocation_id(c.getInt(NUM_COL_ID));
        location.setLocation_city(c.getString(NUM_COL_CITY));
        location.setLocation_area(c.getString(NUM_COL_AREA));
        location.setLocation_Country(c.getString(NUM_COL_COUNTRTY));
        c.close();

        return location;
    }


    public ArrayList<Location> getAllObject() {
        Cursor c = db.query(table_location, new String[] {COL_ID, COL_CITY, COL_AREA, COL_COUNTRY},
                null, null, null, null, COL_ID);
        if( c.getCount() ==0 ){
            c.close();
            return null;
        }
        ArrayList<Location> listLocation = new ArrayList<>();
        do{
            Location location = new Location();
            location.setLocation_id(c.getInt(NUM_COL_ID));
            location.setLocation_city(c.getString(NUM_COL_CITY));
            location.setLocation_area(c.getString(NUM_COL_AREA));
            location.setLocation_Country(c.getString(NUM_COL_COUNTRTY));
            listLocation.add(location);
        }while(c.moveToNext());
        c.close();

        return listLocation;
    }
}
