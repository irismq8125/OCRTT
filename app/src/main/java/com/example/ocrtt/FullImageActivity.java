package com.example.ocrtt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {

    ImageView hinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Intent intent = getIntent();
        String link = intent.getStringExtra("LINK");

        hinh = (ImageView) findViewById(R.id.fullimage_link);
        Glide.with(this).load(link).into(hinh);
    }
}