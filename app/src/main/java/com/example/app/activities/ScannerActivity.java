package com.example.app.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app.APIService;
import com.example.app.R;
import com.example.app.RetrofitClient;
import com.example.app.adapters.VPAdapter;
import com.example.app.data.BookInfo;
import com.example.app.fragments.Fragment2;
import com.example.app.fragments.Fragment3;
import com.example.app.fragments.Fragment_empty3;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class ScannerActivity extends AppCompatActivity { //implements DecoratedBarcodeView.TorchListener{
    // fields
    private static Context mContext;
    private long backKeyPressedTime = 0;
    private Toast toast;
    private TextView profileid;
    private TextView profilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Intent intent = getIntent();
        mContext = this;

        // setting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ViewPager2 vp = findViewById(R.id.viewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment2());
        fragments.add(new Fragment_empty3());
        VPAdapter adapter = new VPAdapter(this, fragments);
        vp.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        vp.setAdapter(adapter);
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                super.onPageSelected(pos);
                if (pos == 1) {
                    MainActivity.setPage(1);
                    finish();
                }
            }
        });

//        relativeLayout = (RelativeLayout) findViewById(R.id.content_main);
//        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
//            public void onSwipeTop() {
//                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//            }
//
//        });

        ImageButton profileBtn = findViewById(R.id.profile_btn);
        profileBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder adb = new AlertDialog.Builder(mContext);



                View view3 = LayoutInflater.from(mContext)
                        .inflate(R.layout.profile, null);
                profileid = (TextView) view3.findViewById(R.id.profile_id);
                profilename = (TextView) view3.findViewById(R.id.profile_name);
                profileid.setText(MainActivity.userid);
                adb.setView(view3);
                final TextView logout = (TextView) view3.findViewById(R.id.profile_logout);
                final TextView signout = (TextView) view3.findViewById(R.id.profile_signout);

                AlertDialog finalDialog = adb.create();

                finalDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                finalDialog.setCancelable(true);
                finalDialog.setView(view3, 0, 0, 0, 0);

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finalDialog.dismiss();

                        String fileName = "user.json";
                        File file = new File(getFilesDir(), fileName);
                        file.delete();

                        Intent loginIntent = new Intent(ScannerActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });
                signout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finalDialog.dismiss();
                        Intent loginIntent = new Intent(ScannerActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });
                finalDialog.show();
            }
        });
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

    public static void updateBookList(BookInfo book, int position) {
        JsonRead jr = new JsonRead();
        JSONObject jo = jr.reading(mContext, "user" +
                ".json");
        FileInputStream fis = null;
        String fileName = "" + MainActivity.userid + ".json";
        try {
            fis = mContext.openFileInput(fileName);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                String contents = stringBuilder.toString();
                jo = new JSONObject(contents);
            }
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray ja = jo.getJSONArray("Books");
            if (position < 0) {
                JSONObject jsonObject = new JSONObject();//배열 내에 들어갈 json
                jsonObject.put("codenum", book.getBookId());
                jsonObject.put("title", book.getBookTitle());
                jsonObject.put("author", book.getBookAuthor());
                jsonObject.put("price", book.getBookPrice());
                jsonObject.put("review", book.getBookPoint());
                jsonObject.put("love", "false");
                jsonObject.put("img", book.getBookImg());
                jsonObject.put("payone", book.getBookUrl1());
                jsonObject.put("paytwo", book.getBookUrl2());
                jsonObject.put("paythree", book.getBookUrl3());
                jsonObject.put("payfour", book.getBookUrl4());
                jsonObject.put("aboutbook", book.getBookExplain());
                ja.put(jsonObject);
                Log.e("newAdded", String.valueOf(ja.length()));
            } else {
                if (book != null) {
                    JSONObject jsonObject = new JSONObject();//배열 내에 들어갈 json
                    jsonObject.put("codenum", book.getBookId());
                    jsonObject.put("title", book.getBookTitle());
                    jsonObject.put("author", book.getBookAuthor());
                    jsonObject.put("price", book.getBookPrice());
                    jsonObject.put("review", book.getBookPoint());
                    jsonObject.put("love", "false");
                    jsonObject.put("img", book.getBookImg());
                    jsonObject.put("payone", book.getBookUrl1());
                    jsonObject.put("paytwo", book.getBookUrl2());
                    jsonObject.put("paythree", book.getBookUrl3());
                    jsonObject.put("payfour", book.getBookUrl4());
                    jsonObject.put("aboutbook", book.getBookExplain());
                    ja.put(ja.length() - position - 1, jsonObject);
                    Log.e("newAdded", String.valueOf(ja.length()));
                } else {
                    ja.remove(ja.length() - position - 1);
                    Log.e("deleted", String.valueOf(ja.length()));
                }
            }
            jo.put("Reviews", ja);

            try {
                FileOutputStream fileOutputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
                //Convert JSON String to Bytes and write() it
                fileOutputStream.write(jo.toString().getBytes());
                //Finally flush and close FileOutputStream
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
