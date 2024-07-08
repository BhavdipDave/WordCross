package com.aswdc_wordcross.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import com.aswdc_wordcross.Bean.Bean_ThreeLetters;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivityThreeLetter extends SQLiteAssetHelper {

    public static String dbName = "letters.db";
    public static int dbVersion = 1;
    static final String tableName = "WC_ThreeLetter";


    public DatabaseActivityThreeLetter(Context context) {
        super(context, dbName,null, dbVersion);


    }

    public List<Bean_ThreeLetters> getWords()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String sqlSelect = "Select ID,words from WC_ThreeLetter";
        qb.setTables(tableName);
        Cursor cursor = db.rawQuery(sqlSelect,null);
        List<Bean_ThreeLetters> result = new ArrayList<>();


        if(cursor.moveToFirst()) {
            do {
                Bean_ThreeLetters bean_threeLetters = new Bean_ThreeLetters();
                bean_threeLetters.setWordID(cursor.getInt(cursor.getColumnIndex("ID")));
                bean_threeLetters.setWords(cursor.getString(cursor.getColumnIndex("words")));
                result.add(bean_threeLetters);

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
        String sqlSelect = "Select ID,words from WC_ThreeLetter";
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
            String sqlSelect = "SELECT words FROM " + Bean_ThreeLetters.TABLE_NAME + " WHERE "+ Bean_ThreeLetters.COLUMN_ID+"="+id;
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
        String countQuery = "SELECT  * FROM " + Bean_ThreeLetters.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.moveToFirst();
        cursor.close();
        return count;
    }
}
