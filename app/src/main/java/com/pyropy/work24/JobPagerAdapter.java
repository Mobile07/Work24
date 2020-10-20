package com.pyropy.work24;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class JobPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public JobPagerAdapter(FragmentManager fm, int numOfTabs) {


        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MyJobsFragment();
            case 1:
                return new SavedGigFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

