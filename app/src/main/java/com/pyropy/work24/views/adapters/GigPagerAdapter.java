package com.pyropy.work24.views.adapters;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class GigPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mListFragments = new ArrayList<>();
    private final List<String> mListFragmentTitles = new ArrayList<>();

    public GigPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mListFragmentTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        mListFragments.add(fragment);
        mListFragmentTitles.add(title);
    }
}
