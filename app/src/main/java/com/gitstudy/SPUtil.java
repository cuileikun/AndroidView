package com.gitstudy;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mbcloud-cuilk on 2018/7/11.
 */
public class SPUtil {
    private static String SP_NAME = "cmb_zn_sp";

    public static void putString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    public static void putInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }
    public static void putFloat(Context context,String key,float value){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().putFloat(key,value).commit();
    }
    public static void putLong(Context context,String key,long value){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().putLong(key,value).commit();
    }
    public static void putBool(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }
    public static String getString(Context context,String key,String def){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp.getString(key,def);
    }
    public static int getInt(Context context,String key,int def){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp.getInt(key,def);
    }
    public static boolean getBool(Context context,String key,boolean def){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp.getBoolean(key,def);
    }
    public static float getFloat(Context context,String key,float def){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp.getFloat(key,def);
    }
    public static long getLong(Context context,String key,long def){
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sp.getLong(key,def);
    }
}
