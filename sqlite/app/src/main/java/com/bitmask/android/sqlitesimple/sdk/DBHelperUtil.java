package com.bitmask.android.sqlitesimple.sdk;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bitmask.android.sqlitesimple.R;

public class DBHelperUtil {
    private static final String DB_NAME = "demo.db";
    private static Application application;

    private static MySQLiteOpenHelper sqLiteOpenHelper;
    private static final int DB_VERSION = 1;

    public static void init(Application application){
        DBHelperUtil.application = application;
    }

    public static class MySQLiteOpenHelper extends SQLiteOpenHelper{

        public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            for (String tableName : RegeditParser.getInstance().getTableNames(application, R.xml.db_tables)){
                ITable table = getTable(tableName);
                if (table != null) {
                    table.createTable(db);
                }
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < newVersion){
                for (String tableName : RegeditParser.getInstance().getTableNames(application, R.xml.db_tables)){
                    ITable table = getTable(tableName);
                    if (table!= null){
                        table.upgradeTable(db, oldVersion, newVersion);
                    }
                }
            }
        }

        private ITable getTable(String tableName){
            try {
                return (ITable) Class.forName(tableName).newInstance();
            } catch (IllegalAccessException e) {
                // throw new RuntimeException(e);
            } catch (InstantiationException e) {
                // throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                // throw new RuntimeException(e);
            }
            return null;
        }
    }

    public static void closeDatabase(){
//        if (sqLiteOpenHelper!= null){
//            sqLiteOpenHelper.close();
//        }
    }

    public static synchronized SQLiteDatabase getDatabase() throws Exception{
        SQLiteDatabase writeDatabase = null;
        synchronized (DBHelperUtil.class) {
            if (sqLiteOpenHelper == null) {
                sqLiteOpenHelper = new MySQLiteOpenHelper(application, DB_NAME, null, DB_VERSION);
            }
            try {
                writeDatabase = sqLiteOpenHelper.getWritableDatabase();
            } catch (Exception e) {
                application.deleteDatabase(DB_NAME);
                return sqLiteOpenHelper.getWritableDatabase();
            }
        }
        return writeDatabase;
    }
}

