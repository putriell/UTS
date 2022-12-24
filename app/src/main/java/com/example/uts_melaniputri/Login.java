package com.example.uts_melaniputri;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-10f1c-default-rtdb.firebaseio.com/");
    EditText username,password;
    Button login;
    TextView register;
    boolean mLogin = false;
//    boolean mLogout = false;

    private static SharedPreferences mSharedPref;
    private final String sharedPrefFile = "com.example.uts_melaniputri";
    private static final String KEY = "key";
//    private final String KEY2 = "key2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
//        getmSharedPref = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.registerNow);
        mLogin = mSharedPref.getBoolean(KEY, false);
//        mLogout = getmSharedPref.getBoolean(KEY2, false);


        if(mLogin) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                if(usernametxt.isEmpty() || passwordtxt.isEmpty()) {
                    Toast.makeText(Login.this, "please enter your username or password", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usernametxt)){

                                final String getPassword = snapshot.child(usernametxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordtxt)){
                                    Toast.makeText(Login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                    mLogin = true;
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    saveLogin();
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(Login.this, "Your account has not been registered", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
    }

    public static void logoutPage() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(KEY, false);
        editor.apply();
    }
    private void saveLogin(){
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(KEY, mLogin);
        editor.apply();
    }

}