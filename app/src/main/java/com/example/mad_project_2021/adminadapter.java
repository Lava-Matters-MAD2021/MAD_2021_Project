package com.example.mad_project_2021;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class adminadapter extends FirebaseRecyclerAdapter<model,adminadapter.myviewholder>
{
    public adminadapter(@NonNull @NotNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myviewholder holder, int position, @NonNull @NotNull model model)
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

                dialogPlus.show();
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
