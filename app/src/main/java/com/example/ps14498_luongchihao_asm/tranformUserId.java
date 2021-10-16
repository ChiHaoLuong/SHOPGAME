package com.example.ps14498_luongchihao_asm;

import android.content.Context;
import android.content.SharedPreferences;

public interface tranformUserId {
    static final String TRUYEN_DU_LIEU = "TRUYEN_DU_LIEU";

    public static void putUserId(Context context, int value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(TRUYEN_DU_LIEU, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", value);
        editor.apply();
    }

    public static int getUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(TRUYEN_DU_LIEU, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("userId", 1000);
    }
}
