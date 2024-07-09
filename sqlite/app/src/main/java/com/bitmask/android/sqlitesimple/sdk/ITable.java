package com.bitmask.android.sqlitesimple.sdk;

import android.database.sqlite.SQLiteDatabase;

public interface ITable {
    void createTable(SQLiteDatabase database);

    void dropTable(SQLiteDatabase database);

    void upgradeTable(SQLiteDatabase database, int oldVersion, int newVersion);
}
