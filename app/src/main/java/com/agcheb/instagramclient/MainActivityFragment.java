package com.agcheb.instagramclient;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    RecyclerView rv;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



         View rootview = inflater.inflate(R.layout.fragment_main, container, false);

         List<PhotoCard> modelPhotos = new ArrayList<>();
         int imgid;
        for (int i = 0; i < 6; i++) {
            if(i%2==0)imgid = R.drawable.snow;
            else imgid = R.drawable.sun;
            modelPhotos.add(new PhotoCard(imgid));
        }


        rv = rootview.findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rv.setLayoutManager(layoutManager);
        RVAdapter rvAdapter = new RVAdapter(modelPhotos);
        rv.setAdapter(rvAdapter);
        return rootview;
    }

}
