package com.example.mammamia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder> {

    Context mContext = null;
    int layout = 0;
    LayoutInflater inflater = null;
    private ArrayList<Address> mDataset;

    public AddressAdapter(Context mContext, int layout, ArrayList<Address> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.mDataset = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.address_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.addrTag.setText(mDataset.get(position).getAddrTag()); //position = 인덱스값
        holder.addrName.setText(mDataset.get(position).getAddrName()); //position = 인덱스값
        holder.addrTel.setText(mDataset.get(position).getAddrTel()); //position = 인덱스값




    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = inflater.inflate(this.layout, parent, false);
//        }
//        TextView tv_code = convertView.findViewById(R.id.tv_code);
//        TextView tv_name = convertView.findViewById(R.id.tv_name);
//        TextView tv_dept = convertView.findViewById(R.id.tv_dept);
//        TextView tv_phone = convertView.findViewById(R.id.tv_phone);
//
//        tv_code.setText("학번 : " + data.get(position).getCode());
//        tv_name.setText("성명 : " + data.get(position).getName());
//        tv_dept.setText("전공 : " + data.get(position).getDept());
//        tv_phone.setText("전화번호 : " + data.get(position).getPhone());
//
//        if ((position % 2) == 1) {
//            convertView.setBackgroundColor(0x50000000);
//        } else {
//            convertView.setBackgroundColor(0x50dddddd);
//        }
//
//        return convertView;
//    }

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
        public TextView addrTag;
        public TextView addrName;
        public TextView addrTel;
        public ImageView addrTagImg;


        MyViewHolder(View v) {
            super(v);
            addrTag = v.findViewById(R.id.addrlist_tag);
            addrName = v.findViewById(R.id.addrlist_name);
            addrTel = v.findViewById(R.id.addrlist_tel);
            addrTagImg = v.findViewById(R.id.addrlist_tagimg);


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

