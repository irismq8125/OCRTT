package com.example.ocrtt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import static com.example.ocrtt.splash.SplashActivity.mData;

public class NhacNhoAdapter extends FirebaseRecyclerAdapter<NhacNho,NhacNhoAdapter.Holder> {

    public NhacNhoAdapter(@NonNull FirebaseRecyclerOptions<NhacNho> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NhacNhoAdapter.Holder holder, int position, @NonNull final NhacNho model) {
        holder.trangthai.setText("Từ " + model.getNgaybd() + " đến " + model.getNgaykt());
        holder.donthuoc.setText(model.getDonthuoc());

        holder.gs.setText(model.getGsang());
        holder.gtr.setText(model.getGtrua());
        holder.gc.setText(model.getGchieu());
        holder.gt.setText(model.getGtoi());
        holder.onoff.setChecked(true);

        holder.huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Bạn muốn xóa nhắc nhở?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mData.child("users").child("NhacNho").child(model.getDonthuoc()).removeValue();
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, AlarmActivity.class);
                intent.putExtra("DONTHUOC",model.getDonthuoc());
                intent.putExtra("GIOSANG",model.getGsang());
                intent.putExtra("GIOTRUA",model.getGtrua());
                intent.putExtra("GIOCHIEU",model.getGchieu());
                intent.putExtra("GIOTOI",model.getGtoi());
                intent.putExtra("NGAYBD",model.getNgaybd());
                intent.putExtra("NGAYKT",model.getNgaykt());
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public NhacNhoAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_nhacnho, parent, false);
        return new NhacNhoAdapter.Holder(v);
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView trangthai, donthuoc, gs, gtr,gc, gt;
        ImageView huy;
        Switch onoff;
        public Holder(@NonNull View itemView) {
            super(itemView);
            trangthai = itemView.findViewById(R.id.trangthai_nhacnho);
            donthuoc = itemView.findViewById(R.id.toathuoc_nhacnho);
            gs = itemView.findViewById(R.id.nhacnho_giosang);
            gtr = itemView.findViewById(R.id.nhacnho_giotrua);
            gc = itemView.findViewById(R.id.nhacnho_giochieu);
            gt = itemView.findViewById(R.id.nhacnho_giotoi);
            onoff = itemView.findViewById(R.id.onoff_nhacnho);
            huy = itemView.findViewById(R.id.huy_nhacnho);
        }
    }
}
