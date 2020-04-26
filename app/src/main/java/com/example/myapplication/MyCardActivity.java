package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.dao.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);

        Button card = findViewById(R.id.btn_card);
        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String cardNumber = ((EditText) findViewById(R.id.et_1)).getText().toString();
                String password= ((EditText) findViewById(R.id.et_2)).getText().toString();
                String password2= ((EditText) findViewById(R.id.et_3)).getText().toString();

                if (password.equals(password2)) {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    FormBody formBody = new FormBody.Builder().add("card_number", cardNumber).add("password",password).build();
                    Request request = new Request.Builder()
                            .url(Constant.CARD)
                            .post(formBody)
                            .build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            Looper.prepare();
                            String re = response.body().string();
                            Toast.makeText(MyCardActivity.this,re, Toast.LENGTH_SHORT).show();
                            if (Boolean.parseBoolean(re)) {
                                Toast.makeText(MyCardActivity.this, "Bank card binding successfully", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MyCardActivity.this, "Bank card binding failed", Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();
                        }

                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            Looper.prepare();
                            Toast.makeText(MyCardActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    });
                }
                else {
                    Toast.makeText(MyCardActivity.this, "Please make sure the passwords are consistent", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
