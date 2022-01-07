package com.example.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.example.app.R;
import com.example.app.activities.MainActivity;

public class Fragment1 extends Fragment{
    // fields
    public static TextView tvServerResponse;

    // Required empty public constructor
    public Fragment1() {}

    public static Fragment1 newInstance() {
        Fragment1 fragment = new Fragment1();
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
        View view = inflater.inflate(R.layout.fragment_1, container, false) ;


        tvServerResponse = view.findViewById(R.id.textView);
        Button contactServerButton = view.findViewById(R.id.button);
        contactServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.HttpGetRequest request = new MainActivity.HttpGetRequest();
                request.execute();
            }
        });

        return view;
    }
}