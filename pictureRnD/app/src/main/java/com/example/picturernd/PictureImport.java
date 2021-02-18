package com.example.picturernd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PictureImport extends AppCompatActivity {

    final static String TAG = "임포트 하기";
    Button button;
    EditText idInput;
    String macIp = null;
    String urlAddr = null;
    String pictureUrl = null;
    ArrayList<UserInfoDto> userInfoDtos;
    ImageView mImageView = null;
    private String img_path = new String();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_import);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE); //사용자에게 사진 사용 권한 받기 (가장중요함)


        button = findViewById(R.id.importBtn);
        idInput = findViewById(R.id.inputId);
        mImageView = findViewById(R.id.iv_picture);


        button.setOnClickListener(mOnclickListener);

    }

    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.importBtn:
                    String importId = idInput.getText().toString().trim();

                    connectingPicture(importId);
                    break;


            }
        }
    };


    private void connectingPicture(String importId) {
        Log.v(TAG, "connectGetData()");
        try {

            macIp = "192.168.35.147";
            urlAddr = "http:/" + macIp + ":8080/test/pictureSelect.jsp?"; //localhost나  127.0.0.1을 넣을경우 LOOP가 생길 수 있으므로 할당된 IP 주소를 사용할것
            urlAddr = urlAddr + "idUrl=" + importId;//jsp에 ID값 Request할 수 있게 페이지 설정.
            Log.v(TAG, urlAddr);
            NetworkTask networkTask = new NetworkTask(PictureImport.this, urlAddr);
            Object obj = networkTask.execute().get(); //obj를 받아들여서
            userInfoDtos = (ArrayList<UserInfoDto>) obj; //userInfoDtos 다시 풀기

            pictureUrl = userInfoDtos.get(0).getUrlPicture();//dto에서 0번째로 낚아 채기 (어짜피 한개 밖에 없음.


            mImageView.setImageBitmap(BitmapFactory.decodeFile(pictureUrl));//가져온 경로를 imageView에 올리기
//            pictureUrl = "file:/" + pictureUrl;
            Log.v(TAG, pictureUrl);
//            Picasso.get()
//                    .load(Uri.parse(pictureUrl))
//                    .into(mImageView);


//            Log.d(TAG,userIdCheck);
//            Log.d(TAG,userPwCheck);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}