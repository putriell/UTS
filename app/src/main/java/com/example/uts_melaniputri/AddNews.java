package com.example.uts_melaniputri;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.HashMap;

public class AddNews extends AppCompatActivity {

    EditText inputJudul, inputIsi;
    Spinner Kategori;
    Button btn_tambahBerita;
    TextView kembali;
    public static SharedPreferences sharedPreference;

    private NotificationManager mnotificationManager;
    private final static String CHANNEL_ID = "primary-channel";
    private int NOTIFICATION_ID = 0;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Berita");

//    private EditText tittle, desk;
//    private RadioButton child, semuaUmur;
//    private Spinner spinner;
//    private TextView btn_batal;
//    private String channelNotif = "notfikasi";
//    private String channelId = "default";
//    private Button insert;
//    private String filter = "";
//    private String kat = "";

//    private ProgressDialog progressDialog;
//    private DatabaseReference mDatabaseReference;
//    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        inputJudul = findViewById(R.id.judul_edittext);
        inputIsi = findViewById(R.id.isi_edittext);
        Kategori = findViewById(R.id.list_kategori);
        btn_tambahBerita = findViewById(R.id.insert_berita);
        kembali = findViewById(R.id.btn_batal);

        mnotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "app notif", NotificationManager.IMPORTANCE_HIGH);
            mnotificationManager.createNotificationChannel(notificationChannel);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (Kategori != null) {
            Kategori.setAdapter(adapter);
        }

        btn_tambahBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBerita();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNews.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addBerita(){
        News berita = new News();
        String judulberita = inputJudul.getText().toString();
        String isiberita = inputIsi.getText().toString();
        String kategori = Kategori.getSelectedItem().toString();

        if (judulberita.isEmpty() || isiberita.isEmpty()){
            Toast.makeText(this, "Data berita tidak boleh ada yang kosong!", Toast.LENGTH_SHORT).show();
        } else {
            databaseReference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    berita.setTitle(judulberita);
                    berita.setDesc(isiberita);
                    berita.setKategori(kategori);

                    databaseReference.child(judulberita).setValue(berita);
//                    sendNotification();
                    Toast.makeText(AddNews.this, "Berita berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
//    private NotificationCompat.Builder getNotificationBuilder() {
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent notificationPendingIntent = PendingIntent
//                .getActivity(this, NOTIFICATION_ID, notificationIntent,
//                        PendingIntent.FLAG_IMMUTABLE);

//        NotificationCompat.Builder notifyBuilder =
//                new NotificationCompat.Builder(this, CHANNEL_ID)
//                        .setContentTitle("Berhasil Input")
//                        .setContentText("Input berita sukses dilakukan!")
//                        .setSmallIcon(R.drawable.ic_baseline_laptop_24).setContentIntent(notificationPendingIntent);
//        return notifyBuilder;
////    }
//
//    private void sendNotification() {
//        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
//        mnotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());
//    }
//
//        tittle = findViewById(R.id.judul_edittext);
//        desk = findViewById(R.id.isi_edittext);
//        insert = findViewById(R.id.insert_berita);
//        btn_batal = findViewById(R.id.btn_batal);
//        spinner = findViewById(R.id.kategori);
//        child = findViewById(R.id.child);
//        semuaUmur =findViewById(R.id.semua_umur);
//        mDatabaseReference = FirebaseDatabase.getInstance().getReference(News.class.getSimpleName());
////        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        //loading
////        progressDialog = new ProgressDialog(AddNews.this);
////        progressDialog.setMessage("Silahkan tunggu..");
////        progressDialog.setCancelable(false);
////        kategori = findViewById(R.id.categori);
////
////        Intent intent = getIntent();
////        String message = intent.getStringExtra(MainActivity.MESSAGE_EXTRA);
////        kategori.setText("kategori :" + message);
//
//       child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                filter = "child";
//            }
//        });
//        semuaUmur.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                filter = "all";
//            }
//        });
//
//        spinner = findViewById(R.id.list_kategori);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.labels_array, android.R.layout.simple_spinner_item);
//
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                kat = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
////        insert.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (tittle.getText().length() > 0 && desk.getText().length() > 0 && kat != "" && filter != ""){
////                    saveBerita(tittle.getText().toString(), desk.getText().toString(), kat, filter, firebaseUser.getDisplayName());
////                }else {
////                    Toast.makeText(getApplicationContext(), "silahkah isi semua data!", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
//        btn_batal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//
//        insert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                News newBerita = new News();
//                String getKategori = spinner.getOnItemSelectedListener().toString();
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
//                    mDatabaseReference.child("Berita").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            mDatabaseReference.child("Berita").child(getTittle).child("deskripsi").setValue(getDesc);
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
//            }
//
//    private void notif(){
//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(AddNews.this, channelId)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setContentTitle("Berita berhasil diunggah!")
//                .setContentText("Terimakasih telah berbagi informasi!");
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel notificationChannel = new NotificationChannel(channelNotif, "notifikasi",importance);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            mBuilder.setChannelId(channelNotif);
//            assert mNotificationManager != null;
//            mNotificationManager.createNotificationChannel(notificationChannel);
//        }
//        assert mNotificationManager != null;
//        mNotificationManager.notify((int)System.currentTimeMillis(),mBuilder.build());
//    }

}
