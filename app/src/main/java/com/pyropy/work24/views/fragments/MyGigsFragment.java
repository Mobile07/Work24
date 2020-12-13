package com.pyropy.work24.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.database.GigHelper;
import com.pyropy.work24.model.GigModel;
import com.pyropy.work24.views.adapters.GeneralGigAdapter;
import com.pyropy.work24.views.adapters.MyGigsAdapter;

import java.util.ArrayList;

public class MyGigsFragment extends Fragment {
    RecyclerView recview;
    DatabaseReference mDbref;
    GeneralGigAdapter adapter ;
    View GigView;
    FirebaseUtil mUtil;

    public MyGigsFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        GigView = inflater.inflate(R.layout.fragment_my_gigs, container, false);


        recview = (RecyclerView) GigView.findViewById(R.id.recview);
        setUpRecyclerView();
        return GigView;

    }
    public void setUpRecyclerView(){

        Query query= FirebaseDatabase.getInstance().getReference("Gigs");

        FirebaseRecyclerOptions<GigHelper> options = new
                FirebaseRecyclerOptions.Builder<GigHelper>()
                .setQuery(query, GigHelper.class)
                .build();

        adapter = new GeneralGigAdapter(options);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        recview.setAdapter(adapter);

    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}