package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.Cinema;
import com.example.myapplication.model.entity.Movie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CinemaListActivity extends AppCompatActivity {

    private ListView Cinema;
    private List<Cinema> mData = new ArrayList<>();;
    private Context mContext = CinemaListActivity.this;
    private CinemaAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_list);
        Cinema = findViewById(R.id.cinemalist);
        mAdapter = new CinemaAdapter(mData,mContext);
        Cinema.setAdapter(mAdapter);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        Toast.makeText(CinemaListActivity.this, id, Toast.LENGTH_SHORT).show();

//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(Constant.GETALLFILM)
//                .build();
//        Call call = okHttpClient.newCall(request);
    }
}
