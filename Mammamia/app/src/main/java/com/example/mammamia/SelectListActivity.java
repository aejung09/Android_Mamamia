package com.example.mammamia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectListActivity extends AppCompatActivity {

    String urlAddr = null;
   TextView addrTag,addrName,addrTel,addrDetail;
    Button backbtn,upbtn;
    String addrNo;
    String tagName;
    String tel;
    String name;
    String detail;
    private RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_list);

        Intent intent = getIntent();
        addrNo =intent.getStringExtra("addrNo");

        name = intent.getStringExtra("addrName");
        tel = intent.getStringExtra("addrTel");
        tagName = intent.getStringExtra("addrTag");
        detail = intent.getStringExtra("addrDetail");


        addrName = findViewById(R.id.addsl_name);
         addrTag = findViewById(R.id.addsl_tagname);
        addrTel = findViewById(R.id.addsl_tel);
        addrDetail = findViewById(R.id.addrlist_Detail);
        upbtn = findViewById(R.id.addsl_upbtn);
        backbtn = findViewById(R.id.addsl_backbtn);

        addrName.setText(name);
        addrDetail.setText(detail);
        addrTel.setText(tel);
        addrTag.setText(tagName);


       upbtn.setOnClickListener(onClickListener);
       backbtn.setOnClickListener(onClickListener1);

    }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SelectListActivity.this, UpdateActivity.class);
                intent1.putExtra("urlAddr",urlAddr);

            }
        };

    View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };







}//------------------------------