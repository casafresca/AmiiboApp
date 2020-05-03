package com.example.amiiboapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String amiiboName = currentItem.getmAmiiboName();
        String otherInfo = currentItem.getmOtherInfo();

        holder.mTextViewAmiiboName.setText(amiiboName);
        holder.mTextViewOtherInfo.setText(otherInfo);

        Glide.with(mContext).load(imageUrl).fitCenter().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewAmiiboName;
        public TextView mTextViewOtherInfo;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewAmiiboName = itemView.findViewById(R.id.text_view_amiibo);
            mTextViewOtherInfo = itemView.findViewById(R.id.text_view_info);
        }
    }

    public void updateList(List<ExampleItem> newList){
        mExampleList = new ArrayList<>();
        mExampleList.addAll(newList);
        notifyDataSetChanged();

    }
}
