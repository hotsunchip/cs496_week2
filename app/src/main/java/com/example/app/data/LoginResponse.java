package com.example.app.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class LoginResponse extends ResponseBody {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("userId")
    private int userId;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return null;
    }

    @NonNull
    @Override
    public BufferedSource source() {
        return null;
    }
}