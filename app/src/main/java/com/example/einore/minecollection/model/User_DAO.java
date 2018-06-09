package com.example.einore.minecollection.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class User_DAO  {

    public static final String name = "Mineral.db";
    public static final int version = 1;

    private SQLiteDatabase db;
    private MineralGestion_DB_SQLite mineral_gestion;


    public User_DAO(Context context){
        mineral_gestion = new MineralGestion_DB_SQLite(context, name, null, version);
    }

    private static final String  table_user = "table_user";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_USERNAME = "USERNAME";
    private static final int NUM_COL_USERNAME = 1;
    private static final String COL_PINCODE = "PINCODE";
    private static final int NUM_COL_PINCODE = 2;
    private static final String COL_NAME= "NAME";
    private static final int NUM_COL_NAME = 3;
    private static final String COL_SURNAME = "SURNAME";
    private static final int NUM_COL_SURNAME = 4;
    private static final String COL_EMAIL = "EMAIL";
    private static final int NUM_COL_EMAIL = 5;
    private static final String COL_PHONE = "PHONE";
    private static final int NUM_COL_PHONE = 6;

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

    public long insertUser(User object) {
        ContentValues content = new ContentValues();
        content.put(COL_USERNAME, object.getUser_username());
        content.put(COL_PINCODE, object.getUser_pinCode());
        content.put(COL_NAME, object.getUser_name());
        content.put(COL_SURNAME, object.getUser_surname());
        content.put(COL_EMAIL, object.getUser_email());
        content.put(COL_PHONE, object.getUser_phone());

        return db.insert(table_user, null, content);
    }


    public int update(int id, User object) {
        ContentValues content = new ContentValues();
        content.put(COL_USERNAME, object.getUser_username());
        content.put(COL_PINCODE, object.getUser_pinCode());
        content.put(COL_NAME, object.getUser_name());
        content.put(COL_SURNAME, object.getUser_surname());
        content.put(COL_EMAIL, object.getUser_email());
        content.put(COL_PHONE, object.getUser_phone());

        return db.update(table_user, content, COL_ID + " = " + id, null );
    }


    public int remove(int id) {
        return db.delete(table_user, COL_ID + " = " + id, null);
    }


    public User getObject(String username) {
        //Cursor c = db.query(table_user, new String[] {COL_ID, COL_USERNAME, COL_PINCODE,  COL_NAME,
        //        COL_SURNAME, COL_EMAIL, COL_PHONE}, COL_USERNAME + " = ? ", new String[] {username}, null, null, COL_ID );

        //alternative trouvée sur stack overflow car fonctionnait pas

        Cursor c = db.rawQuery( "SELECT * FROM " + table_user + " WHERE " +
                COL_USERNAME + " = ? ", new String[] { username } );

        return cursorToObject(c);
    }


    public User cursorToObject(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        User user = new User();
        user.setUser_id(c.getInt(NUM_COL_ID));
        user.setUser_username(c.getString(NUM_COL_USERNAME));
        user.setUser_pinCode(c.getString(NUM_COL_PINCODE));
        user.setUser_name(c.getString(NUM_COL_NAME));
        user.setUser_surname(c.getString(NUM_COL_SURNAME));
        user.setUser_email(c.getString(NUM_COL_EMAIL));
        user.setUser_phone(c.getString(NUM_COL_PHONE));
        c.close();
        return user;
    }


    public ArrayList<User> getAllObject() {
        Cursor c = db.query(table_user, new String[] { COL_ID, COL_USERNAME, COL_PINCODE, COL_NAME,
                        COL_SURNAME, COL_EMAIL, COL_PHONE}, null, null, null,
                null, COL_ID );
        if (c.getCount() == 0){
            c.close();
            return null;
        }
        ArrayList<User> list_user = new ArrayList<>();
        c.moveToFirst();
        do{
            User user = new User();
            user.setUser_id(c.getInt(NUM_COL_ID));
            user.setUser_username(c.getString(NUM_COL_USERNAME));
            user.setUser_pinCode(c.getString(NUM_COL_PINCODE));
            user.setUser_name(c.getString(NUM_COL_NAME));
            user.setUser_surname(c.getString(NUM_COL_SURNAME));
            user.setUser_email(c.getString(NUM_COL_EMAIL));
            user.setUser_phone(c.getString(NUM_COL_PHONE));
            list_user.add(user);
        }while(c.moveToNext());
        c.close();

        return list_user;
    }


}

