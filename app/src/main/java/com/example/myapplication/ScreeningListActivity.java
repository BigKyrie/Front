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
import com.example.myapplication.model.entity.Screening;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScreeningListActivity extends AppCompatActivity {
    private static final String TAG = "Screeninglist";
    private List<Screening> mData = new ArrayList<>();
    private Context mContext;
    private ScreeningAdapter mAdapter = null;
    private ListView screeninglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screening_list);
        screeninglist = findViewById(R.id.screeninglist);

        mContext = ScreeningListActivity.this;

        Bundle bundle = getIntent().getExtras();
        String Movie_id = bundle.getString("Movie_id");
        String Cinema_id = bundle.getString("Cinema_id");
        //Toast.makeText(ScreeningListActivity.this, Movie_id+" "+Cinema_id, Toast.LENGTH_SHORT).show();

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.GETALLSCREENING+Movie_id+"/"+Cinema_id)
                .build();
        //Toast.makeText(ScreeningListActivity.this, Constant.GETALLSCREENING+Movie_id+"/"+Cinema_id, Toast.LENGTH_SHORT).show();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String data = response.body().string();
                Log.d(TAG, "onResponse: "+data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData = JSONArray.parseArray(data, Screening.class);
                        if(mData.size() == 0)
                        {
                            Toast.makeText(ScreeningListActivity.this,"no object",Toast.LENGTH_SHORT).show();
                        }else {
                            mAdapter = new ScreeningAdapter(mData, mContext, Movie_id);
                            screeninglist.setAdapter(mAdapter);
//                            Toast.makeText(ScreeningListActivity.this,mData.get(0).toString(),Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get 1: "+mData.get(0).getMovie().getTitle());
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(ScreeningListActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });
    }
}
