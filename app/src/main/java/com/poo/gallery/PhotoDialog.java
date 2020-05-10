package com.poo.gallery;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PhotoDialog extends Dialog implements View.OnClickListener {
    private String mPath;
    public PhotoDialog(@NonNull Context context, String path) {
        super(context, R.style.DiaLog_photo);
        setContentView(R.layout.view_photo);
        initView();
        showImage(path);

    }

    private void showImage(String path) {
        Glide.with(getContext())
                .load(path)
                .into((ImageView) findViewById(R.id.iv_photo_dialog));
    }


    private void initView() {
        findViewById(R.id.bt_back).setOnClickListener(this);
        findViewById(R.id.iv_share).setOnClickListener(this);
        findViewById(R.id.tv_share).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_back) {
            dismiss();
        } else if (v.getId() == R.id.iv_share || v.getId() == R.id.tv_share) {

        }
    }

    private diaLogListener listener;

    public void setDialogListener(diaLogListener event) {
        listener = event;
    }

    public interface diaLogListener {
        void clickDialog(String data);
    }
}
