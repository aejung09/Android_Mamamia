package com.android.dbcrud01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectAllActivity extends AppCompatActivity {

    final static String TAG = "SelectAllActivity";
    String urlAddr = null;
    ArrayList<Student> members;
    StudentAdapter adapter = null;
    private RecyclerView recyclerView = null;
    Button addrInsert;
    String urlDelete = null;
    String code1 = null;
    String urlUpdate = null;
    ImageButton btnSearch;

    private RecyclerView.LayoutManager layoutManager = null;
    String macIp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectall);

        recyclerView = findViewById(R.id.lv_student);


        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        macIp = intent.getStringExtra("macIP");

        urlAddr = "http://" + macIp + ":8080/test/student_query_all.jsp";

        recyclerView.setOnClickListener(onClickListener);


        //검색 인텐트로 이동하기 위한 버튼 선언언
       btnSearch = findViewById(R.id.search_btn);
        btnSearch.setOnClickListener(searchClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            Intent intent2 = new Intent(SelectAllActivity.this, InsertActivity.class);
            intent2.putExtra("macIP", macIp);

            startActivity(intent2);


        }
    };

    //돋보기 버튼 클릭 - 검색 인텐트로 이동
    View.OnClickListener searchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
            intent.putExtra("macIP", macIp);
            startActivity(intent);
        }
    };


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mi = getMenuInflater();
        if (v == recyclerView) {
            menu.setHeaderTitle("선택");
            mi.inflate(R.menu.menu1, menu);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        connectGetData();
        Log.v(TAG, "onResume");
        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

//                //전화걸기
//                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+members.get(position).getPhone()));
//
//
//                startActivity(intent1);

//                    Toast.makeText(SelectAllActivity.this,members.get(position).getCode(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectAllActivity.this, SelectListActivity.class);


                intent.putExtra("macIP", macIp);
                intent.putExtra("pos", members.get(position).getCode());
                intent.putExtra("name", members.get(position).getName());
                intent.putExtra("dept", members.get(position).getDept());
                intent.putExtra("phone", members.get(position).getPhone());
                startActivity(intent);
                connectGetData();

            }
        });

//        adapter.setOnItemLongClickListener(new StudentAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View v, int position) {
//                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+members.get(position).getPhone()));
//
//
//                startActivity(intent1);
//            }
//        });


        MySwipeHelper swipeHelper = new MySwipeHelper(SelectAllActivity.this, recyclerView, 300) {


            @Override
            public void instantiatrMyButton(RecyclerView.ViewHolder viewHolder, List<MySwipeHelper.MyButton> buffer) {
                buffer.add(new MyButton(SelectAllActivity.this,
                        "Delete",
                        30,
                        R.drawable.dele,
                        Color.parseColor("#FF3C30"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {

                                new AlertDialog.Builder(SelectAllActivity.this) // 저장 후 입력 완료 되었다는 Alert 창, 확인 클릭 시 리스트 창으로 이동
                                        .setTitle("")
                                        .setMessage("삭제 하시겠습니까?")
                                        .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) { // 리스트 창으로 이동해주는 메소드
                                                delete();
                                                connectGetData();
                                            }
                                        })
                                        .setNegativeButton("취소", null)
                                        .show();


                                code1 = members.get(pos).getCode();
                                urlDelete = "http://" + macIp + ":8080/test/studentdelete.jsp?code=" + code1;


                            }
                        }));
                buffer.add(new MyButton(SelectAllActivity.this,
                        "Update",
                        30,
                        R.drawable.upda,
                        Color.parseColor("#03DAC5"),
                        new MyButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                Intent intent1 = new Intent(SelectAllActivity.this, UpdateActivity.class);
                                intent1.putExtra("macIP", macIp);
                                intent1.putExtra("pos", members.get(pos).getCode());
                                intent1.putExtra("name", members.get(pos).getName());
                                intent1.putExtra("dept", members.get(pos).getDept());
                                intent1.putExtra("phone", members.get(pos).getPhone());
                                startActivity(intent1);
                                connectGetData();


                            }
                        }));
            }
        };


    }


    public void click_btn(View view) {

        recyclerView.setAdapter(adapter);
    }

    private void connectGetData() {
        try {

            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlAddr,"select");
            Object obj = networkTask.execute().get();
            members = (ArrayList<Student>) obj;

            adapter = new StudentAdapter(SelectAllActivity.this, R.layout.student_layout, members);
            recyclerView.setAdapter(adapter);
            Log.v(TAG, "ada "+adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void delete() {

        try {
            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlDelete,"delete");
            networkTask.execute();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void update() {
        try {
            NetworkTask networkTask = new NetworkTask(SelectAllActivity.this, urlUpdate,"update");
            networkTask.execute();

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

}//=======