package com.example.einore.minecollection.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Chemical_DAO   {


    public static final String name = "Mineral_Gestion_DB";
    public static final int version = 1;

    private SQLiteDatabase db;
    private MineralGestion_DB_SQLite mineral_gestion;

    public Chemical_DAO(){}

    public Chemical_DAO(Context context){
        mineral_gestion = new MineralGestion_DB_SQLite(context, name, null, version);
    }

    private static final String table_chemical = "table_chemical";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_FORMULA = "FORMULA";
    private static final int NUM_COL_FORMULA = 1;
    private static final String COL_CLASS = "CLASS";
    private static final int NUM_COL_CLASS = 2;

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

    // INSERT done with execSQL method in order to insertonly if the element does not exists;
    // if does not work, try with getAll method in the controller class
    public void insert(Chemical object) {
        /*
        ContentValues content = new ContentValues();
        content.put(COL_FORMULA, object.getChemical_formula());
        content.put(COL_CLASS, object.getChemical_class());
        */

        String insertChemichalQuery = "INSERT INTO " + table_chemical + "(" + COL_FORMULA
                + "," + COL_CLASS + ") SELECT \'" + object.getChemical_formula() + "\' , " +
                object.getChemical_class() + " WHERE NOT EXISTS (SELECT 1 FROM " + table_chemical +
                " WHERE " + COL_FORMULA + " = \'" + object.getChemical_formula() + "\' AND " +
                COL_CLASS + " = " + object.getChemical_class() + " );";

        db.execSQL(insertChemichalQuery);
    }

    public int update(int id, Chemical object) {
        ContentValues content = new ContentValues();
        content.put(COL_FORMULA, object.getChemical_formula());
        content.put(COL_CLASS, object.getChemical_class());

        return db.update(table_chemical, content, COL_ID + " = " + id, null );
    }

    public int remove(int id) {
        return db.delete(table_chemical, COL_ID + " = " + id, null);
    }

    public Chemical getObject(String formula) {
        //Cursor c = db.query(table_chemical, new String[] {COL_ID, COL_FORMULA, COL_CLASS}, COL_FORMULA + " LIKE \"" +
        //        formula + " \"", null, null, null, COL_FORMULA );

        Cursor c = db.rawQuery( "SELECT * FROM " + table_chemical + " WHERE " +
                COL_FORMULA + "=?", new String[] { formula } );

        return cursorToObject(c);
    }

    public Chemical cursorToObject(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Chemical chemical = new Chemical();
        chemical.setChemical_id(c.getInt(NUM_COL_ID));
        chemical.setChemical_formula(c.getString(NUM_COL_FORMULA));
        chemical.setChemical_class(c.getInt(NUM_COL_CLASS));
        c.close();

        return chemical;
    }


    public ArrayList<Chemical> getAllObject() {
        Cursor c = db.query(table_chemical, new String[] {COL_ID, COL_FORMULA, COL_CLASS},
                null, null, null, null, COL_ID);

        if (c.getCount() == 0){
            c.close();
            return null;
        }
        ArrayList<Chemical> listChemical = new ArrayList<>();
        do{
            Chemical chemical = new Chemical();
            chemical.setChemical_id(c.getInt(NUM_COL_ID));
            chemical.setChemical_formula(c.getString(NUM_COL_FORMULA));
            chemical.setChemical_class(c.getInt(NUM_COL_CLASS));
        }while(c.moveToNext());
        c.close();
        return listChemical;
    }
}

