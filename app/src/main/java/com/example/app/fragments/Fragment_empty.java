package com.example.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import com.example.app.R;
import com.example.app.activities.MainActivity;
import com.example.app.activities.ScannerActivity;
import com.google.zxing.integration.android.IntentIntegrator;

public class Fragment_empty extends Fragment{
    // fields

    // Required empty public constructor
    public Fragment_empty() {}

    public static Fragment_empty newInstance() {
        Fragment_empty fragment = new Fragment_empty();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
//        fillFragment()
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            MainActivity.setTab(0);
            getActivity().finish();
        } else {

        }
    }
}