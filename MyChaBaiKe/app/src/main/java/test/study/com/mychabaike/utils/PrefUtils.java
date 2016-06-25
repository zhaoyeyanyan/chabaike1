package test.study.com.mychabaike.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * create in 2016/6/20 11:53 by xinquan(version 1.0)
 * function:
 */
public class PrefUtils {
    /**
     * @param context
     * @param name
     * @return
     */
    public static boolean isFirst(Context context,String name){
        SharedPreferences preferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        int first_num = preferences.getInt(name,1);
        if(first_num == 1){
          return  true;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name,2);
        return false;
    }
}
