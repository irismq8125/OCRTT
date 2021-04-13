package com.example.ocrtt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class ChiTietToaThuocActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ChiTietAdapter adapter;
    ArrayList<ChiTiet> listchitiet;
    Button thietlap;
    ImageView hinhanh;
    ImageButton xoa, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_toa_thuoc);
        recyclerView = (RecyclerView) findViewById(R.id.noidung_toathuoc);
        listchitiet = new ArrayList<ChiTiet>();
        Intent intent = getIntent();
        String text = intent.getStringExtra("NOIDUNG");
        String[] a0 = text.split("_");
        String[] a1 = a0[0].split("-");
        String[] a2 = a0[1].split("-");
        String sl = intent.getStringExtra("SOLUONG");
        String[] a3 = sl.split("_");
        int dai = a1.length;
        String cu = " ", tg = "";
        for(int j=0; j<dai; j++){
            String rr = a2[j];
            if(rr.contains("Uống"))
            {
                tg = rr.substring(0,rr.indexOf("Uống"));
                cu = rr.substring(rr.indexOf("Uống"),rr.length());
            }
            listchitiet.add(new ChiTiet(j+1 + "",a1[j] + "", tg,cu,a3[j]));
        }

        hinhanh = (ImageView) findViewById(R.id.chitiet_hinhanh);
        String link = intent.getStringExtra("LINK");
        Glide.with(this).load(link).into(hinhanh);

        hinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietToaThuocActivity.this,FullImageActivity.class);
                intent1.putExtra("LINK",link);
                startActivity(intent1);
            }
        });

        adapter = new ChiTietAdapter(listchitiet, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        final String donthuoc = intent.getStringExtra("DONTHUOC");
        thietlap = (Button) findViewById(R.id.chitiet_thietlap);
        thietlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietToaThuocActivity.this, ChiTietNhacNhoActivity.class);
                intent1.putExtra("MATHUOC",donthuoc);
                startActivity(intent1);
            }
        });

        xoa = (ImageButton)findViewById(R.id.donthuoc_xoa);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietToaThuocActivity.this)
                        .setIcon(R.drawable.notification)
                        .setTitle("Thông báo!")
                        .setMessage("Bạn muốn xóa đơn thuốc này?")
                        .setCancelable(true)
                        .setNegativeButton("Xóa bỏ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mData.child("users").child("DaXuLy").child(donthuoc).removeValue();
                                mData.child("users").child("ChiTietThuoc").child(donthuoc).removeValue();
                                mData.child("users").child("HoatChat").child(donthuoc).removeValue();
                                mData.child("users").child("NhacNho").child(donthuoc).removeValue();
                                mData.child("users").child("Thuoc").child(donthuoc).removeValue();
                                mData.child("users").child("TuongTac").child(donthuoc).removeValue();

                                Toast.makeText(ChiTietToaThuocActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(ChiTietToaThuocActivity.this,DanhSachActivity.class);
                                startActivity(intent1);
                            }
                        })
                        .setPositiveButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });

        back = (ImageButton) findViewById(R.id.img_back_chitietdonthuoc);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietToaThuocActivity.this, DanhSachActivity.class);
                startActivity(intent1);
            }
        });

    }
}