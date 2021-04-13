package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class ChiTietTraCuuActivity extends AppCompatActivity {

    RecyclerView dsthuoc, dstt;
    TraCuuThuocAdapter adapter;
    TraCuuTTAdapter ttAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tra_cuu);
        Intent intent = getIntent();
        String donthuoc = intent.getStringExtra("MADONTHUOC");
        AnhXa();
        dsthuoc.setLayoutManager(new LinearLayoutManager(this));
        dstt.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference tracuuThuocRef = mData.child("users").child("HoatChat").child(donthuoc);
        FirebaseRecyclerOptions<TraCuuThuoc> options =
                new FirebaseRecyclerOptions.Builder<TraCuuThuoc>()
                        .setQuery(tracuuThuocRef, new SnapshotParser<TraCuuThuoc>() {
                            @NonNull
                            @Override
                            public TraCuuThuoc parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new TraCuuThuoc(snapshot.child("name").getValue().toString(),
                                        snapshot.child("mahoatchat").getValue().toString(),
                                        snapshot.child("tenhoatchat").getValue().toString());
                            }
                        })
                        .build();
        adapter = new TraCuuThuocAdapter(options);
        dsthuoc.setAdapter(adapter);

        DatabaseReference tracuuTTRef = mData.child("users").child("TuongTac").child(donthuoc);
        FirebaseRecyclerOptions<TraCuuTuongTac> optionsTT =
                new FirebaseRecyclerOptions.Builder<TraCuuTuongTac>()
                        .setQuery(tracuuTTRef, new SnapshotParser<TraCuuTuongTac>() {
                            @NonNull
                            @Override
                            public TraCuuTuongTac parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new TraCuuTuongTac(snapshot.child("name").getValue().toString(),
                                        snapshot.child("stt").getValue().toString(),
                                        snapshot.child("mahoatchat").getValue().toString(),
                                        snapshot.child("tenhoatchat").getValue().toString(),
                                        snapshot.child("mahoatchattt").getValue().toString(),
                                        snapshot.child("tenhoatchattt").getValue().toString(),
                                        snapshot.child("mucdo").getValue().toString(),
                                        snapshot.child("noidung").getValue().toString());
                            }
                        })
                        .build();
        ttAdapter = new TraCuuTTAdapter(optionsTT);
        dstt.setAdapter(ttAdapter);
    }

    private void AnhXa() {
        dsthuoc = (RecyclerView) findViewById(R.id.tracuu_danhsach_thuoc);
        dstt = (RecyclerView) findViewById(R.id.tracuu_danhsach_tuongtac);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        ttAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        ttAdapter.stopListening();
    }
}