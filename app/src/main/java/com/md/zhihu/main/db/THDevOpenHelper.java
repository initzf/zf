package com.md.zhihu.main.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.md.dao.DaoMaster;

public class THDevOpenHelper extends DaoMaster.DevOpenHelper {

    public THDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
        onCreate(db);
    }
}
