package com.qk.applibrary.listener;

import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库升级监听器
 */
public interface DbUpdateListener {
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
