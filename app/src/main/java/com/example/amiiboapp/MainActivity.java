package com.example.amiiboapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnitemClickListener {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;
    final int REQUEST_CODE = 999;
    private SharedPrefTheme sharedPrefTheme;

    DatabaseHelper myDB;
    Bitmap bitmap;
    URL urlImage;
    ArrayList<Bitmap> bitmaps;
    List<Amiibo> listAmiibo;
    ImageView imageView;

    public int favouritesCount = 0;

    //test comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DatabaseHelper(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        EditText targetEditText = (EditText)findViewById(R.id.search_bar);
        targetEditText.setOnEditorActionListener(new DoneOnEditorActionListener());
        parseJSON();

        //code for creating the context menus
        registerForContextMenu(mRecyclerView);

        // Code pushing all amiibos into the database 
        String image = "";
        for(ExampleItem item: mExampleList){

            image = item.getmImageUrl();
            try {
                urlImage = new URL(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                bitmap = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
                bitmaps.add(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
//                Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
//
//            myDB.insertData(bitmapToByte(bitmap), item.getmAmiiboName() , item.getmOtherInfo());



        EditText editText = findViewById(R.id.search_bar);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

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

    private void filter (String text){
        ArrayList<ExampleItem> fillteredList = new ArrayList<>();
        for(ExampleItem item : mExampleList){
            if(item.getmAmiiboName().toLowerCase().contains(text.toLowerCase())){
                fillteredList.add(item);
            }
        }
        mExampleAdapter.updateList(fillteredList);
    }

    //method to make a JSON call to the AmiiboApi, goes through their entire database of characters and gets their basic data
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
                                String name = amiibo.getString("name");
                                String imageUrl = amiibo.getString("image");
                                String otherInfo = amiibo.getString("amiiboSeries");

                                mExampleList.add(new ExampleItem(imageUrl,name,otherInfo));
                            }

                            mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException  e) {
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

    private byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte [] byteArray = stream.toByteArray();
        return byteArray;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
            else{
                Toast.makeText(getApplicationContext(), "You do not have permission to access the file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_aboutUs:
                startActivity(new Intent(getApplicationContext(),AboutUsActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position) {
//        for(ExampleItem item: mExampleList){
//
//            String image = mExampleList.get(position).getmImageUrl();
//            try {
//                urlImage = new URL(image);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//            try {
//                bitmap = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{
//             Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
//          myDB.insertData(bitmapToByte(bitmap), mExampleList.get(position).getmAmiiboName() , mExampleList.get(position).getmOtherInfo());
//        }
//
//        Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
//        //attempted to change the color of the background
//        ImageView imageView = (ImageView) findViewById(R.id.image_view);
//        imageView.setBackgroundColor(Color.parseColor("#FF0000"));
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal){
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
    //inflating the context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        menu.setHeaderTitle("Select Action");

    }
    // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //this method handles click events in the context menu
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId() == R.id.add_to_favorites){
            //Toast.makeText(this,"Favorite selected",Toast.LENGTH_SHORT).show();
            displayMessage("Added to Favorites");

            favouritesCount++;

            return true;
        }else if(item.getItemId() == R.id.remove_from_favorites){
            //Toast.makeText(this,"Remove from favorites selected",Toast.LENGTH_SHORT).show();;
            displayMessage("Removed from Favorites");

            favouritesCount--;

        }else
            return false;
        return super.onContextItemSelected(item);
    }

    public void displayMessage(String message){
        Snackbar.make(findViewById(R.id.mCardView), message, Snackbar.LENGTH_SHORT).show();//id .rootView 12:45
    }

    class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        }
    }
}
