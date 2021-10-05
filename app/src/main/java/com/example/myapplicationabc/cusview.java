package com.example.myapplicationabc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class cusview extends AppCompatActivity {

    ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cusview);



        cart = (ImageButton) findViewById(R.id.imageButton2);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(cusview.this, profile.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}