package com.example.app.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.APIService;
import com.example.app.R;
import com.example.app.RetrofitClient;
import com.example.app.adapters.CarouselViewAdapter;
import com.example.app.data.BookInfo;
import com.example.app.data.JoinData;
import com.example.app.data.JoinResponse;
import com.example.app.data.LoginData;
import com.example.app.data.LoginResponse;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mIdView;
    private EditText mPasswordView;
    private EditText mNameView;
    private TextView mLoginJoinTxtBtn;
    private ImageButton mLoginButton;
    private ProgressBar mProgressView;
    private APIService.ApiService service;
    private boolean mLoginMode;
    private String mname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

//        Intent intent = getIntent();

        // setting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mLoginMode = true;
        mNameView = (EditText) findViewById(R.id.in_name);
        mIdView = (AutoCompleteTextView) findViewById(R.id.in_email);
        mPasswordView = (EditText) findViewById(R.id.in_password);
        mLoginButton = (ImageButton) findViewById(R.id.btn_next);
        mProgressView = (ProgressBar) findViewById(R.id.progress);
        mLoginJoinTxtBtn = (TextView) findViewById(R.id.login_mode);

        service = RetrofitClient.getClient().create(APIService.ApiService.class);
        mLoginJoinTxtBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginMode = !mLoginMode;
                setLoginJoinMode();
            }
        });
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLoginMode) {
                    attemptLogin();
                } else {
                    attemptJoin();
                }
            }
        });

        setLoginJoinMode();
        if (bringPrevLogin()) moveMain();
    }

    private void attemptLogin() {
        mIdView.setError(null);
        mPasswordView.setError(null);

        String email = mIdView.getText().toString();
        mname = email.toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mIdView.setError("비밀번호를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }
        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mIdView.setError("아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(email));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        Call<LoginResponse> call_login = service.userLoginCheck(data);
        call_login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    String codenum = response.body().getCodenum();
                    String title = response.body().getTitle();
                    String author = response.body().getAuthor();
                    String price = response.body().getPrice();
                    String review = response.body().getReview();
                    String love = response.body().getLove();
                    String imgbook = response.body().getImgbook();
                    String payone = response.body().getPayone();
                    String paytwo = response.body().getPaytwo();
                    String paythree = response.body().getPaythree();
                    String payfour = response.body().getPayfour();
                    String aboutbook = response.body().getAboutbook();

                    String result = "로그인에 성공하였습니다!";
                    MainActivity.userid = response.body().getUserid();
                    MainActivity.userid =
                            CarouselViewAdapter.userid = response.body().getUserid();
                    Log.v("", "result = " + result);
                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    showProgress(false);
                    moveMain();
                } else {
                    String result = "로그인에 실패하였습니다.";
                    Log.v("", "error = " + result);
                    Toast.makeText(LoginActivity.this, "error = " + result, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생하였습니다.", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생하였습니다.", t.getMessage());
                showProgress(false);
            }
        });
    }

    private void attemptJoin() {
        mNameView.setError(null);
        mIdView.setError(null);
        mPasswordView.setError(null);

        String name = mNameView.getText().toString();
        String email = mIdView.getText().toString();
        String password = mPasswordView.getText().toString();

        mname = email.toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mIdView.setError("비밀번호를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mIdView.setError("아이디를 입력해주세요.");
            focusView = mIdView;
            cancel = true;
        }

        // 이름의 유효성 검사
        if (name.isEmpty()) {
            mNameView.setError("이름을 입력해주세요.");
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            String userid = email;
            String userpw = password;
            String nickname = name;
            MainActivity.userid = userid;
            startJoin(new JoinData(userid, userpw, nickname));
            showProgress(true);
        }
    }

    private void startJoin(JoinData data) {
        Call<JoinResponse> call_join = service.userJoin(data);
        call_join.enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                int code = response.body().getCode();
                String message = response.body().getMessage();
                if (response.isSuccessful()) {
                    String result = "회원가입에 성공하였습니다!";
                    Log.v("", "result = " + result);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    showProgress(false);
                    moveMain();
                } else {
                    String result = "회원가입에 실패하였습니다!";
                    Log.v("", "error = " + result);
                    Toast.makeText(LoginActivity.this, "error = " + result, Toast.LENGTH_SHORT).show();
                }
                if (response.body().getCode() == 200) {
                    moveMain();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                showProgress(false);
            }
        });
    }


    private void setLoginJoinMode() {
        if (mLoginMode) {
            mNameView.setVisibility(View.GONE);
            mLoginJoinTxtBtn.setText("회원가입");
        } else {
            mNameView.setVisibility(View.VISIBLE);
            mLoginJoinTxtBtn.setText("로그인");
        }
    }

    private void moveMain() {
        MainActivity.userid = mname;
        Intent startApp = new Intent(this, MainActivity.class);
        startActivity(startApp);
        finish();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void deletePrevLogin() {
        String fileName = "user.json";
        File file = new File(getFilesDir(), fileName);
        file.delete();
    }

    private boolean bringPrevLogin() {
        JSONObject jo = null;
        String fileName = "user.json";
        FileInputStream fis = null;
        boolean loginBefore = false;
        // 최초 설치 시 파일 초기화
//        File file = new File(this.getActivity().getFilesDir(), fileName);
//        file.delete();
        try {
            fis = openFileInput(fileName);
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
                loginBefore = true;
            }
        } catch (FileNotFoundException | JSONException e) {
            e.printStackTrace();
            JsonRead jr = new JsonRead();
            jo = jr.reading(this, "user.json");
        }

        //bookList 초기화
        JSONArray ja = null;
        try {
            ja = jo.getJSONArray("Books");
            MainActivity.userid = jo.getString("UserId");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ja.length(); i++) {
            JSONObject innerJSONObject = null;
            try {
                innerJSONObject = ja.getJSONObject(i);
                if (innerJSONObject != null) {
                    BookInfo bi = new BookInfo();

                    bi.setBookId(innerJSONObject.getString("codenum"));
                    bi.setBookTitle(innerJSONObject.getString("title"));
                    bi.setBookAuthor(innerJSONObject.getString("author"));
                    bi.setBookPrice(innerJSONObject.getString("price"));
                    bi.setBookPoint(innerJSONObject.getString("review"));
                    bi.setBookLove(innerJSONObject.getString("love"));
                    bi.setBookImg(innerJSONObject.getString("img"));
                    bi.setBookUrl1(innerJSONObject.getString("payone"));
                    bi.setBookUrl2(innerJSONObject.getString("paytwo"));
                    bi.setBookUrl3(innerJSONObject.getString("paythree"));
                    bi.setBookUrl4(innerJSONObject.getString("payfour"));
                    bi.setBookExplain(innerJSONObject.getString("aboutbook"));

                    MainActivity.bookList.add(0, bi);
                }
            } catch (JSONException e) {
            }
        }
        return loginBefore;
    }
}