package com.pyropy.work24.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.pyropy.work24.R;
import com.pyropy.work24.views.adapters.LearnViewPagerAdapter;

public class LearnFragment extends Fragment {

    ViewGroup mViewGroup;
    private TabLayout mTabLayout;
    ViewPager mViewPager;
    LearnViewPagerAdapter mLearnViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        initScreenComponents();

        addFragsToAdapter();

        prepDisplay();

        return mViewGroup;
    }

    private void initScreenComponents() {
        mViewPager = mViewGroup.findViewById(R.id.viewpager);
        mTabLayout = mViewGroup.findViewById(R.id.tablayout);
        mLearnViewPagerAdapter = new LearnViewPagerAdapter(getChildFragmentManager());
    }

    private void addFragsToAdapter() {
        mLearnViewPagerAdapter.addFragment(new CoursesFragment(),"Videos");
        mLearnViewPagerAdapter.addFragment(new ResourcesFragment(),"Ebooks");
        mLearnViewPagerAdapter.addFragment(new UserFragment(),"Users");
    }

    private void prepDisplay() {
        //set adapter to viewpager
        mViewPager.setAdapter(mLearnViewPagerAdapter);
        //add viewpager to tablayout
        mTabLayout.setupWithViewPager(mViewPager);
    }

}