package com.example.uts_melaniputri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-96d3a-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText username = findViewById(R.id.username_signup);
        final EditText password = findViewById(R.id.password_signup);
        final EditText repassword = findViewById(R.id.repasword);
        final Button register = findViewById(R.id.register_btn);
        final TextView loginNow = findViewById(R.id.loginNow);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edittext into string variables
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String repasswordtxt = repassword.getText().toString();

                //chek if fill all the fields before sedding daya to firebase
                if (usernametxt.isEmpty() || passwordtxt.isEmpty() || repasswordtxt.isEmpty()){
                    Toast.makeText(Signup.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }
                //chek password sama dengan yang lain
                //jika tidak sama dengan yang lain maka akan muncul teks toast
                else if(!passwordtxt.equals(repasswordtxt)){
                    Toast.makeText(Signup.this, "password are not matching", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usernametxt)){
                                Toast.makeText(Signup.this, "phone is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("users").child(usernametxt).child("password").setValue(passwordtxt);
                                Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}