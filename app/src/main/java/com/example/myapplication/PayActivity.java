package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
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

                            FormBody formBody = new FormBody.Builder().add("type", Type).build();

                            Request request = new Request.Builder()
                                    .url(Constant.BOOK+Screening_id+"/"+Seat_id)
                                    .post(formBody)
                                    .build();
                            Call call = okHttpClient.newCall(request);
                            call.enqueue(new Callback() {

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    Looper.prepare();
                                    Toast.makeText(PayActivity.this, "success?", Toast.LENGTH_SHORT).show();
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
                EditText password = payview2.findViewById(R.id.et_password);
                Button btnpass2 = payview2.findViewById(R.id.btn_pay1);
                btnpass2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //
                    }
                });
                builder6.setTitle("Please enter bank card password").setView(payview2).show();
            }
        });

        Toast.makeText(PayActivity.this,"Screening_id"+Screening_id+"Seat_id"+Seat_id+"Type"+Type , Toast.LENGTH_SHORT).show();

//        Post.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                String Screening_id = ((EditText) findViewById(R.id.username)).getText().toString();
//                String password= ((EditText) findViewById(R.id.password)).getText().toString();
//
//                FormBody formBody = new FormBody.Builder().add("Screening_id", Screening_id).add("password",password).build();
//
//                Request request = new Request.Builder()
//                        .url(Constant.ADD)
//                        .post(formBody)
//                        .build();
//                Call call = okHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                        Looper.prepare();
//                        Toast.makeText(RegisterActivity.this, "Server not responding", Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                        Looper.prepare();
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
//                        if (Boolean.parseBoolean(response.body().string())) {
//                            Toast.makeText(RegisterActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
//                        }
//                        else if (username.length()>20 || username.length()==0 || password.length()>20 || password.length()==0) {
//                            Toast.makeText(RegisterActivity.this, "Length of username or password must be between 1-20", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
//                        }
//                        Looper.loop();
//                    }
//                });
//
//            }
//
//
//        });
    }
}
