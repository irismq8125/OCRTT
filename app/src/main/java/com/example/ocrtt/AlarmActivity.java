package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import me.pushy.sdk.Pushy;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class AlarmActivity extends AppCompatActivity {

    TextView donthuoc, thoigian, giosang, giotrua, giochieu, giotoi;
    RecyclerView recyclerView;
    AlarmAdapter adapter;
    ArrayList<AlarmChiTiet> listchitiet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pushy.listen(this);
        setContentView(R.layout.activity_alarm);
        AnhXa();

        Intent intent = getIntent();
        String dt = intent.getStringExtra("DONTHUOC");
        String tg = "Từ ngày: " + intent.getStringExtra("NGAYBD") + " đến ngày: " + intent.getStringExtra("NGAYKT");
        String gs = intent.getStringExtra("GIOSANG");
        String gtr = intent.getStringExtra("GIOTRUA");
        String gc = intent.getStringExtra("GIOCHIEU");
        String gt = intent.getStringExtra("GIOTOI");

        donthuoc.setText(dt);
        thoigian.setText(tg);
        giosang.setText(gs);
        giotrua.setText(gtr);
        giochieu.setText(gc);
        giotoi.setText(gt);

        recyclerView .setLayoutManager(new LinearLayoutManager(this));
        listchitiet = new ArrayList<AlarmChiTiet>();

        mData.child("users").child("ChiTietThuoc").child(dt).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    AlarmChiTiet act = data.getValue(AlarmChiTiet.class);
                    listchitiet.add(act);
                }
                adapter = new AlarmAdapter(listchitiet, AlarmActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AlarmActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String kiemtra = intent.getStringExtra("CB_NOIDUNG");
        if(kiemtra != null){
            Intent action = getIntent();
            String madt = action.getStringExtra("DONTHUOC");
            donthuoc.setText(madt);
            thoigian.setText("Từ ngày: " + action.getStringExtra("CB_THOI_GIAN_BD") + " đến ngày: " + action.getStringExtra("CB_THOI_GIAN_KT"));
            giosang.setText(action.getStringExtra("CB_TIME_SANG"));
            giotrua.setText(action.getStringExtra("CB_TIME_TRUA"));
            giochieu.setText(action.getStringExtra("CB_TIME_CHIEU"));
            giotoi.setText(action.getStringExtra("CB_TIME_TOI"));
            listchitiet = new ArrayList<AlarmChiTiet>();
            String buoi = action.getStringExtra("CB_NOIDUNG");
            mData.child("users").child("Thuoc").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot data :snapshot.getChildren()){
                        String text = data.child("name").getValue().toString();
                        if(text.contains(donthuoc.getText().toString().trim())){
                            String nd = data.child(buoi).getValue().toString();
                            String noidung = nd.substring(0,nd.length()-1);
                            AlertDialog.Builder builder = new AlertDialog.Builder(AlarmActivity.this);
                            builder.setMessage(noidung)
                                    .setNegativeButton("Xác nhận", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                           mData.child("users").child("ChiTietThuoc").child(madt).addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                   for(DataSnapshot data : snapshot.getChildren()){
                                                       String tenthuoc = data.child("tenthuoc").getValue().toString();
                                                       if(tenthuoc.contains(noidung)){
                                                           String stt = data.child("stt").getValue().toString();
//                                                           String temp_tenthuoc = tenthuoc.substring(0,tenthuoc.length()-1);
                                                           String mathuoc = data.child("name").getValue().toString();
                                                           String soluong = data.child("soluongth").getValue().toString();
                                                           String conlai = data.child("conlai").getValue().toString();
                                                           String[] arrcl = conlai.split(" ");
                                                           int a = Integer.parseInt(arrcl[0]) -1;
                                                           String temp_conlai = a + " Viên";
                                                           mData.child("users").child("ChiTietThuoc").child(madt).child(noidung).setValue(
                                                                   new AlarmChiTiet(mathuoc,stt,tenthuoc,soluong,temp_conlai)
                                                           );
                                                       }
                                                   }
                                                   Toast.makeText(AlarmActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                                   dialog.dismiss();
                                               }

                                               @Override
                                               public void onCancelled(@NonNull DatabaseError error) {

                                               }
                                           });
                                        }
                                    })
                                    .setPositiveButton("Bỏ qua", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            listchitiet = new ArrayList<AlarmChiTiet>();
            mData.child("users").child("ChiTietThuoc").child(madt).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        AlarmChiTiet act = snapshot.getValue(AlarmChiTiet.class);
                        listchitiet.add(act);
                    }
                    AlarmAdapter adapter1 = new AlarmAdapter(listchitiet, AlarmActivity.this);
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(AlarmActivity.this);
                    recyclerView.setAdapter(adapter1);
                    recyclerView.setLayoutManager(linearLayoutManager1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
//        else {
//
//        }

    }

    private void AnhXa() {
        recyclerView = (RecyclerView) findViewById(R.id.chitiet_nhacnho_danhsach);
        donthuoc = (TextView)findViewById(R.id.chitiet_nhacnho_donthuoc);
        thoigian = (TextView)findViewById(R.id.chitiet_nhacnho_thoigian);
        giosang = (TextView)findViewById(R.id.chitiet_nhacnho_giosang);
        giotrua = (TextView)findViewById(R.id.chitiet_nhacnho_giotrua);
        giochieu = (TextView)findViewById(R.id.chitiet_nhacnho_giochieu);
        giotoi = (TextView)findViewById(R.id.chitiet_nhacnho_giotoi);
    }
}