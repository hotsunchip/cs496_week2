package com.example.app.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app.R;
import com.example.app.adapters.VPAdapter;
import com.example.app.fragments.Fragment2;
import com.example.app.fragments.Fragment_empty;
import com.example.app.fragments.Fragment_empty3;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.util.ArrayList;


public class ScannerActivity extends AppCompatActivity { //implements DecoratedBarcodeView.TorchListener{
    // fields
    private static Context mContext;
    private long backKeyPressedTime = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Intent intent = getIntent();
        mContext = this;

        // setting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ViewPager vp = findViewById(R.id.viewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_empty());
        fragments.add(new Fragment2());
        fragments.add(new Fragment_empty3());
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        // 갤러리를 위한 json file 받기
        // AssetManager assetManager = getResources().getAssets();

        // connect view pager with tab layout
        TabLayout tab = findViewById(R.id.tab);

        tab.setupWithViewPager(vp);

        tab.getTabAt(0).setIcon(R.drawable.ic_friends);
        tab.getTabAt(2).setIcon(R.drawable.ic_histories);

        tab.getTabAt(1).select();

        FloatingActionButton fab = findViewById(R.id.infoApp);
        fab.setOnClickListener(new View.OnClickListener() {
            // floating button을 클릭했을 때
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder adb = new AlertDialog.Builder(mContext);

                View view2 = LayoutInflater.from(mContext)
                        .inflate(R.layout.info_app, null);
                adb.setView(view2);

                AlertDialog finalDialog = adb.create();

                finalDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                finalDialog.setCancelable(true);
                finalDialog.setView(view2, 0, 0, 0, 0);

                finalDialog.show();
            }
        });
    }

    //어플리케이션 종료하는 버튼
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.show();
            return;
        } else {
            finishAffinity();
            toast.cancel();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //액티비티를 종료할 때 애니메이션 없애기
        overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);
        super.onResume();
    }

}
