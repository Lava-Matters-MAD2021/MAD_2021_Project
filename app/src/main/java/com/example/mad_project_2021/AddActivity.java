package com.example.mad_project_2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    //register edit text and buttons

    EditText author,burl,description,price,qt,title;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = (EditText)findViewById(R.id.txtBookname);
        author = (EditText)findViewById(R.id.txtauthor);
        burl = (EditText)findViewById(R.id.txtburl);
        description = (EditText)findViewById(R.id.txtDes);
        price = (EditText)findViewById(R.id.txtprice);
        qt = (EditText)findViewById(R.id.txtquantity);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnBack = (Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //got values text field

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("author",author.getText().toString());
        map.put("title",title.getText().toString());
        map.put("burl",burl.getText().toString());
        map.put("description",description.getText().toString());
        map.put("price",price.getText().toString());
        map.put("qt",qt.getText().toString());

        //connect database in adding values

        FirebaseDatabase.getInstance().getReference().child("Book List").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //added data successfully and clear in data

    private void clearAll()
    {
        author.setText("");
        title.setText("");
        burl.setText("");
        description.setText("");
        price.setText("");
        qt.setText("");
    }
}