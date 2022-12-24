package com.example.uts_melaniputri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailBeritaActivity extends AppCompatActivity {

    TextView title, desc;
    String txtTitle, txtdesc,  key;
    DatabaseReference mDatabaseReference;

//    public DetailBeritaActivity(RecyclerView listItem, Berita berita) {
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailberita);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        title = findViewById(R.id.judulBerita);
        desc = findViewById(R.id.desc);
        //get intent dari adapter
        Intent intent = getIntent();
        txtTitle = intent.getStringExtra("title");
        txtdesc = intent.getStringExtra("desc");

        title.setText(txtTitle);
        desc.setText(txtdesc);


        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateNews.class);
                intent.putExtra("TITLE", txtTitle);
                intent.putExtra("KONTEN", txtdesc);

                startActivity(intent);
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
                Intent intent = new Intent(getApplicationContext(), AddNews.class);
                startActivity(intent);
            }
        });
    }
    private void deleteData() {
        mDatabaseReference.child("News").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()) {
                    if((item.getValue(News.class)).getTitle().equals(title)) {
                        key = item.getKey();
                        mDatabaseReference.child(key).removeValue();
                        Toast.makeText(getApplicationContext(), "Update Succesfully!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}