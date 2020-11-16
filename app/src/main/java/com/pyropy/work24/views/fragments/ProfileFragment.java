package com.pyropy.work24.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pyropy.work24.R;
import com.pyropy.work24.views.activities.InsertGig;


public class ProfileFragment extends Fragment {

    private ViewGroup mGroup;
    private CardView mAddGigBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mGroup = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        mAddGigBtn = (CardView) mGroup.findViewById(R.id.add_tile);
        mAddGigBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uIntent = new Intent(getActivity(), InsertGig.class);
                startActivity(uIntent);
            }
        });
        return mGroup;
    }
}