package com.pyropy.work24;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class GigPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public GigPagerAdapter(FragmentManager fm, int numOfTabs) {


        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
             return new MyGigsFragment();
            case 1:
             return new SavedJobsFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
