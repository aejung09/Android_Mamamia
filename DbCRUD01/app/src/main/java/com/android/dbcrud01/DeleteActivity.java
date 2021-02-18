package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    final static String TAG = "DeleteActivity";

    String code, name, dept,phone;

    EditText ud_code;
    EditText ud_name;
    EditText ud_dept;
    EditText ud_phone;
    String macIP;

    String urlAddr = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();

        macIP = intent.getStringExtra("macIP");

        //입력하는 데이터를 위해 ? 추가
        urlAddr = "http://" + macIP + ":8080/test/studentdelete.jsp?";

        //리스트에서 정보 가져와서 보여주는 부분

        code = intent.getStringExtra("pos");
        name = intent.getStringExtra("name");
        dept = intent.getStringExtra("dept");
        phone = intent.getStringExtra("phone");
        Log.v("헤이~",code);
        ud_code = findViewById(R.id.d_code);
        ud_name = findViewById(R.id.d_name);
        ud_dept = findViewById(R.id.d_dept);
        ud_phone = findViewById(R.id.d_phone);

        ud_code.setText(code);
        ud_name.setText(name);
        ud_dept.setText(dept);
        ud_phone.setText(phone);

        findViewById(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st_code = ud_code.getText().toString();
                urlAddr = urlAddr + "code=" + st_code ;
                connectDeleteData();
                Toast.makeText(DeleteActivity.this, "삭제가 완료되었습니다 ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void connectDeleteData(){
        try {
            NetworkTask insertworkTask = new NetworkTask(DeleteActivity.this,urlAddr,"delete");
            insertworkTask.execute().get();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}