package com.example.einore.minecollection.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MineralGestion_DB_SQLite extends SQLiteOpenHelper {

    //Creation of the USER table
    private static final String table_user = "table_user";
    private static final String COL_ID_user = "ID";
    private static final String COL_USERNAME_user = "USERNAME";
    private static final String COL_PINCODE_user = "PINCODE";
    private static final String COL_NAME_user = "NAME";
    private static final String COL_SURNAME_user = "SURNAME";
    private static final String COL_EMAIL_user = "EMAIL";
    private static final String COL_PHONE_user = "PHONE";

    private static final String CREATE_TABLE_user = "CREATE TABLE " + table_user + " ("
            + COL_ID_user + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_USERNAME_user + " TEXT NOT NULL,"
            + COL_PINCODE_user + " TEXT NOT NULL,"
            + COL_NAME_user + " TEXT NOT NULL,"
            + COL_SURNAME_user + " TEXT NOT NULL, "
            + COL_EMAIL_user + " TEXT NOT NULL ,"
            + COL_PHONE_user + " TEXT NOT NULL );";

    //Creation of the LOCATION table
    private static final String table_location = "table_location";
    private static final String COL_ID_location = "ID";
    private static final String COL_CITY_location = "CITY";
    private static final String COL_AREA_location = "AREA";
    private static final String COL_COUNTRY_location = "COUNTRY";

    private static final String CREATE_TABLE_location = " CREATE TABLE " + table_location + " ("
            + COL_ID_location + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_CITY_location + " TEXT, "
            + COL_AREA_location + " TEXT, "
            + COL_COUNTRY_location + " TEXT NOT NULL );" ;

    //Creation of the CHEMICAL table
    private static final String table_chemical = "table_chemical";
    private static final String COL_ID_chemical = "ID";
    private static final String COL_FORMULA_chemical = "FORMULA";
    private static final String COL_CLASS_chemical = "CLASS";

    private static final String CREATE_TABLE_chemical = "CREATE TABLE " + table_chemical + " ("
            + COL_ID_chemical + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COL_FORMULA_chemical + " TEXT NOT NULL, "
            + COL_CLASS_chemical + " INTEGER );";

    //Creation of the MINERAL table
    private static final String table_mineral = "table_mineral";
    private static final String COL_ID_mineral = "ID";
    private static final String COL_NAME_mineral = "NAME";
    private static final String COL_SYNONYM_mineral = "SYNONYM";
    private static final String COL_MINASS_mineral = "MINASS";
    private static final String COL_SYSTCRIST_mineral = "SYSTCRIST";
    private static final String COL_COLOR_mineral = "COLOR";
    private static final String COL_GLOW_mineral = "GLOW";
    private static final String COL_ASPECT_mineral = "ASPECT";
    private static final String COL_CLIVAGE_mineral = "CLIVAGE";
    private static final String COL_HARDNESS_mineral = "HARDNESS";
    private static final String COL_DENSITY_mineral = "DENSITY";
    private static final String COL_PRICE_mineral = "PRICE";
    private static final String COL_LOCATION_mineral = "LOCATION";
    private static final String COL_FK_user = "FK_USER";
    private static final String COL_FK_location = "FK_LOCATION";
    private static final String COL_FK_chemical = "FK_CHEMICAL";

    private static final String CREATE_TABLE_mineral = "CREATE TABLE " + table_mineral + " ( "
            + COL_ID_mineral + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME_mineral + " TEXT NOT NULL, "
            + COL_SYNONYM_mineral + " TEXT, "
            + COL_MINASS_mineral + " TEXT, "
            + COL_SYSTCRIST_mineral + " TEXT, "
            + COL_COLOR_mineral + " TEXT, "
            + COL_GLOW_mineral + " TEXT, "
            + COL_ASPECT_mineral + " TEXT, "
            + COL_CLIVAGE_mineral + " TEXT, "
            + COL_HARDNESS_mineral + " REAL, "
            + COL_DENSITY_mineral + " REAL, "
            + COL_PRICE_mineral + " REAL, "
            + COL_LOCATION_mineral + " TEXT, "
            + COL_FK_user + " INTEGER,"
            + COL_FK_location + " INTEGER, "
            + COL_FK_chemical + " INTEGER, "
            + " FOREIGN KEY (" + COL_FK_user + ") REFERENCES " + table_user + "(" + COL_ID_user  + "), "
            + " FOREIGN KEY (" + COL_FK_location + ") REFERENCES " + table_location + "(" + COL_ID_location  + "), "
            + " FOREIGN KEY (" + COL_FK_chemical + ") REFERENCES " + table_chemical + "(" + COL_ID_chemical  + ") ); ";



    public MineralGestion_DB_SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_user);
        sqLiteDatabase.execSQL(CREATE_TABLE_location);
        sqLiteDatabase.execSQL(CREATE_TABLE_chemical);
        sqLiteDatabase.execSQL(CREATE_TABLE_mineral);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_user);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_location);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_chemical);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + table_mineral);
        onCreate(sqLiteDatabase);
    }
}
