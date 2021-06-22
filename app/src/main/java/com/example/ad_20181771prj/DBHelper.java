
package com.example.ad_20181771prj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "FRIEND";
    static final String COL_ID = "ID";
    static final String COL_TITLE = "TITLE"; //영화제목
    static final String COL_RATING ="RATING"; //별점
    static final String COL_REVIEW = "REVIEW"; //리뷰 내용


    static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + "(" +
            COL_ID + "INTEGER NOT NULL PRIMARY KEY ," +
            COL_TITLE + " TEXT NOT NULL, " +
            COL_RATING + " TEXT NOT NULL," +
            COL_REVIEW + " TEXT NOT NULL)";    // 아디 + 영화제목 + 별점 + 리뷰내용 이런식으로 보이게할거임


    static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    static final String SQL_LOAD = "SELECT * FROM " + TABLE_NAME;
    static final String SQL_SELECT = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_TITLE + "=?";
    static final String SQL_SELECT_ID = "SELECT ID FROM " + TABLE_NAME + " WHERE " + COL_TITLE + "=?";
    private static final Context applicationContext = null;


    public DBHelper( Context context) {
        super(context, "friend.db",null,1);
    }


    public void update(String title, Float rating, String review, int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 정보 수정
        db.execSQL("UPDATE FRIEND SET title=" + title + " WHERE rating='" + rating + "' and review='"+review+"'+  and id='"+ id +"';");
        db.close();
    }

    public void delete(String title) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM FRIEND WHERE title='" + title + "';");
        db.close();
    }


    @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DROP_TABLE);
            db.execSQL(SQL_CREATE_TABLE);

        }



}

