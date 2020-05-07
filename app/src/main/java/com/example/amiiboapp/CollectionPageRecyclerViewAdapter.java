package com.example.amiiboapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        CollectionPageListItem currentItem = mCollectionList.get(position);

        String amiiboImageUrl = currentItem.getAmiiboImage();
        String amiiboName = currentItem.getAmiiboName();
        String amiiboDateBought = currentItem.getDateBought();
        String amiiboPrice = currentItem.getPrice();

        viewHolder.name.setText(amiiboName);
        viewHolder.dateBought.setText("Enter Date");
        viewHolder.price.setText("Enter Price");

        Glide.with(context).load(amiiboImageUrl).into(viewHolder.image);

        viewHolder.price.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                final EditText dialogEditText = new EditText(context);

                alertDialog.setTitle("Enter Price");
                alertDialog.setView(dialogEditText);

                alertDialog.setPositiveButton("SAVE TEXT", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        viewHolder.price.setText(dialogEditText.getText());
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

        viewHolder.dateBought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                final EditText dialogEditText = new EditText(context);

                alertDialog.setTitle("Enter Date Bought");
                alertDialog.setView(dialogEditText);

                alertDialog.setPositiveButton("SAVE TEXT", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which)
                    {
                        viewHolder.dateBought.setText(dialogEditText.getText());
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
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
