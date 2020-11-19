package com.pyropy.work24.views.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pyropy.work24.views.fragments.ProfileFragment;
import com.pyropy.work24.views.fragments.SavedGigFragment;
import com.pyropy.work24.views.fragments.SavedJobsFragment;

public class FavoritesPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public FavoritesPagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

       switch (position){
           case 0:
               return new ProfileFragment();
           case 1:
               return new SavedJobsFragment();
           case 2:
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
