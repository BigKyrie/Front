package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.os.Looper;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.alibaba.fastjson.JSONArray;
import com.example.myapplication.model.dao.*;
import com.example.myapplication.model.entity.*;
import android.view.View;
import android.content.Intent;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button register = findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password= ((EditText) findViewById(R.id.password)).getText().toString();

                FormBody formBody = new FormBody.Builder().add("username", username).add("password",password).build();

                Request request = new Request.Builder()
                        .url(Constant.ADD)
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Looper.prepare();
//                            if (Integer.parseInt(response.body().string())==3)
//                            {
//                                Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();
//                            }
//                            else if (Integer.parseInt(response.body().string())==2) {
//                                Toast.makeText(this, "Length of username or password must be between 1-20", Toast.LENGTH_SHORT).show();
//                            }
//                            else if (Integer.parseInt(response.body().string())==1) {
//                                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
//                            }
                        if (Boolean.parseBoolean(response.body().string())) {
                            Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if (username.length()>20 || username.length()==0 || password.length()>20 || password.length()==0) {
                            Toast.makeText(RegisterActivity.this, "Length of username or password must be between 1-20", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    }
                });

            }


        });

//        Button register = findViewById(R.id.register);
//        register.setOnClickListener(
//                v ->
//                {
//                    new Thread(()-> {
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        String username = ((EditText) findViewById(R.id.username)).getText().toString();
//                        String password= ((EditText) findViewById(R.id.password)).getText().toString();
//
//                        FormBody formBody = new FormBody.Builder().add("username", username).add("password",password).build();
//
//                        Request request = new Request.Builder()
//                                .url(Constant.ADD)
//                                .post(formBody)
//                                .build();
//                        try (Response response = okHttpClient.newCall(request).execute()) {
//                            Looper.prepare();
////                            if (Integer.parseInt(response.body().string())==3)
////                            {
////                                Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();
////                            }
////                            else if (Integer.parseInt(response.body().string())==2) {
////                                Toast.makeText(this, "Length of username or password must be between 1-20", Toast.LENGTH_SHORT).show();
////                            }
////                            else if (Integer.parseInt(response.body().string())==1) {
////                                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
////                            }
//                            if (Boolean.parseBoolean(response.body().string())) {
//                                Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();
//                            }
//                            else if (username.length()>20 || username.length()==0 || password.length()>20 || password.length()==0) {
//                                Toast.makeText(this, "Length of username or password must be between 1-20", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
//                            }
//                            Looper.loop();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }).start();
//                }
//        );

        Button returnToLogin = findViewById(R.id.returnToLogin);
        returnToLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
