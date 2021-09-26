package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.mad_project_2021.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageButton group_60,group_59;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group_60 = (ImageButton) findViewById(R.id.imageButton6);

        group_60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, adminviewpage.class);
                startActivity(intentLoadNewActivity);
            }
        });

        group_59 = (ImageButton) findViewById(R.id.imageButton2);

        group_59.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

        logout = (Button) findViewById(R.id.btnSignOut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,activity_login.class));
            }
        });
    }
}