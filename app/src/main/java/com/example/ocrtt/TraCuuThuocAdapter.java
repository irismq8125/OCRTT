package com.example.ocrtt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TraCuuThuocAdapter extends FirebaseRecyclerAdapter<TraCuuThuoc,TraCuuThuocAdapter.Holder> {
    public TraCuuThuocAdapter(@NonNull FirebaseRecyclerOptions<TraCuuThuoc> options) {
        super(options);

    }
    @Override
    protected void onBindViewHolder(@NonNull TraCuuThuocAdapter.Holder holder, int position, @NonNull TraCuuThuoc model) {
        holder.stt.setText("");
        holder.mahc.setText(model.getMa());
        holder.tenhc.setText(model.getTen());
    }

    @NonNull
    @Override
    public TraCuuThuocAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_thongtinthuoc, parent, false);

        return new TraCuuThuocAdapter.Holder(v);
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView stt, mahc, tenhc;
        String nd;
        public Holder(@NonNull final View itemView) {
            super(itemView);
            stt = (TextView)itemView.findViewById(R.id.tracuu_danhsach_thuoc_stt);
            mahc = (TextView)itemView.findViewById(R.id.tracuu_danhsach_thuoc_mahc);
            tenhc = (TextView)itemView.findViewById(R.id.tracuu_danhsach_thuoc_tenhc);
        }
    }
}
