package com.example.ocrtt;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChiTietAdapter extends RecyclerView.Adapter<ChiTietAdapter.Holder> {

    private List mThuoc;
    private Context mContext;

    public ChiTietAdapter(List thuoc, Context context){
        this.mThuoc = thuoc;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chitietnoidung, parent, false);

        return new ChiTietAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ChiTiet ct = (ChiTiet) mThuoc.get(position);
        holder.stt.setText(ct.getStt());
        holder.thuoc.setText(ct.getThuoc());
        holder.thoigian.setText(ct.getThoigian());
        holder.cachdung.setText(ct.getCachdung());
        holder.soluong.setText(ct.getSoluong());
        int value = Integer.parseInt(ct.getStt());
        if(value % 2==0){
            holder.mau.setBackgroundColor(Color.parseColor("#438AF4"));
            holder.stt.setTextColor(Color.parseColor("#FFFFFF"));
            holder.thuoc.setTextColor(Color.parseColor("#FFFFFF"));
            holder.thoigian.setTextColor(Color.parseColor("#FFFFFF"));
            holder.cachdung.setTextColor(Color.parseColor("#FFFFFF"));
            holder.soluong.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else {
            holder.mau.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return mThuoc.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public View itemview;
        public TextView stt, thuoc, thoigian, cachdung, soluong;
        LinearLayout mau;
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            mau = (LinearLayout) itemView.findViewById(R.id.chitiet_khung);
            stt = itemView.findViewById(R.id.chitiet_stt);
            thuoc = itemView.findViewById(R.id.chitiet_tenthuoc);
            thoigian = itemView.findViewById(R.id.chitiet_thoigian);
            cachdung = itemView.findViewById(R.id.chitiet_cachdung);
            soluong = itemView.findViewById(R.id.chitiet_soluong);
        }
    }
}
