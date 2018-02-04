package com.babytt.dataaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAccount extends SQLiteOpenHelper {
	
    private static final String DATABASE_NAME = "account.db";
    private static final int DATABASE_VERSION = 1;
    private static String sql = "create table account (" + "_id integer primary key autoincrement, "
                                + "name text, " + "money text, " + "remark text)";
    
	public DBAccount(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
