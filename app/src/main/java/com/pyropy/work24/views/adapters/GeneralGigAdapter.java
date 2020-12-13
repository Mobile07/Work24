package com.pyropy.work24.views.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.annotations.NotNull;
import com.pyropy.work24.R;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.model.GigModel;



public class GeneralGigAdapter extends FirebaseRecyclerAdapter<
        GigHelper, GeneralGigAdapter.myviewholder> {

    public GeneralGigAdapter(
            @NonNull FirebaseRecyclerOptions<GigHelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GeneralGigAdapter.myviewholder holder, int position, @NonNull GigHelper model) {

        holder.gigImg.setText(model.getImg1Uri());
        holder.gigTitle.setText(model.getGigTitle());
        holder.price.setText(model.getPrice());

    }


    @NonNull
    @Override
    public myviewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerow, parent, false);
        return new GeneralGigAdapter.myviewholder(view);
    }


    public class myviewholder extends RecyclerView.ViewHolder {
        TextView gigImg, gigTitle, price;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            gigImg = itemView.findViewById(R.id.gigImg);
            gigTitle = itemView.findViewById(R.id.gigTitle);
            price = itemView.findViewById(R.id.price);
        }
    }
}