package com.example.uts_melaniputri;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataNews extends RecyclerView.ViewHolder {
    public TextView title, desc;
    public ImageView hapus;
    public DataNews(View view) {
        super(view);
        title = view.findViewById(R.id.titleAdapter);
        desc = view.findViewById(R.id.descAdapter);
        hapus = view.findViewById(R.id.btn_Hapus);
    }
}
