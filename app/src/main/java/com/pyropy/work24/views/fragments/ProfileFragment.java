package com.pyropy.work24.views.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.pyropy.work24.views.adapters.MyCoursesAdapter;
import com.pyropy.work24.views.adapters.MyGigsAdapter;


public class ProfileFragment extends Fragment {

    private ViewGroup mGroup;
    private CardView mAddGigBtn;
    private FirebaseUtil mUtil;
    private TextView userId, userPhone, userMail, userType, profileTitle;
    private RecyclerView userGigs, userCourses;
    private LinearLayoutManager mRvManager;
    MyGigsAdapter mMyGigsAdapter;
    MyCoursesAdapter mMyCoursesAdapter;

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


        return mGroup;
    }

    private void initComponents(ViewGroup view) {

        userId = view.findViewById(R.id.tv_userId);
        userPhone = view.findViewById(R.id.tv_phone);
        userMail = view.findViewById(R.id.userEmail);
        userType = view.findViewById(R.id.userType);
        profileTitle = view.findViewById(R.id.profileTitle);
        userGigs = view.findViewById(R.id.user_gigs);
        //userCourses = view.findViewById(R.id.user_courses);

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
                    userId.setText(uModel.fullname);
                    userPhone.setText(uModel.phone);
                    userMail.setText(uModel.email);
                    userType.setText(uModel.usertype);
                    fixProfile(uModel.usertype);
                }

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
                userGigs.setVisibility(RecyclerView.VISIBLE);
               populateMyGigs();
                Log.d("STATUS", "MADE IT HERE");
                //populateMyGigs();
                break;
            case "Buyer":
                profileTitle.setText("My Jobs");
                break;
            case "Instructor":
                profileTitle.setText("My Courses");
                //userCourses.setVisibility(RecyclerView.VISIBLE);
                populateMyCourses();
                break;
            default:
                profileTitle.setText("Favourites");
                break;
        }
    }

    private void populateMyCourses() {

    }

    private void populateMyGigs() {
        mRvManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        userGigs.setLayoutManager(mRvManager);
        mMyGigsAdapter = new MyGigsAdapter(getContext());
        userGigs.setAdapter(mMyGigsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        populateUserData();
    }
}