//package com.example.uts_melaniputri;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class Berita extends AppCompatActivity {
//
//    TextView kategori;
//    RecyclerView listItem;
//    AdapterNews adapterNews;
////    private ArrayList<News> list = new ArrayList<>();
//    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//    ArrayList<News> listBerita;
//    String key;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////
//        listItem = findViewById(R.id.recyclerview);
//        kategori = findViewById(R.id.kategori);
//
//        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
//        listItem.setLayoutManager(mLayout);
//        listItem.setItemAnimator(new DefaultItemAnimator());
//        tampilkanData();
//    }
//
//    private void tampilkanData(){
//        database.child("Mahasiswa").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                listBerita = new ArrayList<>();
//                for (DataSnapshot item : snapshot.getChildren()){
//                    News berita = item.getValue(News.class);
//                    berita.setKey(item.getKey());
//                    listBerita.add(berita);
//                }
//                adapterNews = new AdapterNews(listBerita, Berita.this);
//                listItem.setAdapter(adapterNews);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
////        database.child("Berita");
////        database.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
//////                if (snapshot.hasChildren()){
//////                    for (DataSnapshot currentData : snapshot.getChildren()){
//////                        key = currentData.getKey();
//////
//////                    }
//////                }
////                listBerita = new ArrayList<>();
////                for (DataSnapshot item : snapshot.getChildren()) {
////                    News berita = item.getValue(News.class);
////                    berita.setKey(item.getKey());
////                    listBerita.add(berita);
////                }
////
////                adapterNews = new AdapterNews(listItem, Berita.this);
////                listItem.setAdapter(adapterNews);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//    }
////
////    private void showRecycleList() {
////        listItem.setLayoutManager(new LinearLayoutManager(this));
////        ListBerita listberita = new ListBerita(list);
////        listItem.setAdapter(listberita);
////    }
//
//
//}