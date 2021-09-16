package com.example.mycalculate;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

    private static String SP_NAME = "noteSp";

    public static  void saveString(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();

    }

    public static String getString(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public static  void saveInt(Context context, String key, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        //editor.apply();
        editor.commit();
    }

    public static int getInt(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,-1);
    }

    public static int getIntWithDefault(Context context,String key,int def){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,def);
    }
}
