package com.example.uts_melaniputri;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddNews extends AppCompatActivity {

    private EditText tittle, desk;
    private RadioButton child, semuaUmur;
    private Spinner spinner;
    private TextView btn_batal;
    private String channelNotif = "notfikasi";
    private String channelId = "default";
    private Button insert;
    private String filter = "";
    private String kat = "";

    private ProgressDialog progressDialog;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        tittle = findViewById(R.id.judul_edittext);
        desk = findViewById(R.id.isi_edittext);
        insert = findViewById(R.id.insert_berita);
        btn_batal = findViewById(R.id.btn_batal);
        child = findViewById(R.id.child);
        semuaUmur =findViewById(R.id.semua_umur);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(News.class.getSimpleName());
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //loading
        progressDialog = new ProgressDialog(AddNews.this);
        progressDialog.setMessage("Silahkan tunggu..");
        progressDialog.setCancelable(false);
//        kategori = findViewById(R.id.categori);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.MESSAGE_EXTRA);
//        kategori.setText("kategori :" + message);

       child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filter = "child";
            }
        });
        semuaUmur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                filter = "all";
            }
        });

        spinner = findViewById(R.id.list_kategori);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kat = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tittle.getText().length() > 0 && desk.getText().length() > 0 && kat != "" && filter != ""){
                    saveBerita(tittle.getText().toString(), desk.getText().toString(), kat, filter, firebaseUser.getDisplayName());
                }else {
                    Toast.makeText(getApplicationContext(), "silahkah isi semua data!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                News newBerita = new News();
////                String getKategori = kategori.getText().toString();
//                String getTittle = tittle.getText().toString();
//                String getDesc = desk.getText().toString();
//
//                if (getTittle.isEmpty()){
//                    tittle.setError("masukkan judul");
//                }
//                else if (getDesc.isEmpty()){
//                    desk.setError("masukkan deskripsi");
//                }
//                else {
//                    databaseReference.child("Berita").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            databaseReference.child("Berita").child(getTittle).child("deskripsi").setValue(getDesc);
////                        newBerita.setTitle(getTittle);
////                        newBerita.setDesc(getDesc);
////                        databaseReference.push().setValue(newBerita);
//                            Toast.makeText(AddNews.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(AddNews.this, MainActivity.class));
//                            notif();
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//                            Toast.makeText(AddNews.this, "Gagal Menyimpan", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
//            }
//        });
    }
    private void saveBerita(String title, String desc, String kategori, String filter, String namaUser){
        News newNews =new News();
        newNews.setTitle(title);
        newNews.setDesc(desc);
        newNews.setKategori(kategori);
        newNews.setUmur(filter);

        mDatabaseReference.push().setValue(newNews);
        Toast.makeText(this, "Successfilly insert data!", Toast.LENGTH_SHORT).show();
    }
    private void notif(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(AddNews.this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Berita berhasil diunggah!")
                .setContentText("Terimakasih telah berbagi informasi!");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelNotif, "notifikasi",importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            mBuilder.setChannelId(channelNotif);
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify((int)System.currentTimeMillis(),mBuilder.build());
    }

}
