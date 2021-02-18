package com.android.dbcrud01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    Context mContext = null;
    int layout = 0;
    LayoutInflater inflater = null;
    private ArrayList<Student> mDataset;

    public StudentAdapter(Context mContext, int layout, ArrayList<Student> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.mDataset = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    @Override
//    public int getCount() {
//        return data.size();
//    }
//

//    public Object getItem(int position) {
//        return mDataset.get(position).getCode(); //프라이머리 키
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvcode.setText(mDataset.get(position).getCode()); //position = 인덱스값
        holder.tvname.setText(mDataset.get(position).getName()); //position = 인덱스값
        holder.tvdept.setText(mDataset.get(position).getDept()); //position = 인덱스값
        holder.tvphone.setText(mDataset.get(position).getPhone()); //position = 인덱스값



    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //인터페이스 선언
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null;

    //메인에서 사용할 클릭메서드 선언
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
    //-----------------Click Event---------------------
    //-----------------Click Event---------------------


    //-----------------LongClick Event---------------------
    //-----------------LongClick Event---------------------
    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    private OnItemLongClickListener mLongListener = null;

    public void setOnItemLongClickListener(OnItemLongClickListener longListener) {
        this.mLongListener = longListener;
    }

    //-----------------LongClick Event---------------------
    //-----------------LongClick Event---------------------




    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvcode;
        public TextView tvname;
        public TextView tvdept;
        public TextView tvphone;


        MyViewHolder(View v) {
            super(v);
            tvcode = v.findViewById(R.id.tv_code);
            tvname = v.findViewById(R.id.tv_name);
            tvdept = v.findViewById(R.id.tv_dept);
            tvphone = v.findViewById(R.id.tv_phone);


            // 뷰홀더에서만 리스트 포지션값을 불러올 수 있음.


            //-----------------Click Event---------------------
            //-----------------Click Event---------------------
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();//어뎁터 포지션값
                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
                    if (position != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(view, position);
                        }
                    }
                }
            });
            //-----------------Click Event---------------------
            //-----------------Click Event---------------------


            //-----------------LongClick Event---------------------
            //-----------------LongClick Event---------------------
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();//어뎁터 포지션값
                    // 뷰홀더에서 사라지면 NO_POSITION 을 리턴
                    if (position != RecyclerView.NO_POSITION) {
                        if (mLongListener != null) {
                            mLongListener.onItemLongClick(view, position);
                        }
                    }


                    return true;
                }
            });

            //-----------------LongClick Event---------------------
            //-----------------LongClick Event---------------------


        }
    }


}//-------------------------------

