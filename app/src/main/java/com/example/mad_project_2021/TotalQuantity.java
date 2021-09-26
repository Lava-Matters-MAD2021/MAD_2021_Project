package com.example.mad_project_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TotalQuantity extends AppCompatActivity {
    EditText etnum1,etnum2,etnum3;
    TextView textV, tvResult;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_total_quantity);
        etnum1=(EditText)findViewById(R.id.etnum1);
        etnum2=(EditText)findViewById(R.id.etnum2);
        etnum3=(EditText)findViewById(R.id.etnum3);
        textV=(TextView)findViewById(R.id.tvResult);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        onAdd();
    }
    private void onAdd(){
        //calculate total quantity
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1=Integer.parseInt(etnum1.getText().toString());
                int num2=Integer.parseInt(etnum2.getText().toString());
                int num3=Integer.parseInt(etnum3.getText().toString());
                int result=num1+num2+num3;
                textV.setText(Integer.toString(result));
            }
        });
    }
}