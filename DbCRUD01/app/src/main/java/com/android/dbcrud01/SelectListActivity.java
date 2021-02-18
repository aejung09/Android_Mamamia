package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SelectListActivity extends AppCompatActivity {


    String urlAddr = null;

    String name, dept, phone;
    Button button;
    String macIP;
    String pos;
    TextView code, name1, dept1, phone1;
    private RecyclerView recyclerView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        Intent intent = getIntent();
        pos = intent.getStringExtra("pos");

        name = intent.getStringExtra("name");
        dept = intent.getStringExtra("dept");
        phone = intent.getStringExtra("phone");


        code = findViewById(R.id.lv_code);
        name1 = findViewById(R.id.lv_name);
        dept1 = findViewById(R.id.lv_dept);
        phone1 = findViewById(R.id.lv_phone);
        button = findViewById(R.id.lv_backbtn);


        code.setText(pos);
        name1.setText(name);
        dept1.setText(dept);
        phone1.setText(phone);


        findViewById(R.id.lv_backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();

            }
        });


    }

}//---------------------------------