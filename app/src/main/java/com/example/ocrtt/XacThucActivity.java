package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class XacThucActivity extends AppCompatActivity {

    Button xacthuc;
    EditText code;
    String OTP, CODE;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_thuc);

        auth = FirebaseAuth.getInstance();

        AnhXa();

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().isEmpty())
                    code.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final Intent intent = getIntent();
        OTP = intent.getStringExtra("OTP");

        CODE = code.getText().toString().trim();
        xacthuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (OTP != null){
//                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(OTP, CODE);
//                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Intent intent = new Intent(getApplicationContext(), DanhSachActivity.class);
//                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
//                                    } else {
//                                        Toast.makeText(XacThucActivity.this, "Mã xác thực không đúng", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                }
                Intent intent1 = new Intent(XacThucActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void AnhXa() {
        code = (EditText) findViewById(R.id.edt_code);
        xacthuc = (Button) findViewById(R.id.btn_xacthuc);
    }
}