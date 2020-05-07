package com.example.amiiboapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CollectionPageRecyclerViewAdapter adapter;
    private ArrayList<CollectionPageListItem> mCollectionList = new ArrayList<>();
    private RequestQueue mRequestQueue;
    private SharedPrefTheme sharedPrefTheme;
   // private CollectionDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //myDB = new CollectionDatabaseHelper(this);
        // Light vs Dark Mode
        sharedPrefTheme = new SharedPrefTheme(this);
        if(sharedPrefTheme.loadNightModeState() == true){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRequestQueue = Volley.newRequestQueue(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Bottom Menu Navigation
        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setSelectedItemId(R.id.collectionPage);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.collectionPage:
                        return true;
                    case R.id.collectionStats:
                        startActivity(new Intent(getApplicationContext(),CollectionsStatsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        getAmiiboInfo();

    }

    private void getAmiiboInfo() {
        String url = "https://www.amiiboapi.com/api/amiibo/?amiiboSeries=Super%20Mario%20Bros.";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("amiibo");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject amiibo = jsonArray.getJSONObject(i);

                                String amiiboName = amiibo.getString("name");
                                String amiiboImageUrl = amiibo.getString("image");

                                mCollectionList.add(new CollectionPageListItem(amiiboImageUrl, amiiboName));
                            }

                            adapter = new CollectionPageRecyclerViewAdapter(CollectionPage.this, mCollectionList);
                            mRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    public void collectionOnClick(View view) {
        /* do later
        int amiiboListSize = mCollectionList.size();

        //add amiibo

        mRecyclerView.getAdapter().notifyItemInserted(amiiboListSize);
        mRecyclerView.smoothScrollToPosition(amiiboListSize);
        */
    }

//    public  void AddData() {
////
////        mRecyclerView.setOnClickListener(
////                new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////
////                        boolean isInserted = myDB.insertData(mCollectionList.get(0).getAmiiboName(),
////                                mCollectionList.get(0).getDateBought(),
////                                mCollectionList.get(0).getPrice() );
////                        if(isInserted == true)
////                            Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
////                        else
////                            Toast.makeText(getApplicationContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
////                    }
////                }
////        );
////    }
}
