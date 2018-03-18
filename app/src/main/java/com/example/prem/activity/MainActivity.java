package com.example.prem.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prem.R;
import com.example.prem.Utility.DialogUtil;
import com.example.prem.Utility.SessionManager;
import com.example.prem.network.AppController;
import com.example.prem.network.MyUtility;
import com.example.prem.responcemodel.LoginResponce;
import com.example.prem.runtimepermission.MarshMallowPermission;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;


    @BindView(R.id.password_text_view)
    EditText passwordET;

    @BindView(R.id.mobile_text_view)
    EditText mobileET;
    private MarshMallowPermission marshMallowPermission=new MarshMallowPermission(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        ///
//        if (new SessionManager(this).getIsLogin()) {
//            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
//        }

        if (!marshMallowPermission.checkPermissionForInternet()) {
            marshMallowPermission.requestPermissionForInternet();
        }

    }


    @OnClick(R.id.login_button)
    public void onButton() {


        String mobileNo = mobileET.getText().toString().trim();
//        String mobileNo = "9090909091";
        String passord = passwordET.getText().toString().trim();
//        String passord = "123456";
        if (validation(mobileNo, passord)) {
            DialogUtil.showProgressDialog(this, "Loging in...");
            JsonObjectRequest jsonObjectRequest = null;
            try {
                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, MyUtility.LOGIN_URL, MyUtility.getLoginParam(mobileNo, passord), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Gson gson = new Gson();
                        LoginResponce loginResponce = gson.fromJson(response.toString(), LoginResponce.class);
                        DialogUtil.hideProgressDialog();
                        if (loginResponce.getSuccess()) {
                            Log.d(TAG, response.toString());
                            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                            saveToSessionManager(loginResponce);
                        } else {
                            //invalid userbname and password

                            Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        DialogUtil.hideProgressDialog();
                        Toast.makeText(getApplicationContext(),"Invalid credential",Toast.LENGTH_SHORT).show();

                        Log.d(TAG,error.toString());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        }
    }

    private void saveToSessionManager(LoginResponce loginResponce) {
        new SessionManager(getApplicationContext()).setToken(loginResponce.getData().getToken());
        new SessionManager(getApplicationContext()).isLogin(loginResponce.getSuccess());

    }

    private boolean validation(String mobileNo, String passord) {
        boolean value = true;
        if (mobileNo.equals("")) {
            value = false;
            mobileET.setError("Enter Mobile no");
        } else if (passord.equals("")) {
            passwordET.setError("Enter Password");
            value = false;
        }
        return value;
    }

}
