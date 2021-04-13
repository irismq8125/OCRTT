package com.example.ocrtt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class TaiKhoanActivity extends AppCompatActivity {

    TextView dangnhap, ngaysinh, gioitinh;
    EditText hienthi, email, diachi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);

        AnhXa();

        ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(v.getContext());
                d.setContentView(R.layout.ngaysinh);

                final DatePicker date = (DatePicker) d.findViewById(R.id.ngaysinh);

                Button xacnhan = (Button) d.findViewById(R.id.ngaysinh_xacnhan);
                xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int ngay = date.getDayOfMonth();
                        int thang = date.getMonth();
                        int nam = date.getYear();
                        ngaysinh.setText(ngay + "/"  + thang + "/" + nam);
                        d.dismiss();
                    }
                });
                d.show();

                Button huybo = (Button) d.findViewById(R.id.ngaysinh_huybo);
                huybo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
            }
        });

        gioitinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d = new Dialog(v.getContext());
                d.setContentView(R.layout.gioitinh);

                Button xacnhan = (Button) d.findViewById(R.id.gioitinh_xacnhan);
                xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RadioButton nam = (RadioButton) d.findViewById(R.id.nam);
                        RadioButton nu = (RadioButton) d.findViewById(R.id.nu);
                        if(nam.isChecked())
                            gioitinh.setText("Nam");
                        else if (nu.isChecked())
                            gioitinh.setText("Nữ");
                        else
                            gioitinh.setText("Khác");
                        d.dismiss();
                    }
                });
                d.show();

                Button huybo = (Button) d.findViewById(R.id.gioitinh_huybo);
                huybo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
            }
        });

    }

    private void AnhXa() {
        dangnhap = (TextView) findViewById(R.id.thongtin_dangnhap);
        hienthi = (EditText) findViewById(R.id.thongtin_tenhienthi);
        email = (EditText) findViewById(R.id.thongtin_email);
        diachi = (EditText) findViewById(R.id.thongtin_diachi);
        ngaysinh = (TextView) findViewById(R.id.thongtin_ngaysinh);
        gioitinh = (TextView) findViewById(R.id.thongtin_gioitinh);
    }
}