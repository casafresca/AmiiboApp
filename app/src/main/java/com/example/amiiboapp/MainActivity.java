package com.example.amiiboapp;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;



    List<Amiibo> listAmiibo;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add action
            }
        });

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

        // Bottom Menu Navigation
        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setSelectedItemId(R.id.home);
        botNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.collectionPage:
                        startActivity(new Intent(getApplicationContext(),CollectionPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.collectionStats:
                        startActivity(new Intent(getApplicationContext(),CollectionsStatsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


       // listAmiibo = new ArrayList<>();
        // must add amiibo to the array list
        //RecyclerView myrv = findViewById(R.id.recyclerview_id);
        //RecycleViewAdapter myAdapter = new RecycleViewAdapter(this, listAmiibo);
       // myrv.setLayoutManager(new GridLayoutManager(this, 3));
       // myrv.setAdapter(myAdapter);

        //imageView = findViewById(R.id.imageView);
    }

    private void parseJSON(){
        String url = "https://www.amiiboapi.com/api/amiibo/";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("amiibo");
                            //loop to get amiibo data from api
                            for(int i = 0; i < jsonArray.length(); i ++){
                                JSONObject amiibo = jsonArray.getJSONObject(i);
                                String name = amiibo.getString("character");
                                String imageUrl = amiibo.getString("image");
                                String otherInfo = amiibo.getString("gameSeries");

                                mExampleList.add(new ExampleItem(imageUrl,name,otherInfo));
                            }

                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // must fix these three commented lines of code to get the search bar to work
        //MenuItem menuItem = menu.findItem(R.id.search_bar);
        //SearchView searchView = (SearchView) menuItem.getActionView();
        //searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<ExampleItem> newList = new ArrayList<>();
        String name = "";

        for(int i = 0; i < mExampleList.size(); i++){
            name = mExampleList.get(i).getmAmiiboName();
            if(name.toLowerCase().contains(userInput)){
                newList.add(findName(name));
            }

        }
        mExampleAdapter.updateList(newList);
        return true;
    }

    public ExampleItem findName(String name){
        for(ExampleItem exampleItem: mExampleList){
            if(exampleItem.getmAmiiboName().equals(name)){
                return exampleItem;
            }
        }
        return  null;
    }
}
