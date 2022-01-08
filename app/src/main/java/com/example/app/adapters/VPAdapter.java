package com.example.app.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();

    public VPAdapter(@NonNull FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        items = fragments;

//        itext.add("Friends");
//        itext.add("BarCode");
//        itext.add("Histories");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position){
//        return itext.get(position);
//    }
}
