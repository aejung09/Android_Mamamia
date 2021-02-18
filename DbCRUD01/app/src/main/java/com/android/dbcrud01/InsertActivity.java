package com.android.dbcrud01;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    final static String TAG = "InsertActivity.java";
    String urlAddr = null;
    String scode, sname, sdept, sphone, macIP;
    EditText Ecode, Ename, Edept, Ephone;
    Button btnInsert ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        Intent intent = getIntent();
        macIP = intent.getStringExtra("macIP");

        //입력하는 데이터를 위해 ? 추가
        urlAddr = "http://"+macIP+":8080/test/studentInsert.jsp?";

        Ecode = findViewById(R.id.insert_code);
        Ename = findViewById(R.id.insert_name);
        Edept = findViewById(R.id.insert_dept);
        Ephone = findViewById(R.id.insert_phone);


        // 입력시 자릿수 제한
        Ecode.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        Ename.setFilters(new InputFilter[] {new InputFilter.LengthFilter(10)});
        Edept.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});
        Ephone.setFilters(new InputFilter[] {new InputFilter.LengthFilter(12)});

        btnInsert = findViewById(R.id.insert_btn);
        btnInsert.setOnClickListener(onClickListener);





    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            scode = Ecode.getText().toString();
            sname = Ename.getText().toString();
            sdept = Edept.getText().toString();
            sphone = Ephone.getText().toString();


            urlAddr = urlAddr + "code="+scode+"&name="+sname+"&dept="+sdept+"&phone="+sphone;
            connectInsertData();
            Toast.makeText(InsertActivity.this, scode+"가 입력 되었습니다", Toast.LENGTH_SHORT).show();

        }
    };




    private void connectInsertData() {
        try {
            NetworkTask networkTask = new NetworkTask(InsertActivity.this,urlAddr,"insert");
            networkTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}//————————