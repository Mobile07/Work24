package com.pyropy.work24.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

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
import com.pyropy.work24.views.activities.InsertGig;


public class ProfileFragment extends Fragment {

    private ViewGroup mGroup;
    private CardView mAddGigBtn;
    private FirebaseUtil mUtil;
    private TextView userId, userPhone, userMail, userType, profileTitle;

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

        initComponents(mGroup);
        populateUserData();

        return mGroup;
    }

    private void initComponents(ViewGroup view) {

        userId = view.findViewById(R.id.tv_userId);
        userPhone = view.findViewById(R.id.tv_phone);
        userMail = view.findViewById(R.id.userEmail);
        userType = view.findViewById(R.id.userType);
        profileTitle = view.findViewById(R.id.profileTitle);

        mUtil = FirebaseUtil.getInstances(getContext());
    }

    private void populateUserData() {
        Query userDetails = mUtil.mDbRef.child("Users").orderByChild("phone").equalTo(mUtil.mAuthPhone);
        userDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String firstName = null;
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    UsersModel uModel = singleSnapshot.getValue(UsersModel.class);
                    userId.setText(uModel.fullname);
                    userPhone.setText(uModel.phone);
                    userMail.setText(uModel.email);
                    userType.setText(uModel.userType);
                    fixProfile(uModel.userType);
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

    private void fixProfile(String userType) {
        switch (userType){
            case "Freelancer":
                profileTitle.setText("My Gigs");
                break;
            case "Buyer":
                profileTitle.setText("My Jobs");
                break;
            case "Instructor":
                profileTitle.setText("My Courses");
                break;
            default:
                profileTitle.setText("Favourites");
                break;
        }
    }
}