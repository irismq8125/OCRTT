package com.example.ocrtt.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ocrtt.MainActivity;
import com.example.ocrtt.QuenMatKhauActivity;
import com.example.ocrtt.R;
import com.example.ocrtt.signup.DangKyActivity;

public class DangNhapActivity extends AppCompatActivity {

    Button btndangnhap;
    EditText sdt, mk;
    TextView quenmk, dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        AnhXa();
        sdt.requestFocus();

        sdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                    sdt.setError("Vui lòng nhập số điện thoại!");
                else
                    if(s.charAt(0) != '0')
                        sdt.setError("Đây không phải số điện thoại!");
                    else
                        sdt.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                    mk.setError("Vui lòng nhập mật khẩu!");
                else
                    if(s.length() < 6)
                        mk.setError("Mật khẩu phải lớn hơn 6 ký tự");
                    else
                        mk.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(intent);
            }
        });

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        btndangnhap = (Button) findViewById(R.id.btn_dangnhap);
        dangky = (TextView) findViewById(R.id.btn_dangky);
        sdt = (EditText) findViewById(R.id.edt_dienthoai);
        mk = (EditText) findViewById(R.id.edt_matkhau);
        quenmk = (TextView) findViewById(R.id.btn_quenmatkhau);
    }
}