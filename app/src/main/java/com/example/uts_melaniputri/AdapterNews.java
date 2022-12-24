package com.example.uts_melaniputri;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
//public class AdapterNews {

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder>{

    private List<News> listBerita;
    private Activity activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public AdapterNews(List<News> listBerita, Activity activity) {
        this.listBerita = listBerita;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterNews.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterNews.MyViewHolder holder, int position) {
        final News berita = listBerita.get(position);
        holder.txtjudul.setText(berita.getTitle());
        holder.txtisi.setText(berita.getDesc());
//
//        holder.delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//                builder.setPositiveButton("iya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        databaseReference.child("Berita").child(berita.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(activity, "Data berita berhasil dihapus", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(activity, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).setMessage("Apakah yakin akan dihapus? " + berita.getTitle());
//                builder.show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtjudul, txtisi;
        Spinner spinner;
        Button update, delete;

        public MyViewHolder(View view) {
            super(view);
            txtjudul = view.findViewById(R.id.titleAdapter);
            txtisi = view.findViewById(R.id.descAdapter);
            update = view.findViewById(R.id.btn_update);
            delete = view.findViewById(R.id.btn_delete);
        }
    }
}
