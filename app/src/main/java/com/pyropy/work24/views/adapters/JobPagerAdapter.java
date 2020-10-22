package com.pyropy.work24.views.adapters;

import com.pyropy.work24.views.fragments.MyJobsFragment;
import com.pyropy.work24.views.fragments.SavedGigFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class JobPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mListFragmentTitles = new ArrayList<>();

    public JobPagerAdapter(FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mListFragmentTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String tabTitle){
        mFragmentList.add(fragment);
        mListFragmentTitles.add(tabTitle);
    }
}

