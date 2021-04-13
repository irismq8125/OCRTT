package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class TraCuuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TraCuuAdapter adapter;
    LinearLayout khungnhap, khungds;
    EditText edt_search;
    ImageView add;
    ListView ds;
    TextView timkiem;
    String[] listArray = new String[]{};
    String test = "";
    ImageButton back;

    public static DataHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu);
        AnhXa();

        final List<String> ListElementsArrayList = new ArrayList<>(Arrays.asList(listArray));
        final ArrayAdapter<String> adapterList = new ArrayAdapter<>
                (TraCuuActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
        ds.setAdapter(adapterList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference tracuuRef = mData.child("users").child("DaXuLy");
        FirebaseRecyclerOptions<TraCuu> options =
                new FirebaseRecyclerOptions.Builder<TraCuu>()
                        .setQuery(tracuuRef, new SnapshotParser<TraCuu>() {
                            @NonNull
                            @Override
                            public TraCuu parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new TraCuu(snapshot.child("name").getValue().toString(),
                                        snapshot.child("link").getValue().toString(),
                                        snapshot.child("content").getValue().toString(),
                                        snapshot.child("soluong").getValue().toString());
                            }
                        })
                        .build();
        adapter = new TraCuuAdapter(options);
        recyclerView.setAdapter(adapter);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    khungds.setVisibility(View.VISIBLE);
                }
                else {
                    khungds.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElementsArrayList.add(edt_search.getText().toString().trim());
                adapterList.notifyDataSetChanged();
            }
        });

        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String ID = String.valueOf(calendar.getTimeInMillis());
                for(int i=0; i<ListElementsArrayList.size(); i++){
                    test += ListElementsArrayList.get(i) + "_";
                }
                mData.child("users").child("TimTuongTac").child(ID).setValue(test);
                Toast.makeText(TraCuuActivity.this, test, Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TraCuuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void AnhXa() {
        recyclerView = (RecyclerView) findViewById(R.id.tracuu_danhsach);
        khungnhap = (LinearLayout) findViewById(R.id.khung_search_nhap);
        khungds = (LinearLayout) findViewById(R.id.khung_search_danhsach);
        edt_search = (EditText) findViewById(R.id.tracuu_search_edt);
        add = (ImageView)findViewById(R.id.img_add_tracuu);
        ds = (ListView)findViewById(R.id.list_search);
        timkiem = (TextView) findViewById(R.id.timkiem);
        back = (ImageButton) findViewById(R.id.img_back_1);
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