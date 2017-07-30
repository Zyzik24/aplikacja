package com.example.zuzia.oszczedzanie2.Model.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountancyDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Accountancy.db";

    public AccountancyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public AccountancyDbHelper(Context context, int database_version) {
        super(context, DATABASE_NAME, null, database_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AccountancyContract._SQL_ENABLE_FOREIGN_KEY_SUPPORT);
        db.execSQL(AccountancyContract._SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            //edit when needed
        }
    }

    public void delete(SQLiteDatabase db) {
        db.execSQL(AccountancyContract._SQL_DISABLE_FOREIGN_KEY_SUPPORT);
        db.execSQL(AccountancyContract._SQL_DELETE_ENTRIES);
    }
}
