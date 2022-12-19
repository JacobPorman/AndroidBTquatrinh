package com.example.btquatrinh_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnShowLinear, btnShowRalative, btnShowTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLinear = (Button) findViewById(R.id.btnLinear);
        btnShowRalative = (Button) findViewById(R.id.btnRelative);
        btnShowTable = (Button) findViewById(R.id.btnTable);

        btnShowLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LinearActivity.class);
                startActivity(intent);
            }
        });

        btnShowRalative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RelativeActivity.class);
                startActivity(intent);
            }
        });

        btnShowTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TableActivity.class);
                startActivity(intent);
            }
        });
    }
}