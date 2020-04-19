package com.example.amiiboapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectionsStatsActivity extends AppCompatActivity {
    private RecyclerView mCollectionStatsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections_stats);

        ArrayList<CollectionStatsItem> collectionStatsList = new ArrayList<>();
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Total Collection", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Animal Crossing", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "BoxBoy!", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Chibi-Robo!", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Dark Souls", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Diablo", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Fire Emblem", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Kirby", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Mario Sports Superstars", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Mega Man", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Metroid", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Monster Hunter", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Pikmin", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Pokémon", "Percentage of your collection: "));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Pokkén Tournament", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Shovel Knight", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Skylanders", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Splatoon", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Super Mario", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Super Mario Bros. 30th Anniversary", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Super Mario Cereal", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Super Smash Bros.", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "The Legend of Zelda", "Percentage of your collection: %"));
        collectionStatsList.add(new CollectionStatsItem(R.drawable.circle_progress_bar, "Yoshi's Woolly World", "Percentage of your collection: %"));

        mCollectionStatsRecyclerView = findViewById(R.id.collectionStats_recyclerView);
        mCollectionStatsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CollectionStatsAdapter(collectionStatsList);

        mCollectionStatsRecyclerView.setLayoutManager(mLayoutManager);
        mCollectionStatsRecyclerView.setAdapter(mAdapter);
    }
}