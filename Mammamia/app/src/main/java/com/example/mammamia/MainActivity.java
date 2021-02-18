package com.example.mammamia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    final static String TAG = "SelectAllActivity";
    String urlAddr = null;
    ArrayList<Address> members;
    AddressAdapter adapter = null;
    private RecyclerView recyclerView = null;
    Button addrInsert;

    private RecyclerView.LayoutManager layoutManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_address);


        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        urlAddr = "http://localhost:8080/test/mammamia.jsp";

//        recyclerView.setOnClickListener(onClickListener);


    }

//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            //recyclerview눌렀을때,  viewlist로 가게 함
//            Intent intent = new Intent(MainActivity.this, SelectListActivity.class);
//            intent.putExtra("urlAddr", urlAddr);
//            startActivity(intent);
//
//
//        }
//    };

    @Override
    protected void onResume() {
        super.onResume();


        connectGetData();
        Log.v(TAG, "onResume");
        adapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

//                //전화걸기
//                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+members.get(position).getPhone()));
//
//
//                startActivity(intent1);

                Intent intent = new Intent(MainActivity.this, SelectListActivity.class);


                intent.putExtra("urlAddr", urlAddr);
                intent.putExtra("addrNo", members.get(position).getAddrNo());
                intent.putExtra("addrName", members.get(position).getAddrName());
                intent.putExtra("addrTag", members.get(position).getAddrTag());
                intent.putExtra("addrTel", members.get(position).getAddrTel());
                intent.putExtra("addrDetail", members.get(position).getAddrDetail());
                intent.putExtra("addrAddr", members.get(position).getAddrAddr());
                startActivity(intent);

            }
        });
    }


    private void connectGetData() {
        try {

            NetworkTask networkTask = new NetworkTask(MainActivity.this, urlAddr);
            Object obj = networkTask.execute().get();
            members = (ArrayList<Address>) obj;

            adapter = new AddressAdapter(MainActivity.this, R.layout.address_layout, members);
            recyclerView.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //배경 터치 시 키보드 사라지게
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        InputMethodManager imm;
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}//------------------------------