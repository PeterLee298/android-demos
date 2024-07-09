package com.bitmask.android.sqlitesimple;

import android.app.Application;

import com.bitmask.android.sqlitesimple.sdk.DBHelperUtil;

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据库
        DBHelperUtil.init(this);
    }
}
