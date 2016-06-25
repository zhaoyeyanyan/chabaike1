package test.study.com.mychabaike.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * create in 2016/3/31 20:46 by xinquan(version 1.0)
 * function:
 */
public class DBUtils {
    private Context context;
    private SQLiteDatabase db;
    private MyDBHelper myHelp;

    public DBUtils(Context context) {
        this.context = context;
        myHelp = new MyDBHelper(context);
    }

    public long insert(ContentValues values) {
        db = myHelp.getWritableDatabase();
        return db.insert("collections", null, values);
    }

    public int delete(String whereClause, String[] whereArgs) {
        db = myHelp.getWritableDatabase();
        return db.delete("collections", whereClause, whereArgs);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        db = myHelp.getWritableDatabase();
        return db.update("collections", values, whereClause, whereArgs);
    }

    public Cursor query(String selection, String[] selectionArgs) {
        db = myHelp.getReadableDatabase();
        return db.query("collections", null, selection, selectionArgs, null, null, null);
    }

    public Cursor queryAll() {
        db = myHelp.getReadableDatabase();
        return db.query("collections", null, null, null, null, null, null);
    }
    }


