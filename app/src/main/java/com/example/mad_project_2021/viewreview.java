package com.example.mad_project_2021;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class viewreview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_viewreview);
    }
}