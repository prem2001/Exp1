package com.example.prem.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by prem on 9/3/18.
 */

public class SessionManager {
    private static final String PREF_NAME = "Indglobal_chat_app";
    private static final int PRIVATE_MODE = 0;
    private static final String TOKEN = "token";
    private static final String IS_LOGIN = "is_login";
    private final Context mContext;
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        this.mContext = context;
        pref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public  void setToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }
    public String getToken(){
        return  pref.getString(TOKEN,null);
    }

    public void isLogin(Boolean success) {
        editor.putBoolean(IS_LOGIN,success);
        editor.commit();
    }
    public boolean getIsLogin(){
        return pref.getBoolean(IS_LOGIN,false);
    }
}
