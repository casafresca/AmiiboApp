package com.example.amiiboapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CollectionsStatsActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {
    private RecyclerView mCollectionStatsRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPrefTheme sharedPrefTheme;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Light vs Dark Mode
        sharedPrefTheme = new SharedPrefTheme(this);
        if(sharedPrefTheme.loadNightModeState() == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

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

        // Bottom Menu Navigation
        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setSelectedItemId(R.id.collectionStats);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.collectionPage:
                        startActivity(new Intent(getApplicationContext(),CollectionPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.collectionStats:
                        return true;
                }
                return false;
            }
        });
    }
    //inflates context menu
    public void showPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.context_menu_two);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.what_is_amiibo:
                Toast.makeText(this, "Amiibo toys were first released in North America on November 21, 2014 for Wii U. " +
                        " It consists of a wireless communications and storage protocol for connecting figurines to the Wii U, " +
                        "Nintendo 3DS, and Nintendo Switch", Toast.LENGTH_LONG).show();
                return true;
            case R.id.about_nintendo:
                Toast.makeText(this, "The history of Nintendo traces back to 1889, when it was founded to produce handmade hanafuda. Nintendo Co., Ltd.  " +
                        "It is a Japanese multinational consumer electronics company headquartered in Kyoto, Japan. It eventually became the world's largest video game company by revenue ",
                        Toast.LENGTH_LONG).show();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}