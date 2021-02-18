package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    final static String TAG = "UpdateActivity";

    String code, name, dept,phone;

    EditText ud_code;
    EditText ud_name;
    EditText ud_dept;
    EditText ud_phone;
    String macIP;
    String pos;

    String urlAddr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        pos = intent.getStringExtra("pos");


        macIP = intent.getStringExtra("macIP");

        //입력하는 데이터를 위해 ? 추가
        urlAddr = "http://" + macIP + ":8080/test/update.jsp?";

        //리스트에서 정보 가져와서 보여주는 부분


        name = intent.getStringExtra("name");
        dept = intent.getStringExtra("dept");
        phone = intent.getStringExtra("phone");

        ud_code = findViewById(R.id.ud_code);
        ud_name = findViewById(R.id.ud_name);
        ud_dept = findViewById(R.id.ud_dept);
        ud_phone = findViewById(R.id.ud_phone);


        ud_code.setText(pos);
        ud_name.setText(name);
        ud_dept.setText(dept);
        ud_phone.setText(phone);


        findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st_code = ud_code.getText().toString();
                String st_name = ud_name.getText().toString();
                String st_dept = ud_dept.getText().toString();
                String st_phone = ud_phone.getText().toString();

                urlAddr = urlAddr+ "code=" + st_code + "&name=" + st_name + "&dept=" + st_dept + "&phone=" + st_phone;
                connectUpdateData();
                Toast.makeText(UpdateActivity.this, st_code + "를 변경하였습니다 ", Toast.LENGTH_SHORT).show();
            }
        });

    }
        private void connectUpdateData() {
            try {
                NetworkTask networkTask = new NetworkTask(UpdateActivity.this,urlAddr,"update");
                networkTask.execute().get();
            }catch (Exception e){
                e.printStackTrace();
            }

        }





}