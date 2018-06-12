package com.example.einore.minecollection.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Mineral_DAO  {

    public static final String name = "Mineral_Gestion_DB";
    public static final int version = 1;

    private SQLiteDatabase db;
    private MineralGestion_DB_SQLite mineral_gestion;

    public Mineral_DAO(){}

    public Mineral_DAO(Context context){
        mineral_gestion = new MineralGestion_DB_SQLite(context, name, null, version);
    }

    private static final String table_mineral = "table_mineral";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "NAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_SYNONYM = "SYNONYM";
    private static final int NUM_COL_SYNPNYM = 2;
    private static final String COL_MINASS = "MINASS";
    private static final int NUM_COL_MINASS = 3;
    private static final String COL_SYSTCRIST = "SYSTCRIST";
    private static final int NUM_COL_SYSTCRIST = 4;
    private static final String COL_COLOR = "COLOR";
    private static final int NUM_COL_COLOR = 5;
    private static final String COL_GLOW = "GLOW";
    private static final int NUM_COL_GLOW = 6;
    private static final String COL_ASPECT = "ASPECT";
    private static final int NUM_COL_ASPECT = 7;
    private static final String COL_CLIVAGE = "CLIVAGE";
    private static final int NUM_COL_CLIVAGE = 8;
    private static final String COL_HARDNESS = "HARDNESS";
    private static final int NUM_COL_HARDNESS = 9;
    private static final String COL_DENSITY = "DENSITY";
    private static final int NUM_COL_DENSITY = 10;
    private static final String COL_PRICE = "PRICE";
    private static final int NUM_COL_PRICE = 11;
    private static final String COL_LOCATION = "LOCATION";
    private static final int NUM_COL_LOCATION = 12;
    private static final String COL_FK_user = "FK_USER";
    private static final int NUM_COL_FK_USER = 13;
    private static final String COL_FK_location = "FK_LOCATION";
    private static final int NUM_COL_FK_LOCATION = 14;
    private static final String COL_FK_chemical = "FK_CHEMICAL";
    private static final int NUM_COL_FK_CHEMICAL = 15;

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


    //Override methods that come from class DAO

    public long insert(Mineral object) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, object.getMineral_name());
        content.put(COL_SYNONYM, object.getMineral_synonyme());
        content.put(COL_MINASS, object.getMineral_minAss());
        content.put(COL_SYSTCRIST, object.getMineral_systCrist());
        content.put(COL_COLOR, object.getMineral_color());
        content.put(COL_GLOW, object.getMineral_glow());
        content.put(COL_ASPECT, object.getMineral_aspect());
        content.put(COL_CLIVAGE, object.getMineral_clivage());
        content.put(COL_HARDNESS, object.getMineral_hardness());
        content.put(COL_DENSITY, object.getMineral_density());
        content.put(COL_PRICE, object.getMineral_price());
        content.put(COL_LOCATION, object.getMineral_location());
        content.put(COL_FK_user, object.getForeignKey_user());
        content.put(COL_FK_location, object.getForeignKey_location());
        content.put(COL_FK_chemical, object.getForeignKey_chemical());

        return db.insert(table_mineral, null, content);
    }


    public int update(String id, Mineral object) {
        ContentValues content = new ContentValues();
        content.put(COL_NAME, object.getMineral_name());
        content.put(COL_SYNONYM, object.getMineral_synonyme());
        content.put(COL_MINASS, object.getMineral_minAss());
        content.put(COL_SYSTCRIST, object.getMineral_systCrist());
        content.put(COL_COLOR, object.getMineral_color());
        content.put(COL_GLOW, object.getMineral_glow());
        content.put(COL_ASPECT, object.getMineral_aspect());
        content.put(COL_CLIVAGE, object.getMineral_clivage());
        content.put(COL_HARDNESS, object.getMineral_hardness());
        content.put(COL_DENSITY, object.getMineral_density());
        content.put(COL_PRICE, object.getMineral_price());
        content.put(COL_LOCATION, object.getMineral_location());
        content.put(COL_FK_user, object.getForeignKey_user());
        content.put(COL_FK_location, object.getForeignKey_location());
        content.put(COL_FK_chemical, object.getForeignKey_chemical());

        return db.update(table_mineral, content, COL_ID + " = " + id, null );
    }


    public int remove(int id) {
        return db.delete(table_mineral, COL_ID + " = " + id, null);
    }


    public Mineral getObject(String name) {
        //Cursor c = db.query(table_mineral, new String[] {COL_ID, COL_NAME, COL_SYNONYM,  COL_MINASS,
        //        COL_SYSTCRIST, COL_COLOR, COL_GLOW, COL_ASPECT, COL_CLIVAGE, COL_HARDNESS, COL_DENSITY,
        //        COL_DATE, COL_PRICE, COL_LOCATION, COL_FK_user, COL_FK_location, COL_FK_chemical},
        //        COL_NAME + " LIKE \"" + name + " \"", null, null, null, COL_NAME );

        Cursor c = db.rawQuery( "SELECT * FROM " + table_mineral + " WHERE " +
                COL_NAME + " = ? ", new String[] { name } );

        return cursorToObject(c);
    }

    public Mineral getObjectById(String id) {

        Cursor c = db.rawQuery( "SELECT * FROM " + table_mineral + " WHERE " +
                COL_ID + " = ? ", new String[] {String.valueOf(id)} );

        return cursorToObject(c);
    }


    public Mineral cursorToObject(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Mineral mineral = new Mineral();
        mineral.setMineral_id(c.getInt(NUM_COL_ID));
        mineral.setMineral_name(c.getString(NUM_COL_NAME));
        mineral.setMineral_synonyme(c.getString(NUM_COL_SYNPNYM));
        mineral.setMineral_minAss(c.getString(NUM_COL_MINASS));
        mineral.setMineral_systCrist(c.getString(NUM_COL_SYSTCRIST));
        mineral.setMineral_color(c.getString(NUM_COL_COLOR));
        mineral.setMineral_glow(c.getString(NUM_COL_GLOW));
        mineral.setMineral_aspect(c.getString(NUM_COL_ASPECT));
        mineral.setMineral_clivage(c.getString(NUM_COL_CLIVAGE));
        mineral.setMineral_hardness(c.getFloat(NUM_COL_HARDNESS));
        mineral.setMineral_density(c.getFloat(NUM_COL_DENSITY));
        mineral.setMineral_price(c.getFloat(NUM_COL_PRICE));
        mineral.setMineral_location(c.getString(NUM_COL_LOCATION));
        mineral.setForeignKey_user(c.getInt(NUM_COL_FK_USER));
        mineral.setForeignKey_location(c.getInt(NUM_COL_FK_LOCATION));
        mineral.setForeignKey_chemical(c.getInt(NUM_COL_FK_CHEMICAL));
        c.close();

        return mineral;
    }


    public ArrayList<Mineral> getAllObject(int  idFKUser) {
        Cursor c = db.query(table_mineral, new String[] {COL_ID, COL_NAME, COL_SYNONYM,  COL_MINASS,
                        COL_SYSTCRIST, COL_COLOR, COL_GLOW, COL_ASPECT, COL_CLIVAGE, COL_HARDNESS, COL_DENSITY,
                        COL_PRICE, COL_LOCATION, COL_FK_user, COL_FK_location, COL_FK_chemical},
                null, null, null, null, COL_ID);

        if (c.getCount() == 0){
            c.close();
            return null;
        }
        ArrayList<Mineral> listMineral = new ArrayList<>();
        c.moveToFirst();

        do{
            if(idFKUser == c.getInt(NUM_COL_FK_USER)) {
                Mineral mineral = new Mineral();
                mineral.setMineral_id(c.getInt(NUM_COL_ID));
                mineral.setMineral_name(c.getString(NUM_COL_NAME));
                mineral.setMineral_synonyme(c.getString(NUM_COL_SYNPNYM));
                mineral.setMineral_minAss(c.getString(NUM_COL_MINASS));
                mineral.setMineral_systCrist(c.getString(NUM_COL_SYSTCRIST));
                mineral.setMineral_color(c.getString(NUM_COL_COLOR));
                mineral.setMineral_glow(c.getString(NUM_COL_GLOW));
                mineral.setMineral_aspect(c.getString(NUM_COL_ASPECT));
                mineral.setMineral_clivage(c.getString(NUM_COL_CLIVAGE));
                mineral.setMineral_hardness(c.getFloat(NUM_COL_HARDNESS));
                mineral.setMineral_density(c.getFloat(NUM_COL_DENSITY));
                mineral.setMineral_price(c.getFloat(NUM_COL_PRICE));
                mineral.setMineral_location(c.getString(NUM_COL_LOCATION));
                mineral.setForeignKey_user(c.getInt(NUM_COL_FK_USER));
                mineral.setForeignKey_location(c.getInt(NUM_COL_FK_LOCATION));
                mineral.setForeignKey_chemical(c.getInt(NUM_COL_FK_CHEMICAL));

                listMineral.add(mineral);
            }
        }while(c.moveToNext());
        c.close();

        return listMineral;
    }
}

