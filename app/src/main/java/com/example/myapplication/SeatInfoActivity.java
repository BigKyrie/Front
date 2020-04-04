package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.bumptech.glide.Glide;
import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.SeatInfo;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SeatInfoActivity extends AppCompatActivity
{

    private GridView mGv;
    private ImageView mIv4;
    private List<SeatInfo> mData = new ArrayList<>();
    private SeatAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_info);

        mGv = findViewById(R.id.gv);
        mIv4 = findViewById(R.id.iv_4);
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1585766851861&di=5a7914cd48602ba7ec04edbafddfdd64&imgtype=0&src=http%3A%2F%2Farticle.fd.zol-img.com.cn%2Ft_s640x2000%2Fg5%2FM00%2F08%2F0F%2FChMkJloaiXSIRkabAAAj33mmx2cAAiemALgW1MAACP3535.jpg").into(mIv4);

        Bundle bundle = getIntent().getExtras();
        String Screening_id = bundle.getString("Screening_id");

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constant.GETSEATINFO+Screening_id)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String data = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mData = JSONArray.parseArray(data, SeatInfo.class);
                        if(mData.size() == 0)
                        {
                            Toast.makeText(SeatInfoActivity.this,"no object",Toast.LENGTH_SHORT).show();
                        }else {
                            mAdapter = new SeatAdapter(mData, SeatInfoActivity.this);
                            mGv.setAdapter(mAdapter);
                            mGv.setNumColumns(mData.get(mData.size()-1).getCol());
                            //Toast.makeText(SeatInfoActivity.this,(mData.get(mData.size()-1).getCol())+"" , Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(SeatInfoActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        });


    }
}
