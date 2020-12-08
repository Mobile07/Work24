package com.pyropy.work24.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.pyropy.work24.R;
import com.pyropy.work24.views.adapters.JobAndGigViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class JobsFragment extends Fragment {

    private TabLayout mTabLayout;
    ViewPager mViewPager;
    JobAndGigViewPagerAdapter mJobGigAdapter;
    ViewGroup mViewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewGroup = (ViewGroup) inflater.inflate(R.layout.jobs_fragment, container, false);

        initScreenComponents();

        addFragsToAdapter();

        prepDisplay();

        return mViewGroup;
    }

    private void initScreenComponents() {
        mViewPager = mViewGroup.findViewById(R.id.jobs_pager);
        mTabLayout = mViewGroup.findViewById(R.id.job_tabs);
        mJobGigAdapter = new JobAndGigViewPagerAdapter(getChildFragmentManager());
    }

    private void addFragsToAdapter() {
        mJobGigAdapter.addFragment(new MyGigsFragment(), "my gigs");
        mJobGigAdapter.addFragment(new MyJobsFragment(), "my jobs");
        mJobGigAdapter.addFragment(new SavedJobsFragment(), "Favorites");
    }

    private void prepDisplay() {
        mViewPager.setAdapter(mJobGigAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
