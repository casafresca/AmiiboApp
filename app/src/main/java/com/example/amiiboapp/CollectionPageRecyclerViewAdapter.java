package com.example.amiiboapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectionPageRecyclerViewAdapter extends RecyclerView.Adapter<CollectionPageRecyclerViewAdapter.ViewHolder> {
    ArrayList<String> mAmiiboImages = new ArrayList<>();
    ArrayList<String> mAmiiboNames = new ArrayList<>();
    String mAmiiboDateBought;
    String mAmiiboPrice;
    Context context;

    //find a way to do placeholders for date bought and price
    public CollectionPageRecyclerViewAdapter(Context context, ArrayList<String> amiiboImages,
                                             ArrayList<String> amiiboNames) {
        this.context = context;
        this.mAmiiboImages = amiiboImages;
        this.mAmiiboNames = amiiboNames;
        this.mAmiiboDateBought = "Enter Date";
        this.mAmiiboPrice = "$0.00";
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_page_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //get images with Glide

        viewHolder.name.setText(mAmiiboNames.get(position));
        viewHolder.dateBought.setText(mAmiiboDateBought);
        viewHolder.price.setText(mAmiiboPrice);
    }

    @Override
    public int getItemCount() {
        return mAmiiboNames.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView dateBought;
        TextView price;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.amiiboImage);
            name = itemView.findViewById(R.id.amiiboName);
            dateBought = itemView.findViewById(R.id.amiiboDateBought);
            price = itemView.findViewById(R.id.amiiboPrice);
            parentLayout = itemView.findViewById(R.id.collectionParentLayout);
        }
    }
}
