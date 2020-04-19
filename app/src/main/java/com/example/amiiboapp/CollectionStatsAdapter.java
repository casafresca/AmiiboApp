package com.example.amiiboapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectionStatsAdapter extends RecyclerView.Adapter<CollectionStatsAdapter.CollectionStatsViewHolder> {
    private ArrayList<CollectionStatsItem> mCollectionList;

    public CollectionStatsAdapter(ArrayList<CollectionStatsItem> collectionList) {
        mCollectionList = collectionList;
    }

    @NonNull
    @Override
    public CollectionStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_stats_item, parent, false);
        CollectionStatsViewHolder collectionViewHolder = new CollectionStatsViewHolder(v);
        return collectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionStatsViewHolder holder, int position) {
        CollectionStatsItem current = mCollectionList.get(position);

        holder.mProgressBar.setProgress(current.getProgressBarStatus());
        holder.mGameSeriesText.setText(current.getGameSeriesText());
        holder.mPercentageText.setText(current.getPercentageText());
    }

    @Override
    public int getItemCount() {
        return mCollectionList.size();
    }

    public static class CollectionStatsViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar mProgressBar;
        public TextView mGameSeriesText;
        public TextView mPercentageText;

        public CollectionStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            mGameSeriesText = itemView.findViewById(R.id.gameSeries_textView);
            mPercentageText = itemView.findViewById(R.id.percentage_textView);
        }
    }
}
