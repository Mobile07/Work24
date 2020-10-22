package com.pyropy.work24.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.pyropy.work24.R;
import com.pyropy.work24.views.adapters.JobPagerAdapter;
import com.pyropy.work24.views.fragments.MyJobsFragment;
import com.pyropy.work24.views.fragments.SavedJobsFragment;

public class MyJobScreen extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private JobPagerAdapter mJobPagerAdapter;
    private static final String TAG = "MyGigScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job_screen);

        initComponents();

        addFragToAdapter();

        prepDisplay();

    }

    private void initComponents() {
        mTabLayout = findViewById(R.id.myjob_tab_bar);
        mViewPager = findViewById(R.id.myjob_viewpager);

        mJobPagerAdapter = new JobPagerAdapter(getSupportFragmentManager());
    }

    private void addFragToAdapter() {
        mJobPagerAdapter.addFragment(new MyJobsFragment(), getString(R.string.job_tab_title));
        mJobPagerAdapter.addFragment(new SavedJobsFragment(), getString(R.string.saved_job_tab_title));
        Log.d(TAG,"Fragments added to Adapter.");
    }

    private void prepDisplay() {
        mViewPager.setAdapter(mJobPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        Log.d(TAG, "Display ready and launched");
    }
}
