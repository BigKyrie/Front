package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.Movie;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilmListActivity extends AppCompatActivity {
    private static final String TAG = "filmlist";
    private List<Movie> mData = new ArrayList<>();;
    private Context mContext;
    private FilmAdapter mAdapter = null;
    private ListView listview;
    private Button search;
    private EditText searchInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_list);
        mContext = FilmListActivity.this;
        listview = findViewById(R.id.listview);
        search = findViewById(R.id.search);
        searchInfo = findViewById(R.id.et_1);


        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.GETALLFILM)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String data = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData = JSONArray.parseArray(data,Movie.class);
                        mAdapter = new FilmAdapter(mData, mContext);
                        listview.setAdapter(mAdapter);
//                        Toast.makeText(FilmListActivity.this, "Lead actor: "+mData.get(0).getUrl(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(FilmListActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });

        // search
        search.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                String keyword = searchInfo.getText().toString();
                FormBody formBody = new FormBody.Builder().add("keyword", keyword).build();
                Request request1 = new Request.Builder()
                        .url(Constant.SEARCH)
                        .post(formBody)
                        .build();
                Call call1 = okHttpClient.newCall(request1);
                call1.enqueue(new Callback() {

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String data = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mData = JSONArray.parseArray(data, Movie.class);
                                mAdapter = new FilmAdapter(mData, mContext);
                                listview.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();

//                        Toast.makeText(FilmListActivity.this, "Lead actor: "+mData.get(0).getUrl(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(FilmListActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                });
            }
        });

    }
}
