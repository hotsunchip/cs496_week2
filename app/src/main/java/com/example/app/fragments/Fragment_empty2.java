package com.example.app.fragments;

import android.app.Activity;
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

public class Fragment_empty2 extends Fragment {
    // fields

    // Required empty public constructor
    public Fragment_empty2() {
    }

    public static Fragment_empty2 newInstance() {
        Fragment_empty2 fragment = new Fragment_empty2();
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
        View view = inflater.inflate(R.layout.fragment_empty2, container, false);
        return view;
    }
}