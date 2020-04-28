package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.Cinema;
import com.example.myapplication.model.entity.Movie;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CinemaListActivity extends AppCompatActivity {

    private static final String TAG = "cinema";
    private ListView Cinema;
    private List<Cinema> mData = new ArrayList<>();
    ;
    private Context mContext = CinemaListActivity.this;
    private CinemaAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_list);
        Cinema = findViewById(R.id.cinemalist);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
//        Toast.makeText(CinemaListActivity.this, id, Toast.LENGTH_SHORT).show();

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.GETALLCINEMA + id)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String data = response.body().string();
                Log.d(TAG, "onResponse: "+data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData = JSONArray.parseArray(data, Cinema.class);
                        if(mData.size() == 0)
                        {
                            //Toast.makeText(CinemaListActivity.this,"0",Toast.LENGTH_SHORT).show();
                        }else {
                            mAdapter = new CinemaAdapter(mData, mContext, id);
                            Cinema.setAdapter(mAdapter);
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(CinemaListActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
    }
}
