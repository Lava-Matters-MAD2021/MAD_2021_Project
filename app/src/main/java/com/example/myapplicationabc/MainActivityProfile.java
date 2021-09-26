package com.example.myapplicationabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityProfile extends AppCompatActivity {


        Button btnLogOut;
        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mainactivityprofile);

            btnLogOut = findViewById(R.id.btnLogout);
            mAuth = FirebaseAuth.getInstance();

            btnLogOut.setOnClickListener(view ->{
                mAuth.signOut();
                startActivity(new Intent(MainActivityProfile.this, activity_login.class));
            });

        }

        @Override
        protected void onStart() {
            super.onStart();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user == null){
                startActivity(new Intent(MainActivityProfile.this, activity_login.class));
            }
        }
    }