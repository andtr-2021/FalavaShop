package com.example.falavashop.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "USER_LIST.DB";
    private static final int DB_VERSION = 1;


    public static final String TABLE_NAME = "User";
    public static final String ID = "id";
    public static final String HW_NAME = "Username";
    public static final String START_TIME = "Password";
    public static final String DURATION = "Login";
    public static final String COMPLETED = "SavePassword";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + HW_NAME + " TEXT NOT NULL," + START_TIME + " TEXT," + DURATION + " TEXT," + COMPLETED + " TEXT NOT NULL" + ");" ;

    public UserDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}





