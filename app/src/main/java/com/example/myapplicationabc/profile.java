package com.example.myapplicationabc;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class profile extends AppCompatActivity {
    EditText txtID, txtName, txtAdd, txtrev, txtsug;
    Button btnsave, btnshow, btnupdate, btndelete;
    Customer customer;
    DatabaseReference dbref;
    Button btn;
    ImageView imageView;
    int SELECT_IMAGE_CODE=1;
    Button btnLogOut;
    FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btnLogOut = findViewById(R.id.btnLogout);
        btn=findViewById(R.id.btn);
        imageView=findViewById(R.id.pickedImage);



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Title"),SELECT_IMAGE_CODE);


            }

        });





        txtID = (EditText) findViewById((R.id.txtID));
        txtName = (EditText) findViewById((R.id.txtName));
        txtAdd = (EditText) findViewById((R.id.txtAdd));
        txtrev = (EditText) findViewById((R.id.txtrev));
        txtsug = (EditText) findViewById((R.id.txtsug));

        btnsave = (Button) findViewById(R.id.btnsave);
        btnshow = (Button) findViewById(R.id.btnshow);
        btnupdate = (Button) findViewById(R.id.btnupdate);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnLogOut = (Button) findViewById(R.id.btnLogout);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(profile.this,activity_login.class));
            }
        });


        customer = new Customer();
        dbref = FirebaseDatabase.getInstance().getReference().child("Customer");
        //insert
        btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                customer.setCustomerID(txtID.getText().toString().trim());
                customer.setName(txtName.getText().toString().trim());
                customer.setAddress(txtAdd.getText().toString().trim());
                customer.setReview(txtrev.getText().toString().trim());
                customer.setSuggestion(txtsug.getText().toString().trim());

                dbref.push().setValue(customer);
                Toast.makeText(getApplicationContext(),"data inserted successfully",Toast.LENGTH_SHORT).show();
            }

        });




        //retrieve
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Customer").child("customer2");
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String customerID = dataSnapshot.child("customerID").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String Address = dataSnapshot.child("Address").getValue().toString();
                        String review = dataSnapshot.child("review").getValue().toString();
                        String suggestion = dataSnapshot.child("suggestion").getValue().toString();

                        txtID.setText(customerID);
                        txtName.setText(name);
                        txtAdd.setText(Address);
                        txtrev.setText(review);
                        txtsug.setText(suggestion);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //update
        btnupdate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String customerID= txtID.getText().toString().trim();
                String name= txtName.getText().toString().trim();
                String Address= txtAdd.getText().toString().trim();
                String review= txtrev.getText().toString().trim();
                String suggestion= txtsug.getText().toString().trim();
                updateCustomer(customerID, name, Address, review,suggestion);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            Uri uri=data.getData();
            imageView.setImageURI(uri);
            btn.setText("Done");
        }






    }

    private void updateCustomer(String customerID, String name, String Address, String review, String suggestion){
        HashMap customer = new HashMap();
        customer.put("customerID", customerID);
        customer.put("name", name);
        customer.put("Address", Address);
        customer.put("review", review);
        customer.put("suggestion", suggestion);

        DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("customer2");
        upRef.updateChildren(customer).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(profile.this, "updated successfully!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(profile.this, "Updating Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //delete

        btndelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Customer").child("customer2");
                delRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(profile.this, "customer deleted", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(profile.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });






            }
        });
    }


}
