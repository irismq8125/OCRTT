package com.example.ocrtt.signup;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ocrtt.login.DangNhapActivity;
import com.example.ocrtt.R;
import com.example.ocrtt.XacThucActivity;
import com.google.firebase.auth.FirebaseAuth;

public class DangKyActivity extends AppCompatActivity {

    Button tieptuc;
    ImageButton huy;
    EditText sdt;
    private String verificationId;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        auth = FirebaseAuth.getInstance();

        AnhXa();

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });

        tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = sdt.getText().toString().trim();
                final String b = a.substring(1, a.length());
                final AlertDialog.Builder builder = new AlertDialog.Builder(DangKyActivity.this);
                builder.setTitle("(+84)" + b)
                .setMessage("Vui lòng xác nhận số điện thoại này là đúng.")
                .setNegativeButton("Thay đổi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sdt.requestFocus();
                    }
                })
                .setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(DangKyActivity.this, XacThucActivity.class);
                        startActivity(intent);
//                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                "+84" + sdt.getText().toString().trim(),
//                                60,
//                                TimeUnit.SECONDS,
//                                DangKyActivity.this,
//                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                    @Override
//                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                    }
//
//                                    @Override
//                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                    }
//
//                                    @Override
//                                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                        Intent intent = new Intent(getApplicationContext(), XacThucActivity.class);
//                                        intent.putExtra("SDT", sdt.getText().toString());
//                                        intent.putExtra("OTP", verificationId);
//                                        startActivity(intent);
//                                    }
//                                }
//                        );
                    }
                }).show();
            }
        });
    }

    private void AnhXa() {
        tieptuc = (Button) findViewById(R.id.btn_tieptuc);
        huy = (ImageButton) findViewById(R.id.btn_back);
        sdt = (EditText) findViewById(R.id.edt_phone);
    }
}