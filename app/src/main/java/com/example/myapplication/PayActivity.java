package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay2);

        Bundle bundle = getIntent().getExtras();
        int Screening_id = bundle.getInt("Screening_id");
        int Seat_id = bundle.getInt("Seat_id");
        int Type = bundle.getInt("Type");
        Toast.makeText(PayActivity.this,"Screening_id"+Screening_id+"Seat_id"+Seat_id+"Type"+Type , Toast.LENGTH_SHORT).show();
    }
}
