package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.APIService;
import com.example.app.R;
import com.example.app.RetrofitClient;
import com.example.app.data.JoinData;
import com.example.app.data.JoinResponse;
import com.example.app.data.LoginData;
import com.example.app.data.LoginResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mNameView;
    private Button mEmailLoginButton;
    private Button mJoinButton;
    private ProgressBar mProgressView;
    private APIService.ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

        Intent intent = getIntent();

        // setting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mNameView = (EditText) findViewById(R.id.in_name);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.in_email);
        mPasswordView = (EditText) findViewById(R.id.in_password);
        mEmailLoginButton = (Button) findViewById(R.id.login_button);
        mJoinButton = (Button) findViewById(R.id.join_button);
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);

        service = RetrofitClient.getClient().create(APIService.ApiService.class);

        mEmailLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mJoinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });
//        moveMain();
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }
        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("아이디를 입력해주세요.");
            focusView = mEmailView;
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
        Call<LoginResponse> call_login = service.userLogin(data);
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
                    Log.v("", "result = " + result);
                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    showProgress(false);
                } else {
                    String result ="로그인에 실패하였습니다.";
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
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String name = mNameView.getText().toString();
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            mEmailView.setError("비밀번호를 입력해주세요.");
            focusView = mEmailView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = mPasswordView;
            cancel = true;
        }

        // 이메일의 유효성 검사
        if (email.isEmpty()) {
            mEmailView.setError("아이디를 입력해주세요.");
            focusView = mEmailView;
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

    private void moveMain(){
        MainActivity.setTab(1);
        finish();
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}