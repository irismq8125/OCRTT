package com.example.ocrtt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class TraCuuAdapter extends FirebaseRecyclerAdapter<TraCuu,TraCuuAdapter.Holder> {

    public TraCuuAdapter(@NonNull FirebaseRecyclerOptions<TraCuu> options) {
        super(options);

    }
    @Override
    protected void onBindViewHolder(@NonNull TraCuuAdapter.Holder holder, int position, @NonNull TraCuu model) {
        holder.madon.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model.getLink()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ChiTietTraCuuActivity.class);
                intent.putExtra("MADONTHUOC",model.getName());
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.donthuoc, parent, false);

        return new TraCuuAdapter.Holder(v);
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img, zoom;
        TextView soluong, madon;
        String nd;
        public Holder(@NonNull final View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.donthuoc_hinh);
            madon = (TextView)itemView.findViewById(R.id.donthuoc_ma);
            soluong = (TextView)itemView.findViewById(R.id.donthuoc_soluong);
        }
    }
}
