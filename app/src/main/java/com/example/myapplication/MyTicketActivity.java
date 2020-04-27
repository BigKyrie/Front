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
import com.example.myapplication.model.entity.Ticket;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class MyTicketActivity extends AppCompatActivity {
    private List<Ticket> mData = new ArrayList<>();;
    private Context mContext;
    private MyTicketAdapter mAdapter = null;
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket);
        mContext = MyTicketActivity.this;
        listview = findViewById(R.id.ticketlist);


        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody formBody = new FormBody.Builder().add("user_id", MainActivity.user_id.toString()).build();
        Request request = new Request.Builder()
                .url(Constant.MYTICKETS)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String data = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData = JSONArray.parseArray(data,Ticket.class);
                        mAdapter = new MyTicketAdapter(mData, mContext);
                        listview.setAdapter(mAdapter);
                    }
                });

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(MyTicketActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });


    }
}
