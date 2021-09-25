package com.example.mad_project_2021;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class adminadapter extends FirebaseRecyclerAdapter<model,adminadapter.myviewholder>
{
    public adminadapter(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myviewholder holder,final int position, @NonNull @NotNull model model)
    {
        holder.author.setText(model.getAuthor());
        holder.title.setText(model.getTitle());
        holder.description.setText(model.getDescription());
        holder.price.setText(model.getPrice());
        holder.qt.setText(model.getQt());
        Glide.with(holder.img.getContext()).load(model.getBurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_focused)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1500)
                        .create();

                //dialogPlus.show();

                //call to popup window to added data

                View view = dialogPlus.getHolderView();

                EditText txtBookname = view.findViewById(R.id.txtBookname);
                EditText txtDes = view.findViewById(R.id.txtDes);
                EditText txtburl = view.findViewById(R.id.txtburl);
                EditText txtauthor = view.findViewById(R.id.txtauthor);
                EditText txtquantity = view.findViewById(R.id.txtquantity);
                EditText txtprice = view.findViewById(R.id.txtprice);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                txtBookname.setText(model.getTitle());
                txtDes.setText(model.getDescription());
                txtburl.setText(model.getBurl());
                txtauthor.setText(model.getAuthor());
                txtquantity.setText(model.getQt());
                txtprice.setText(model.getPrice());

                dialogPlus.show();

                //perform click operation in edit

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map = new HashMap<>();
                        map.put("title",txtBookname.getText().toString());
                        map.put("description", txtDes.getText().toString());
                        map.put("burl", txtburl.getText().toString());
                        map.put("author", txtauthor.getText().toString());
                        map.put("qt", txtquantity.getText().toString());
                        map.put("price", txtprice.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Book List")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.title.getContext(), "Data Updated Sussessfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.title.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });

        // delete operation

        holder .btnDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Book List")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.title.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                
                builder.show();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_item,parent,false);//connect xml file
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView author,description,price, qt, title;

        Button btnEdit,btnDelet;

        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            img=(CircleImageView)itemView.findViewById(R.id.img1);
            title=(TextView)itemView.findViewById(R.id.nametext);
            description=(TextView)itemView.findViewById(R.id.coursetext);
            author=(TextView)itemView.findViewById(R.id.emailtext);
            qt=(TextView)itemView.findViewById(R.id.emailtext2);
            price=(TextView)itemView.findViewById(R.id.emailtext3);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelet=(Button)itemView.findViewById(R.id.btnDelet);
        }
    }

}
