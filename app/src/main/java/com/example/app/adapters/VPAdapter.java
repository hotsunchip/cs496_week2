package com.example.app.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> items;

    public VPAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
        super(fragmentActivity);
        items = fragments;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
