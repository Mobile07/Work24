package com.pyropy.work24.views.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pyropy.work24.R;
import com.pyropy.work24.database.FirebaseUtil;
import com.pyropy.work24.model.UsersModel;


public class DashboardFragment extends Fragment {


    private ViewGroup mView;
    private RecyclerView verticalRecyclerView;
    private FirebaseUtil mUtil;
    private TextView userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);
        verticalRecyclerView = mView.findViewById(R.id.trending_courses);

        initComponents(mView);
        populateUserData();

        //make vertical adapter for recyclerview
        return mView;
    }

    private void initComponents(ViewGroup view) {
        userId = (TextView) view.findViewById(R.id.userId);
        mUtil = FirebaseUtil.getInstances(getContext());
    }

    private void populateUserData() {
        Query userDetails = mUtil.mDbRef.child("Users").orderByKey().equalTo(mUtil.mAuthEmail);
        userDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstName = null;
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    UsersModel uModel = singleSnapshot.getValue(UsersModel.class);
                    String fullNames[] = uModel.fullname.split(" ");
                    firstName = fullNames[0];
                    userId.setText(firstName);
                }
//                if (dataSnapshot.exists()){
//                    UsersModel uUsersModel =
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}