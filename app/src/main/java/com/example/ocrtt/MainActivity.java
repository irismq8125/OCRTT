package com.example.ocrtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout danhsach, nhacnho, tracuu, taikhoan, hethong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        danhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSachActivity.class);
                startActivity(intent);
            }
        });

        nhacnho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhacNhoActivity.class);
                startActivity(intent);
            }
        });

        tracuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TraCuuActivity.class);
                startActivity(intent);
            }
        });

        taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaiKhoanActivity.class);
                startActivity(intent);
            }
        });



    }

    private void AnhXa() {
        danhsach = (LinearLayout) findViewById(R.id.layout_danhsach);
        nhacnho = (LinearLayout) findViewById(R.id.nhacnho);
        tracuu = (LinearLayout) findViewById(R.id.tracuu);
        taikhoan = (LinearLayout) findViewById(R.id.taikhoan);
        hethong = (LinearLayout) findViewById(R.id.hethong);
    }
}