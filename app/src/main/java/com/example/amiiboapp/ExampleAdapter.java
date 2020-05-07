package com.example.amiiboapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<ExampleItem> mExampleList;
    private FavDB favDB;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        favDB = new FavDB(mContext);
        //create table
        SharedPreferences prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if(firstStart){
            createTableOnFirstStart();
        }
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false); //change to the right xml file name
        return new ExampleViewHolder(v, mListener);
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        //final ExampleItem exampleItem = null;
        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String amiiboName = currentItem.getmAmiiboName();
        String otherInfo = currentItem.getmOtherInfo();

        holder.mTextViewAmiiboName.setText(amiiboName);
        holder.mTextViewOtherInfo.setText(otherInfo);

        Glide.with(mContext).load(imageUrl).fitCenter().into(holder.mImageView);

        //readCursorData(exampleItem,holder);
       // holder.mImageView.setImageResource(exampleItem.getmImageUrl());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    private OnitemClickListener mListener;
   //private View.OnClickListener mListener;
    //private AdapterView.OnItemLongClickListener mLongListener;

    public interface OnitemClickListener{
        void onItemClick(int position);
       // void onItemLongClick(int position);
    }
   // public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener){mLongListener = listener}
    public void setOnItemClickListener(OnitemClickListener listener){
        mListener = listener;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder  implements View.OnCreateContextMenuListener {
        public ImageView mImageView;
        public TextView mTextViewAmiiboName;
        public TextView mTextViewOtherInfo;
        CardView cardView;
        Button favBtn;


        public ExampleViewHolder(@NonNull View itemView, final OnitemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewAmiiboName = itemView.findViewById(R.id.text_view_amiibo);
            mTextViewOtherInfo = itemView.findViewById(R.id.text_view_info);
            favBtn = itemView.findViewById(R.id.favBtn);
            cardView = itemView.findViewById(R.id.mCardView);
            cardView.setOnCreateContextMenuListener(this);
//favorite button on hold
//            favBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    ExampleItem exampleItem = mExampleList.get(position);///////
//
//                    if(exampleItem.getmfavStatus().equals("0")){
//                        exampleItem.setMfavStatus("1");
//                        favDB.insertIntoTheDatabase(exampleItem.getmImageUrl(),exampleItem.getmAmiiboName(), exampleItem.getmOtherInfo(), exampleItem.getmfavStatus(),exampleItem.getKey_id());
//                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//                        //String imageUrl, String amiiboName, String otherInfo, String favStatus, String key_id
//                    }else{
//                        exampleItem.setMfavStatus("0");
//                        favDB.remove_fav(exampleItem.getKey_id());
//                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
//                    }
//                }
//            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
           // menu.add(this.getAdapterPosition(),121,0,"Add To Favorites");
           // menu.add(this.getAdapterPosition(),122,1,"Remove From Favorites");
            //DO NOT DELETE THIS METHOD

        }
    }


    public void updateList(List<ExampleItem> newList){
        mExampleList = new ArrayList<>();
        mExampleList.addAll(newList);
        notifyDataSetChanged();
    }

//    public void readCursorData(ExampleItem exampleItem, RecyclerView.ViewHolder viewHolder) {
//        Cursor cursor = favDB.read_all_data(exampleItem.getKey_id());
//        SQLiteDatabase db = favDB.getReadableDatabase();
//        try {
//                while (cursor.moveToNext()) {
//                    String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVORITE_STATUS));
//                    exampleItem.setMfavStatus(item_fav_status);
//
//                    //check fav status
//                    if (item_fav_status != null && item_fav_status.equals("1")) {
//                        viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24dp);
//                    } else if (item_fav_status != null && item_fav_status.equals("0")) {
//                        viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24dp);
//                    }
//                }
//        } finally{
//            if (cursor != null && cursor.isClosed())
//                    cursor.close();
//        }
//    }

}
