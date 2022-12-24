package com.example.uts_melaniputri;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder>  {

    private List<News> mList;
    private Activity activity;
    Spinner spinner;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

//    public AdapterNews(RecyclerView mList, Activity activity){
//        this.mList = (List<News>) mList;
//        this.activity = activity;
//    }
    public AdapterNews(List<News> mList, Activity activity, String filterUmur, String kat){
        this.mList = mList;
        this.activity = activity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(viewItem);

    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final News data = mList.get(position);
//        holder.tittle.setText(data.getTitle());
//        holder.desc.setText(data.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailBeritaActivity.class);
//                intent.putExtras("tittle", data.getTitle());
//                intent.putExtras("deskripsi", data.getDesc());
//
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card_hasil;
        TextView tittle, desc;
        ImageView hapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.titleAdapter);
            desc = itemView.findViewById(R.id.descAdapter);
            card_hasil = itemView.findViewById(R.id.card_hasil);
            hapus = itemView.findViewById(R.id.hapus);
        }
    }
}

