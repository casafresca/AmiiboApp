package com.example.amiiboapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        int favouritesCount = ((MainActivity)getActivity()).favouritesCount;
        TextView favourites = (TextView) view.findViewById(R.id.favouritesTextView);

        favourites.setText("You have " + favouritesCount + " Amiibos in favourites");

        return view;
    }
}
