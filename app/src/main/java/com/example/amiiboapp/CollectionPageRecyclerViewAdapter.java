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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CollectionPageRecyclerViewAdapter extends RecyclerView.Adapter<CollectionPageRecyclerViewAdapter.ViewHolder> {
    ArrayList<CollectionPageListItem> mCollectionList;
    Context context;

    public CollectionPageRecyclerViewAdapter(Context context, ArrayList<CollectionPageListItem> collectionList) {
        this.context = context;
        mCollectionList = collectionList;
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
        CollectionPageListItem currentItem = mCollectionList.get(position);

        String amiiboImageUrl = currentItem.getAmiiboImage();
        String amiiboName = currentItem.getAmiiboName();
        String amiiboDateBought = currentItem.getDateBought();
        String amiiboPrice = currentItem.getPrice();

        viewHolder.name.setText(amiiboName);
        viewHolder.dateBought.setText("Enter Date");
        viewHolder.price.setText("Enter Price");

        Glide.with(context).load(amiiboImageUrl).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mCollectionList.size();
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
