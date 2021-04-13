package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class DanhSachActivity extends AppCompatActivity {

    ImageButton add;
    RecyclerView recyclerView;
    DonThuocAdapter adapter;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach);

        recyclerView = (RecyclerView) findViewById(R.id.donthuoc_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference donthucreference = mData.child("users").child("DaXuLy");
        FirebaseRecyclerOptions<DonThuoc> options =
                new FirebaseRecyclerOptions.Builder<DonThuoc>()
                        .setQuery(donthucreference, new SnapshotParser<DonThuoc>() {
                            @NonNull
                            @Override
                            public DonThuoc parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new DonThuoc(snapshot.child("name").getValue().toString(),
                                        snapshot.child("link").getValue().toString(),
                                        snapshot.child("content").getValue().toString(),
                                        snapshot.child("soluong").getValue().toString());
                            }
                        })
                        .build();
        adapter = new DonThuocAdapter(options);
        recyclerView.setAdapter(adapter);

        add = (ImageButton) findViewById(R.id.add_donthuoc);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        back = (ImageButton)findViewById(R.id.img_back_ds);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDialogAdd(){
        Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog_add_donthuoc);

        final TextView txtcamera = (TextView) d.findViewById(R.id.dialog_txt_camera);
        final TextView txtgallery = (TextView) d.findViewById(R.id.dialog_txt_gallery);

        txtcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCamera(1);
            }
        });

        txtgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoCamera(2);
            }
        });
        d.show();
    }

    private void gotoCamera(int sc){
        Intent intent = new Intent(DanhSachActivity.this, CaptureActivity.class);
        if(sc == 1){
            intent.putExtra("CAMERA",11);
        } else {
            intent.putExtra("CAMERA",22);
        }
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}