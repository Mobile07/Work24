package com.pyropy.work24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyGigScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gig_screen);

        TabLayout tabLayout = findViewById(R.id.mygig_tab_bar);
        TabItem tabMy_Gigs = findViewById(R.id.tabMy_Gigs);
        TabItem tabSaved_Jobs = findViewById(R.id.tabSaved_Jobs);
        final ViewPager viewPager = findViewById(R.id.mygig_viewpager);

        GigPagerAdapter gigPagerAdapter = new
                GigPagerAdapter(getSupportFragmentManager() ,
                        tabLayout.getTabCount()) ;

        viewPager.setAdapter(gigPagerAdapter);

        //to change tabviews when tab is selected or clicked
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}