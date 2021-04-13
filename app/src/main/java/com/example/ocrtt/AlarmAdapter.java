package com.example.ocrtt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.Holder>{
    private List mThuoc;
    private Context mContext;

    public AlarmAdapter(List thuoc, Context context){
        this.mThuoc = thuoc;
        this.mContext = context;
    }

    @NonNull
    @Override
    public AlarmAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_alarm_detail, parent, false);

        return new AlarmAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        AlarmChiTiet act = (AlarmChiTiet) mThuoc.get(position);
        holder.stt.setText(act.getStt());
        holder.thuoc.setText(act.getTenthuoc());
        holder.soluong.setText(act.getSoluongth());
        holder.conlai.setText(act.getConlai());

    }


    @Override
    public int getItemCount() {
        return mThuoc.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public View itemview;
        public TextView stt, thuoc, soluong, conlai;
        LinearLayout mau;
        public Holder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            mau = (LinearLayout) itemView.findViewById(R.id.chitiet_nhacnho_maukhung);
            stt = itemView.findViewById(R.id.chitiet_nhacnho_stt);
            thuoc = itemView.findViewById(R.id.chitiet_nhacnho_tenthuoc);
            soluong = itemView.findViewById(R.id.chitiet_nhacnho_soluong);
            conlai = itemView.findViewById(R.id.chitiet_nhacnho_conlai);
        }
    }
}
