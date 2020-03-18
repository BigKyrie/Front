package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.Cinema;
import com.example.myapplication.model.entity.Movie;
import com.example.myapplication.model.entity.Time;


import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;


public class TimeActivity extends AppCompatActivity {

    private ListView Time;
    private List<Time> mData = new ArrayList<>();;
    private Context mContext = TimeActivity.this;
    private TimeAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_list);
        Time = findViewById(R.id.timelist);
        mAdapter = new TimeAdapter(mData,mContext);
        Time.setAdapter(mAdapter);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        Toast.makeText(TimeActivity.this, id, Toast.LENGTH_SHORT).show();
    }
}
