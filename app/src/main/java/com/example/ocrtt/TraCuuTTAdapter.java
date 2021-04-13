package com.example.ocrtt;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TraCuuTTAdapter extends FirebaseRecyclerAdapter<TraCuuTuongTac,TraCuuTTAdapter.Holder> {
    public TraCuuTTAdapter(@NonNull FirebaseRecyclerOptions<TraCuuTuongTac> options) {
        super(options);

    }
    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull TraCuuTuongTac model) {
        holder.stt.setText(model.getStt());
        holder.mahc1.setText(model.getMahc1());
        holder.mahc2.setText(model.getMahc2());
        holder.tenhc1.setText(model.getTenhc1());
        holder.tenhc2.setText(model.getTenhc2());
        holder.mucdo.setText(model.getMucdo());
        holder.noidung.setText(model.getNoidung());

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_thongtintuongtac, parent, false);

        return new TraCuuTTAdapter.Holder(v);
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView stt, mahc1, mahc2, tenhc1, tenhc2, mucdo, noidung;
        LinearLayout mau;
        public Holder(@NonNull final View itemView) {
            super(itemView);
            mau = (LinearLayout)itemView.findViewById(R.id.mau_tt);
            stt = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_stt);
            mahc1 = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_mahc1);
            mahc2 = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_mahc2);
            tenhc1 = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_tenhc1);
            tenhc2 = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_tenhc2);
            mucdo = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_mucdo);
            noidung = (TextView)itemView.findViewById(R.id.tracuu_danhsach_tuongtac_noidung);
        }
    }
}
