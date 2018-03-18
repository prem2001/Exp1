package com.example.prem.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prem.R;
import com.example.prem.Utility.SessionManager;
import com.example.prem.adapter.MessageAdapter;
import com.example.prem.database.DatabaseManager;
import com.example.prem.interfaces.OnItemClickListener;
import com.example.prem.model.MessageModel;
import com.example.prem.model.MsgResponceModel;
import com.example.prem.model.SenderMsgRes;
import com.example.prem.model.SenderMsgResponse;
import com.example.prem.network.AppController;
import com.example.prem.network.MyUtility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class SecondActivity extends Activity {

    private static final int PICK_IMAGE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_STROAGE = 2;
    String TAG = "SecondActivity";

    @BindView(R.id.message_edit_text)
    EditText messageEditText;


    @BindView(R.id.chat_recycler_view)
    RecyclerView chat_recycler_view;
    private ArrayList<MessageModel> messageModels;
    private MessageAdapter msgAdapter;
    private DatabaseManager dbManager;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        messageModels = new ArrayList<>();


        dbManager = new DatabaseManager(this);


        updateUi();

        /**
         * This method will run in every 2 second and the timer will stop on opPause() method
         * rightnow it is commented
         * if you uncomment this method kinly uncommend the time cancel in opPause()
         */
//        taskScheduler();

        msgAdapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                 messageModels.get(position);

            }
        });
    }

    private void taskScheduler() {
        timer = new Timer();
        TimerTask looper = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(getIntent());
            }
        };

        //2 sec
        timer.schedule(looper, 2000, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        runtimepermisstion();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        timer.cancel();

    }

    @OnClick(R.id.send_message_image_view)
    public void onSendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (!message.equals("")) {
            messageEditText.setText(null);
            addToDatabase(message, 0);
            updateUi();
            msgAdapter.notifyDataSetChanged();

            JsonObjectRequest jsonObjectRequest = null;
            try {

                JSONObject msg = MyUtility.getMessageJson(message, getApplicationContext());
                Log.d(TAG, "Request: " + msg.toString());

                String url=MyUtility.POST_MESSAGE_URL+ new SessionManager(this).getToken();
                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, msg, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        SenderMsgResponse msgResponse= gson.fromJson(response.toString(),SenderMsgResponse.class);
                        MsgResponceModel senderMsgResponse=msgResponse.getData();
                        SenderMsgRes senderMsgRes=senderMsgResponse.getChats();
                        Log.d(TAG,senderMsgRes.getMessage());
                        addToDatabase(senderMsgRes.getMessage(), Integer.parseInt(senderMsgRes.getType()));
                        updateUi();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString());

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        } else
            messageEditText.setError("Enter Message");
    }

    private void addToDatabase(String message, int i) {
        dbManager.open();
        dbManager.insertValues(message, i);
    }

    public void updateUi() {
        dbManager.open();
        messageModels = dbManager.getMessages(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);

        chat_recycler_view.setLayoutManager(mLayoutManager);

        msgAdapter = new MessageAdapter(getApplicationContext(), messageModels);
        chat_recycler_view.setAdapter(msgAdapter);

        msgAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.camera_image)
    public void startCamera() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImageUri,
                filePathColumn, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imagePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d(TAG, imagePath);
            addToDatabase(imagePath, 2);

            messageModels.add(new MessageModel(imagePath, "null1", 2));
            msgAdapter.notifyDataSetChanged();
            updateUi();


        }
    }

    private void runtimepermisstion() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {


            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_STROAGE);

            }
        } else {
            // Permission has already been granted
        }
    }


}

