package brianjenkins.cs360.a8bitcafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "menuDatabase.db";     // create database
    public static final String TABLE_NAME = "coffeeMenu";             // create table
    public static final String COL_1 = "ID";
    public static final String COL_2 = "COFFEENAME";                  // create column for coffee
    public static final String COL_3 = "COFFEEDESCRIPTION";           // create column for description
    public static final String COL_4 = "COFFEEPRICE";                 // create column for price


    public DatabaseHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, COFFEENAME TEXT, COFFEEDESCRIPTION TEXT, COFFEEPRICE DOUBLE)");
        //sqLiteDatabase.execSQL("INSERT INTO ")
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String coffeeName, String coffeeDescrip, double coffeePrice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, coffeeName);
        contentValues.put(COL_3, coffeeDescrip);
        contentValues.put(COL_4, coffeePrice);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " +TABLE_NAME,null);
        return res;
    }
}
