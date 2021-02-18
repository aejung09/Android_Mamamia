package com.android.dbcrud01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtIP;
    Button insertBtn = null;
    Button updateBtn = null;
    Button deleteBtn= null;
    Button selectAllBtn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //홍깅동 Area

        addListener();

    }

    private void addListener(){
        edtIP = findViewById(R.id.edt_ip);
        insertBtn = findViewById(R.id.btn_insert);
        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);
        selectAllBtn = findViewById(R.id.btn_selectA);

        insertBtn.setOnClickListener(onClickListener);
        updateBtn.setOnClickListener(onClickListener);
        deleteBtn.setOnClickListener(onClickListener);
        selectAllBtn.setOnClickListener(onClickListener);

    }//addListener

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tempIp = edtIP.getText().toString();
            Intent intent = null;

            switch (v.getId()){
                case R.id.btn_insert:
                    intent = new Intent(MainActivity.this, InsertActivity.class);
                    intent.putExtra("macIP", tempIp);
                    startActivity(intent);
                    break;
                case R.id.btn_update:
                    Toast.makeText(MainActivity.this,"검색 후 선택을 짧게 하시면 수정화면으로이동합니다" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_delete:
                    Toast.makeText(MainActivity.this,"검색 후 선택을 길게 하시면 삭제화면으로이동합니다" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_selectA:
                    intent = new Intent(MainActivity.this, SelectAllActivity.class);
                    intent.putExtra("macIP",tempIp);
                    startActivity(intent);
                    break;
            }
        }
    }; //onClickListener


}//===끝===