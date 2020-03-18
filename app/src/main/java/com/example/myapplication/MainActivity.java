package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button register = findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);

            }


        });

        Button film = findViewById(R.id.film);
        film.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilmListActivity.class);
                startActivity(intent);

            }


        });

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
                FormBody formBody = new FormBody.Builder().add("username", username).add("password", password).build();
                Request request = new Request.Builder()
                        .url(Constant.GET)
                        .post(formBody)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        List<User> users = JSONArray.parseArray(response.body().string(), User.class);
                        Looper.prepare();
                        if (username.length() == 0 || password.length() == 0) {
                            Toast.makeText(MainActivity.this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show();

                        } else {
                            if (users.size() == 0) {
                                Toast.makeText(MainActivity.this, "Username or password not correct", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Login is successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Looper.loop();
                    }
                });
            }
        });
    }
}

