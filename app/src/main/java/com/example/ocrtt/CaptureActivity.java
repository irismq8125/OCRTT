package com.example.ocrtt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.ocrtt.splash.SplashActivity.mData;

public class CaptureActivity extends AppCompatActivity {

    Button save;
    ImageView img;
    private Uri path;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        Intent intent = getIntent();
        int select = intent.getIntExtra("CAMERA", -1);
        if (select == 11) {
            Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent1, 11);
        } else if (select == 22) {
            Intent intent1 = new Intent();
            intent1.setType("image/*");
            intent1.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent1,"Select Image from here..."),22);
        }

        AnhXa();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String name = calendar.getTimeInMillis() + "";
                final String str = "users/image/" + name;

                if (path != null) {
                    final ProgressDialog progressDialog= new ProgressDialog(CaptureActivity.this);
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference ref = storageReference.child("users").child("image").child(name + ".png");
                    mData.child("users").child("image").child(name).setValue(str);
                    ref.putFile(path)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CaptureActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                    Intent t = new Intent(CaptureActivity.this, DanhSachActivity.class);
                                    startActivity(t);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressDialog.dismiss();
                                    Toast.makeText(CaptureActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                    double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                }
                            });
                }
                else {
                    Toast.makeText(CaptureActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void AnhXa() {
        save = (Button) findViewById(R.id.img_save);
        img = (ImageView) findViewById(R.id.img_donthuoc);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 11:
                if (resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    img.setImageBitmap(bitmap);
                    File file = createImageFile();
                    if (file != null){
                        FileOutputStream fout;
                        try {
                            fout = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fout);
                            fout.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Uri uri=Uri.fromFile(file);
                        path = uri;
                    }
                }
                break;
            case 22:
                if (resultCode == RESULT_OK && data != null) {
                    Uri uri = data.getData();
                    path = uri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        File mFileTemp = null;
        String root = this.getDir("my_sub_dir",Context.MODE_PRIVATE).getAbsolutePath();
        File myDir = new File(root + "/Img");
        if(!myDir.exists()){
            myDir.mkdirs();
        }
        try {
            mFileTemp=File.createTempFile(imageFileName,".png",myDir.getAbsoluteFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return mFileTemp;
    }

}