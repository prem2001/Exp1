package com.example.prem.myrecycler.premrecyclerviewgson;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.prem.R;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(this,getIntent().getStringExtra("data"),Toast.LENGTH_SHORT).show();
        Log.d("THREAD2","thread name: "+Thread.currentThread().getName()
                +" Thread ID: "+Thread.currentThread().getId());
        //prem
    }
}
