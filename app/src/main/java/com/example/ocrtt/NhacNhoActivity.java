package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class NhacNhoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NhacNhoAdapter adapter;
    ImageView back, add;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhac_nho);

        AnhXa();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference nhacnhoreference = mData.child("users").child("NhacNho");
        FirebaseRecyclerOptions<NhacNho> options =
                new FirebaseRecyclerOptions.Builder<NhacNho>()
                        .setQuery(nhacnhoreference, new SnapshotParser<NhacNho>() {
                            @NonNull
                            @Override
                            public NhacNho parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new NhacNho(
                                        snapshot.child("donthuoc").getValue().toString(),
                                        snapshot.child("ngaybd").getValue().toString(),
                                        snapshot.child("ngaykt").getValue().toString(),
                                        snapshot.child("gsang").getValue().toString(),
                                        snapshot.child("gtrua").getValue().toString(),
                                        snapshot.child("gchieu").getValue().toString(),
                                        snapshot.child("gtoi").getValue().toString());
                            }
                        })
                        .build();
        adapter = new NhacNhoAdapter(options);
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(),DanhSachActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        recyclerView = (RecyclerView) findViewById(R.id.list_nhacnho);
        back = (ImageView) findViewById(R.id.img_back_nn);
        add = (ImageView) findViewById(R.id.img_add_nn);

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