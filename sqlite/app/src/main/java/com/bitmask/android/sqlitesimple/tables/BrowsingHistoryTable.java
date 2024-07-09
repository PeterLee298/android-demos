package com.bitmask.android.sqlitesimple.tables;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bitmask.android.sqlitesimple.modes.BrowsingHistoryBin;
import com.bitmask.android.sqlitesimple.sdk.DBHelperUtil;
import com.bitmask.android.sqlitesimple.sdk.ITable;

import java.util.ArrayList;
import java.util.List;

public class BrowsingHistoryTable implements ITable {

    public static final String TABLE_NAME = "browsing_history";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";

    @Override
    public void createTable(SQLiteDatabase database) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_URL + " TEXT UNIQUE, " + COLUMN_TITLE + " TEXT, " + COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + COLUMN_IS_FAVORITE + " INTEGER)";
        database.execSQL(sql);
    }

    @Override
    public void dropTable(SQLiteDatabase database) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        database.execSQL(sql);
    }

    @Override
    public void upgradeTable(SQLiteDatabase database, int oldVersion, int newVersion) {
        // TODO: Implement upgrade logic
        // For example, you can drop the old table and create a new one with updated columns
        dropTable(database);
        createTable(database);
    }

    public static void cleanTable() {
        try {
            DBHelperUtil.getDatabase().delete(TABLE_NAME, "1=1", null);
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
        DBHelperUtil.closeDatabase();
    }

    public static int deleteHistoryById(int id){
        int count = 0;
        try {
            count = DBHelperUtil.getDatabase().delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
        DBHelperUtil.closeDatabase();
        return count;
    }

    public static int deleteHistoryByUrl(String url){
        int count = 0;
        try {
            count = DBHelperUtil.getDatabase().delete(TABLE_NAME, COLUMN_URL + " =?", new String[]{url});
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
        DBHelperUtil.closeDatabase();
        return count;
    }

    public static void insertOrUpgrade(String url, String title, int isFavorite) {
        try {
            DBHelperUtil.getDatabase().execSQL("INSERT OR REPLACE INTO " + TABLE_NAME + " (" + COLUMN_URL + ", " + COLUMN_TITLE + ", " + COLUMN_IS_FAVORITE + ") VALUES (?,?,?)", new Object[]{url, title, isFavorite});
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
    }

    public static void insertOrUpgrade(BrowsingHistoryBin browsingHistoryBin) {
        try {
            DBHelperUtil.getDatabase().execSQL("INSERT OR REPLACE INTO " + TABLE_NAME + " (" + COLUMN_URL + ", " + COLUMN_TITLE + ", " + COLUMN_IS_FAVORITE + ") VALUES (?,?,?)", new Object[]{browsingHistoryBin.getUrl(), browsingHistoryBin.getTitle(), browsingHistoryBin.getIsFavorite()});
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
    }

    public static void insertOrUpgrade(List<BrowsingHistoryBin> browsingHistoryBins) {
        try {
            for (BrowsingHistoryBin browsingHistoryBin : browsingHistoryBins) {
                DBHelperUtil.getDatabase().execSQL("INSERT OR REPLACE INTO " + TABLE_NAME + " (" + COLUMN_URL + ", " + COLUMN_TITLE + ", " + COLUMN_IS_FAVORITE + ") VALUES (?,?,?)", new Object[]{browsingHistoryBin.getUrl(), browsingHistoryBin.getTitle(), browsingHistoryBin.getIsFavorite()});
            }
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }

    }

    public static List<BrowsingHistoryBin> getAll() {
        List<BrowsingHistoryBin> browsingHistoryBins = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, null, null, null, null, COLUMN_DATE + " DESC", null);

            if (cursor.moveToFirst()){
                do {
                    BrowsingHistoryBin browsingHistoryBin = new BrowsingHistoryBin();
                    browsingHistoryBin.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    browsingHistoryBin.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL)));
                    browsingHistoryBin.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                    browsingHistoryBin.setDate(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                    browsingHistoryBin.setIsFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)));
                    browsingHistoryBins.add(browsingHistoryBin);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        } finally {
            if (cursor!= null) {
                cursor.close();
            }
        }
        DBHelperUtil.closeDatabase();
        return browsingHistoryBins;
    }

    public static List<BrowsingHistoryBin> getByPage(int page, int pageSize) {
        // 分页查询
        List<BrowsingHistoryBin> browsingHistoryBins = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, null, null, null, null, COLUMN_DATE + " DESC", String.valueOf(page * pageSize) + "," + String.valueOf(pageSize));

            if (cursor.moveToFirst()){
                do {
                    BrowsingHistoryBin browsingHistoryBin = new BrowsingHistoryBin();
                    browsingHistoryBin.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                    browsingHistoryBin.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL)));
                    browsingHistoryBin.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                    browsingHistoryBin.setDate(cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE)));
                    browsingHistoryBin.setIsFavorite(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_FAVORITE)));

                    browsingHistoryBins.add(browsingHistoryBin);
                }while (cursor.moveToNext());
            }
            cursor.close();

        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
        return browsingHistoryBins;
    }

    public static int getCount() {
        int count = 0;
        try {
            Cursor cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, null, null, null, null, null);
            count = cursor.getCount();
            cursor.close();
            DBHelperUtil.closeDatabase();
        } catch (Exception e) {
            DBHelperUtil.closeDatabase();
            throw new RuntimeException(e);
        }
        return count;
    }

}
