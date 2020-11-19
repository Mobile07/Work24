package com.pyropy.work24.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pyropy.work24.R;
import com.pyropy.work24.views.adapters.FavoritesPagerAdapter;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        TabLayout tabLayout = findViewById(R.id.tabBar);
        TabItem tabUser = findViewById(R.id.user);
        TabItem tabJobs = findViewById(R.id.saved_jobs);
        TabItem tabGigs = findViewById(R.id.saved_gigs);
        ViewPager viewpager = findViewById(R.id.ViewPager);

        FavoritesPagerAdapter pagerAdapter = new
                FavoritesPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewpager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition() );
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