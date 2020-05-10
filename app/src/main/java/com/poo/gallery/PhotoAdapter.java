package com.poo.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private ArrayList<String> listPhoto;
    private Context context;

    public PhotoAdapter(ArrayList<String> listPhoto, Context context) {
        this.listPhoto = listPhoto;
        this.context = context;
    }

    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_photo, null);
        return new PhotoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        Glide.with(context).load(listPhoto.get(position)).override(holder.widthView,holder.widthView).into(holder.ivImage);
        holder.ivImage.setTag(listPhoto.get(position));

    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {
        ImageView ivImage ;
        int widthView;

        public PhotoHolder(@NonNull final View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_photo);
            widthView= itemView.getResources().getDisplayMetrics().widthPixels/3;
            itemView.findViewById(R.id.ln_photo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.clickPhoto((String) ivImage.getTag());
                }
            });
        }
    }
    private onPhotoListener listener;


    public void setOnPhotoListener(onPhotoListener event){
        listener=event;
    }

    public  interface onPhotoListener{
        void clickPhoto(String data);
    }
}
