package com.example.amiiboapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectionPage extends AppCompatActivity {
    ArrayList<String> mAmiiboImages = new ArrayList<>();
    ArrayList<String> mAmiiboNames = new ArrayList<>();
    RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);


        CollectionPageRecyclerViewAdapter adapter = new CollectionPageRecyclerViewAdapter(this, mAmiiboImages, mAmiiboNames);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void collectionOnClick(View view) {
        int amiiboListSize = mAmiiboNames.size();

        //add amiibo

        mRecyclerView.getAdapter().notifyItemInserted(amiiboListSize);
        mRecyclerView.smoothScrollToPosition(amiiboListSize);
    }
}
