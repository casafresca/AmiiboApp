package com.example.amiiboapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionPage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CollectionPageRecyclerViewAdapter adapter;
    private ArrayList<CollectionPageListItem> mCollectionList = new ArrayList<>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_page);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRequestQueue = Volley.newRequestQueue(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
}
