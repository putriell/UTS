package com.example.uts_melaniputri;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private AdapterNews newsAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<News> data;
    String kat, tanggal_lahir, key, filterUmur;
    FloatingActionButton tambah;
    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get data from user detail
        Intent intent = getIntent();
        kat = intent.getStringExtra("kategori");
        tanggal_lahir = intent.getStringExtra("tanggal_lahir");

        //recyclerView
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        //Button add berita
        tambah = findViewById(R.id.btn_tambah);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNews = new Intent(view.getContext(), AddNews.class);
                startActivity(addNews);
            }
        });

        //menghitung umur
        String TanggalLahirUser[] = tanggal_lahir.split("-");
        int umurUser = 2022 - Integer.parseInt(TanggalLahirUser[2]);
        if (umurUser >= 18 ) {
            filterUmur = "all";
        }else {
            filterUmur = "child";
        }



        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(newsAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        tampildata();
    }

    private void tampildata() {
        mDatabaseReference.child("News").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                data = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()){
                    News dataBerita = item.getValue(News.class);
                    dataBerita.setKey(item.getKey());
                    data.add(dataBerita);
                }

                newsAdapter = new AdapterNews(data, MainActivity.this, filterUmur, kat);
                recyclerView.setAdapter(newsAdapter);


                //kategori berita

//                for(int i = 0; i < dataBerita.judul.length(); i++){
//                    if(filterUmur.equals(dataBerita.umur)) {
//                        if(NewsData.kategori[i].equals(kat)){
//                            data.add(new News(
//                                    NewsData.judul[i],
//                                    NewsData.description[i],
//                                    NewsData.umur[i],
//                                    NewsData.kategori[i],
//                                    ));
//                        }
//                    }
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    Spinner spinner;
//    Button tambah;
//    RecyclerView listItem;
//    String key;
//    AdapterNews adapterNews;
//
////    private static String key = "key";
////    private ArrayList<ModelNews> list = new ArrayList<>();
//    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//    ArrayList<ModelNews> listBerita;
//    private Spinner recyclerView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        tambah = findViewById(R.id.btn_tambah);
//        listItem = findViewById(R.id.recyclerview);
//        listItem.setAdapter(adapterNews);
//        listItem.setLayoutManager(new LinearLayoutManager(this));
////        kategori = findViewById(R.id.kategori);
//
//        RecyclerView.LayoutManager mLayout = new LinearLayoutManager(this);
//        listItem.setLayoutManager(mLayout);
//        listItem.setItemAnimator(new DefaultItemAnimator());
//        tampilkanData();
//
//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, AddNews.class));
//            }
//        });
////      TextView kategori;
//
//    }
//
//    private void tampilkanData() {
//        database.child("Berita").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot item : snapshot.getChildren()) {
////                    key = item.getKey();
////                    ModelNews berita = item.getValue(ModelNews.class);
//////                    berita.setTitle(item.getKey());
////                    listBerita.add(berita);
////                  }
////                adapterNews = new AdapterNews(listBerita, MainActivity.this);
////                recyclerView.setAdapter(adapterNews);
////
//                listBerita = new ArrayList<>();
//                for (DataSnapshot item : snapshot.getChildren()) {
//                    ModelNews berita = item.getValue(ModelNews.class);
////                    berita.setKey(item.getKey());
////                    berita.setTitle(getTitle());
////                    berita.setDesc(getDesc());
//                    listBerita.add(berita);
//                }
//                adapterNews = new AdapterNews(listBerita, MainActivity.this);
//                listItem.setAdapter(adapterNews);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}