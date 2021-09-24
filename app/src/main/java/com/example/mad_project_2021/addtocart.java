package com.example.mad_project_2021;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class addtocart extends AppCompatActivity {
    EditText textquantity1;
    EditText textquantity2;
    EditText textquantity3;
    Button buttoncheckout;
    Button btnUpdate;
    DatabaseReference reff;
    Quantity quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_addtocart);
        textquantity1 = (EditText) findViewById((R.id.textquantity1));
        textquantity2 = (EditText) findViewById((R.id.textquantity2));
        textquantity3 = (EditText) findViewById((R.id.textquantity3));
        buttoncheckout = (Button) findViewById(R.id.buttoncheckout);
        quantity = new Quantity();
        reff = FirebaseDatabase.getInstance().getReference().child("Quantity");
        buttoncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    int qty1 = Integer.parseInt(textquantity1.getText().toString().trim());
                    quantity.setQty1(qty1);}
                catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                try{
                    int qty2 = Integer.parseInt(textquantity2.getText().toString().trim());
                    quantity.setQty2(qty2);}
                catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                try{
                    int qty3 = Integer.parseInt(textquantity3.getText().toString().trim());
                    quantity.setQty3(qty3);}
                catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }


                reff.push().setValue(quantity);
                Toast.makeText(addtocart.this,"data inserted successfully", Toast.LENGTH_LONG).show();




            }
        });

        //update
        btnUpdate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                      int qty1=Integer.parseInt(textquantity1.getText().toString().trim());
                int qty2=Integer.parseInt(textquantity2.getText().toString().trim());
                int qty3=Integer.parseInt(textquantity3.getText().toString().trim());
                updateQunatity(qty1,qty2,qty3);
            }
        });
    }

    private void updateQunatity(int qty1, int qty2, int qty3){
        HashMap quantity = new HashMap();
        quantity.put("qty1", qty1);
        quantity.put("qty2", qty2);
        quantity.put("qty3", qty3);


        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Quantity").child("quantity2");
        upRef.updateChildren(quantity).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(addtocart.this, "updated successfully!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(addtocart.this, "Updating Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}