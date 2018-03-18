package com.example.prem.myrecycler.premrecyclerviewgson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.prem.R;
import com.example.prem.interfaces.OnItemClickListener;
import com.example.prem.network.AppController;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity1 extends Activity {

    private static final String GET_COUNTRY_URL = "https://www.trabaajo.com/webservice/rest/pretable/getcountryid";
    private RecyclerView mRecyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        mRecyclerView=findViewById(R.id.myrecyclerview);
        mContext=getApplicationContext();
        Log.d("THREAD1","thread name: "+Thread.currentThread().getName()
        +" Thread ID: "+Thread.currentThread().getId());


        Map<String, String> postParam = new HashMap<>();

        postParam.put("candidate_id", "");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, GET_COUNTRY_URL,
                new JSONObject(postParam), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                MyCountryResponce myCountryResponce = gson.fromJson(response.toString(), MyCountryResponce.class);

                setUI(myCountryResponce);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }

    private void setUI(final MyCountryResponce myCountryResponce) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.VERTICAL, false));
        MyAdapter adapter = new MyAdapter(mContext, myCountryResponce.getData());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()){
                    case (R.id.button):
                        Intent intent = new Intent(getApplicationContext(),Activity2.class);
                        intent.putExtra("data",myCountryResponce.getData().get(position).getCountry_name());

                        startActivity(intent);
                        break;
                        default:
                            Toast.makeText(getApplicationContext(),"List is clicked",Toast.LENGTH_SHORT).show();
                            break;
                }


            }
        });
    }
}
