package com.example.prem.network;

import android.content.Context;

import com.example.prem.Utility.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prem on 9/3/18.
 */

public class MyUtility {
    public static final String LOGIN_URL = "http://phplaravel-92226-383167.cloudwaysapps.com/api/v1/authenticate/login";
    public static final String POST_MESSAGE_URL = "http://phplaravel-92226-383167.cloudwaysapps.com/api/v1/public_group_chat_store"+"?token=";

    public static JSONObject getLoginParam(String mobileNo, String passord) throws JSONException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("mobile_no",mobileNo);
        jsonObject.put("password",passord);
        jsonObject.put("role","3");

        return jsonObject;
    }

    public static JSONObject getMessageJson(String message, Context context) throws JSONException {
        JSONObject jsonObject=new JSONObject();
//                jsonObject.put("token",new SessionManager(context).getToken());
        jsonObject.put("group_id","1");
        jsonObject.put("type","1");
        jsonObject.put("message",message);



        return  jsonObject;
    }
}
