package test.study.com.mychabaike.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * create in 2016/3/31 20:50 by xinquan(version 1.0)
 * function:
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "collection.db";
    private static int VERSION = 1;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS [collections] ([c_des] TEXT(300), [c_content] TEXT(500));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         if(newVersion>oldVersion){
             String sql = "drop table if exists user";
             db.execSQL(sql);
             onCreate(db);
         }
    }
}
