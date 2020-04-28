package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.example.myapplication.model.dao.Constant;
import com.example.myapplication.model.entity.Movie;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;



import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay2);

        Button Post1 = findViewById(R.id.rb_1);
        Button Post2 = findViewById(R.id.rb_2);

        Bundle bundle = getIntent().getExtras();
        int Screening_id = bundle.getInt("Screening_id");
        int Seat_id = bundle.getInt("Seat_id");
        int Type_id = bundle.getInt("Type");

        String user_id = MainActivity.user_id.toString();
        String Type;
        if (Type_id == 0) {
            Type = "Adult Ticket";
        }
        else if (Type_id==2){
            Type = "Child Ticket";
        }
        else {
            Type = "Elderly Ticket";
        }

        Post1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder5 = new AlertDialog.Builder(PayActivity.this);
                View payview1 = LayoutInflater.from(PayActivity.this).inflate(R.layout.pay_dialog2, null);
                Button btnpass1 = payview1.findViewById(R.id.btn_pay2);
                btnpass1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (MainActivity.flag == 0){
                            Toast.makeText(PayActivity.this, "please login first", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            OkHttpClient okHttpClient = new OkHttpClient();

                            FormBody formBody = new FormBody.Builder().add("type", Type).add("user_id", user_id).build();

                            Request request = new Request.Builder()
                                    .url(Constant.CASHPAY+Screening_id+"/"+Seat_id)
                                    .post(formBody)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    Looper.prepare();
                                    String re = response.body().string();
                                    Toast.makeText(PayActivity.this, re, Toast.LENGTH_SHORT).show();
                                    if (Boolean.parseBoolean(re)) {
                                        Toast.makeText(PayActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(PayActivity.this,MainActivity.class);
                                                PayActivity.this.startActivity(intent);
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(PayActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                    Looper.loop();
                                }

                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Looper.prepare();
                                    Toast.makeText(PayActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            });
                        }

                    }
                });
                builder5.setTitle("Please pay cash at the counter:").setView(payview1).show();
            }
        });

        Post2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder6 = new AlertDialog.Builder(PayActivity.this);
                View payview2 = LayoutInflater.from(PayActivity.this).inflate(R.layout.pay_dialog1, null);
                EditText Password = payview2.findViewById(R.id.et_password);
                Button btnpass2 = payview2.findViewById(R.id.btn_pay1);
                btnpass2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (MainActivity.flag == 0){
                            Toast.makeText(PayActivity.this, "please login first", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String password= (Password.getText().toString());
                            OkHttpClient okHttpClient = new OkHttpClient();

                            FormBody formBody = new FormBody.Builder().add("password", password).add("type", Type).add("user_id", user_id).build();

                            Request request = new Request.Builder()
                                    .url(Constant.CARDPAY+Screening_id+"/"+Seat_id)
                                    .post(formBody)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    Looper.prepare();
                                    String re = response.body().string();
                                    Toast.makeText(PayActivity.this, re, Toast.LENGTH_SHORT).show();
                                    if (Boolean.parseBoolean(re)) {
                                        Toast.makeText(PayActivity.this, "succeeded", Toast.LENGTH_SHORT).show();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(PayActivity.this,MainActivity.class);
                                                PayActivity.this.startActivity(intent);
                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(PayActivity.this, "failed", Toast.LENGTH_SHORT).show();
                                    }
                                    Looper.loop();
                                }

                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                    Looper.prepare();
                                    Toast.makeText(PayActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            });
                        }
                    }
                });
                builder6.setTitle("Please enter bank card password").setView(payview2).show();
            }
        });

    }
}
