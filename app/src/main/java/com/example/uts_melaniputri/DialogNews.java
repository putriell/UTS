package com.example.uts_melaniputri;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogNews extends DialogFragment {
//    String title, desc, key, pilih;
//    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//
//    public DialogNews(String title, String desc, String key, String pilih) {
//        this.title = title;
//        this.desc = desc;
//        this.key = key;
//        this.pilih = pilih;
//    }
//
//    TextView tjudul, tdesc;
//    Button insert;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        final View view = inflater.inflate(R.layout.activity_add_news, container, false);
//        tjudul = view.findViewById(R.id.judul_edittext);
//        tdesc = view.findViewById(R.id.isi_edittext);
//        insert = view.findViewById(R.id.insert_berita);
//
//        tjudul.setText(title);
//        tdesc.setText(desc);
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String judul = tjudul.getText().toString();
//                String desc = tdesc.getText().toString();
//
//                if (pilih.equals("ubah")) {
//                    databaseReference.child("Berita").child(key).setValue(new News(title, desc)).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(view.getContext(), "berhasil di update", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(view.getContext(), "gagal memperbarui", Toast.LENGTH_SHORT).show();
//                        }
//
//                    });
//                }
//            }
//        });
//
//            return view;
//        }
////        public void onStart() {
////            super.onStart();
////            Dialog dialog =getDialog();
////            if(dialog != null){
////                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////            }
////        }
//

}




