package com.example.uts_melaniputri;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    RecyclerView recyclerViewBerita;
    List<News> beritas = new ArrayList<>();
    AdapterNews berita_adapter;
    String key;
    Button btn_add;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.kategori);
        btn_add = findViewById(R.id.btn_tambah);
//        btn_logout = findViewById(R.id.fab_logout);
        recyclerViewBerita = findViewById(R.id.recyclerview);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddNews.class);
                startActivity(intent);
            }
        });

//        btn_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.logoutPage();
//                Intent intent = new Intent(v.getContext(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBerita.setLayoutManager(layoutManager);
        recyclerViewBerita.setItemAnimator(new DefaultItemAnimator());

        tampilData();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (spinner != null) {
            spinner.setAdapter(adapter);
        }
    }

    private void tampilData() {
        databaseReference.child("Berita").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                beritas = new ArrayList<>();
                for (DataSnapshot item : snapshot.getChildren()) {
//                    key = item.getKey();
                    News berita = item.getValue(News.class);
                    berita.setKey(item.getKey());
                    beritas.add(berita);
                }
                berita_adapter = new AdapterNews(beritas, MainActivity.this);
                recyclerViewBerita.setAdapter(berita_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
