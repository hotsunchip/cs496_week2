package com.example.app.fragments;

import static com.example.app.activities.MainActivity.URL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.app.APIService;
import com.example.app.R;
import com.example.app.activities.MainActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment implements View.OnClickListener {
    // fields
    public static TextView tvServerResponse;
    private Button btn_get, btn_post, btn_delete, btn_update;
    private Retrofit retrofit;
    private APIService.ApiService service;

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

        firstInit();
        btn_get = (Button) view.findViewById(R.id.btn_get);
        btn_post = (Button) view.findViewById(R.id.btn_post);
        btn_delete = (Button) view.findViewById(R.id.btn_delete);
        btn_update = (Button) view.findViewById(R.id.btn_update);

        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
//        tvServerResponse = view.findViewById(R.id.textView);
//        Button contactServerButton = view.findViewById(R.id.button);
//        contactServerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.HttpGetRequest request = new MainActivity.HttpGetRequest();
//                request.execute();
//            }
//        });

        return view;
    }

    /**
     * Init
     */
    public void firstInit() {

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.ApiService.class);
    }

    /**
     * View.OnLongClickListener override method
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                Call<ResponseBody> call_get = service.getFunc("get data");
                call_get.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.v(MainActivity.TAG, "result = " + result);
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.v(MainActivity.TAG, "error = " + String.valueOf(response.code()));
                            Toast.makeText(getContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(MainActivity.TAG, "Fail");
                        Toast.makeText(getContext(), "Response Fail" + URL, Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_post:
                Call<ResponseBody> call_post = service.postFunc("post data");
                call_post.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.v(MainActivity.TAG, "result = " + result);
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.v(MainActivity.TAG, "error = " + String.valueOf(response.code()));
                            Toast.makeText(getContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(MainActivity.TAG, "Fail");
                        Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_update:
                Call<ResponseBody> call_put = service.putFunc("board", "put data");
                call_put.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.v(MainActivity.TAG, "result = " + result);
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.v(MainActivity.TAG, "error = " + String.valueOf(response.code()));
                            Toast.makeText(getContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(MainActivity.TAG, "Fail");
                        Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_delete:
                Call<ResponseBody> call_delete = service.deleteFunc("board");
                call_delete.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.v(MainActivity.TAG, "result = " + result);
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.v(MainActivity.TAG, "error = " + String.valueOf(response.code()));
                            Toast.makeText(getContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(MainActivity.TAG, "Fail");
                        Toast.makeText(getContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}