package com.aswdc_wordcross.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import com.aswdc_wordcross.Bean.Bean_FiveLetters;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseActivityFiveLetter extends SQLiteAssetHelper {

    public static String dbName = "letters.db";
    public static int dbVersion = 1;
    static final String tableName = "WC_FiveLetter";


    public DatabaseActivityFiveLetter(Context context) {
        super(context, dbName,null, dbVersion);


    }

    public List<Bean_FiveLetters> getWords()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sqlSelect = "Select ID,words from WC_FiveLetter";
        qb.setTables(tableName);
        Cursor cursor = db.rawQuery(sqlSelect,null);
        List<Bean_FiveLetters> result = new ArrayList<>();


        if(cursor.moveToFirst()) {
            do {
                Bean_FiveLetters bean_fiveLetters = new Bean_FiveLetters();
                bean_fiveLetters.setWordID(cursor.getInt(cursor.getColumnIndex("ID")));
                bean_fiveLetters.setWords(cursor.getString(cursor.getColumnIndex("words")));
                result.add(bean_fiveLetters);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return result;
    }

    public List<String> getString()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sqlSelect = "Select ID,words from WC_FiveLetter";
        qb.setTables(tableName);
        Cursor cursor = db.rawQuery(sqlSelect,null);
        List<String> result = new ArrayList<>();


        if(cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("words")));

            }while (cursor.moveToNext());
        }
        db.close();

        return result;
    }

    public String getWord(int id, Context mainActivity){
        SQLiteDatabase db = getReadableDatabase();
        String s="";
        try{
            String sqlSelect = "SELECT words FROM " + Bean_FiveLetters.TABLE_NAME + " WHERE "+ Bean_FiveLetters.COLUMN_ID+"="+id;
            s=sqlSelect;
            Cursor cursor = db.rawQuery(sqlSelect,null);
            cursor.moveToFirst();
            s= cursor.getString(cursor.getColumnIndex("words"));}

        catch (Exception e){
            Toast.makeText(mainActivity, "Data Is Not Available", Toast.LENGTH_SHORT).show();
        }
        return s;
    }

    public int getAllwordscount(){

        SQLiteDatabase db = getReadableDatabase();
        String countQuery = "SELECT  * FROM " + Bean_FiveLetters.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        cursor.close();
        return count;
    }
}