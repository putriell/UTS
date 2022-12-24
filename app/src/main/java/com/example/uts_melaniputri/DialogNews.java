package com.example.uts_melaniputri;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogNews extends DialogFragment {
    String title, kategori, desc, key, pilih;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public DialogNews(String kategori, String title, String desc, String key, String pilih) {
        this.kategori = kategori;
        this.title = title;
        this.desc = desc;
        this.key = key;
        this.pilih = pilih;
    }

    EditText judul, tv_kategori, deskripsi;
    Button insert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_add_news, container, false);
        judul = view.findViewById(R.id.judul_edittext);
        deskripsi = view.findViewById(R.id.isi_edittext);
         tv_kategori= view.findViewById(R.id.list_kategori);
        insert = view.findViewById(R.id.insert_berita);

        judul.setText(title);
        tv_kategori.setText(kategori);
        deskripsi.setText(desc);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = judul.getText().toString();
                String kategori = tv_kategori.getText().toString();
                String isi = deskripsi.getText().toString();

                if (pilih.equals("Ubah")){
                    databaseReference.child("Berita").setValue(new News(title, desc, kategori)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(view.getContext(), "Berhasil diupdate", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view.getContext(), "Maaf gagal diupdate", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }
//    private DatabaseReference databaseReference;
//    public DialogNews(){
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        databaseReference = db.getReference(News.class.getSimpleName());
//
//    }
//    public Task<Void> add(News emp)
//    {
//        return databaseReference.push().setValue(emp);
//    }
//    public Task<Void> update(String key, HashMap<String ,Object> hashMap)
//    {
//        return databaseReference.child(key).updateChildren(hashMap);
//    }
//    public Task<Void> remove(String key)
//    {
//        return databaseReference.child(key).removeValue();
//    }
//    public Query get(String key) {
//        if (key == null) {
//           return databaseReference.orderByKey().limitToFirst(8);
//        }
//        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);


}




