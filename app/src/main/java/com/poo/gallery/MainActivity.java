package com.poo.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PhotoAdapter.onPhotoListener {

    private static final int REQUEST_STORAGE = 11;
    private ArrayList<String> mListPath;
    private String coloum = "_data";
    private String[] projection = {coloum};


    private RecyclerView rvItem;
    private PhotoAdapter photoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListPath = new ArrayList<>();
        initView();


    }


    private void initView() {

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        }else
        try (Cursor cursor = getApplicationContext().
                getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    final int index = cursor.getColumnIndexOrThrow(coloum);
                    String path = cursor.getString(index);
                    if (path != null && !path.isEmpty()) {
                        mListPath.add(path);
                    }
                    cursor.moveToNext();
                }
            }
        }
        rvItem = findViewById(R.id.rv_photo);
        rvItem.setLayoutManager(new GridLayoutManager(this, 3));
       initData();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_STORAGE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }

    private void initData() {
        photoAdapter = new PhotoAdapter(mListPath, this);
        photoAdapter.setOnPhotoListener(this);
        rvItem.setAdapter(photoAdapter);
    }



    public void clickPhoto(String path) {
        PhotoDialog dialog = new PhotoDialog(this,path);
        dialog.show();
    }


}
