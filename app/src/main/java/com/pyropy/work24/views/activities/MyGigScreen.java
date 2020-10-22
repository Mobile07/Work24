package com.pyropy.work24.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pyropy.work24.views.fragments.MyGigsFragment;
import com.pyropy.work24.R;
import com.pyropy.work24.views.fragments.SavedGigFragment;
import com.pyropy.work24.views.adapters.GigPagerAdapter;

public class MyGigScreen extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private static final String TAG = "MyGigScreen";
    private GigPagerAdapter mGigPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gig_screen);

        initComponents();

        addFragToAdapter();

        prepDisplay();


    }


    private void initComponents() {
        mTabLayout = findViewById(R.id.mygig_tab_bar);
        mViewPager = findViewById(R.id.mygig_viewpager);

        mGigPagerAdapter = new GigPagerAdapter(getSupportFragmentManager());
        Log.d(TAG,"Components Initialized and ViewPagerAdapter Created.");
    }

    private void addFragToAdapter() {
        mGigPagerAdapter.addFragment(new MyGigsFragment(),getString(R.string.gig_tab_title));
        mGigPagerAdapter.addFragment(new SavedGigFragment(),getString(R.string.saved_gig_tab_title));
        Log.d(TAG,"Fragments added to Adapter.");
    }

    private void prepDisplay() {
        //set adapter to viewpager
        mViewPager.setAdapter(mGigPagerAdapter);

        //add viewpager to tablayout
        mTabLayout.setupWithViewPager(mViewPager);
        Log.d(TAG,"Display ready and launched.");
    }
}