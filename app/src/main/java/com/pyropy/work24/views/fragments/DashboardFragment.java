package com.pyropy.work24.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyropy.work24.R;



public class DashboardFragment extends Fragment {


    private ViewGroup mView;
    private RecyclerView verticalRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        verticalRecyclerView = mView.findViewById(R.id.trending_courses);

        //make vertical adapter for recyclerview
        return mView;
    }

}