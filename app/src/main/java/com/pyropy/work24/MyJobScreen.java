package com.pyropy.work24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyJobScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job_screen);


        TabLayout tabLayout = findViewById(R.id.myjob_tab_bar);
        TabItem tabMy_Gigs = findViewById(R.id.tabMy_Jobs);
        TabItem tabSaved_Jobs = findViewById(R.id.tabSaved_Gigs);
        final ViewPager viewPager = findViewById(R.id.myjob_viewpager);

        JobPagerAdapter jobPagerAdapter = new
                JobPagerAdapter(getSupportFragmentManager() ,
                tabLayout.getTabCount()) ;

        viewPager.setAdapter(jobPagerAdapter);

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
