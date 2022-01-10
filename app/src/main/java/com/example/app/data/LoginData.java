package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userid")
    String userid;

    public LoginData(String userid) {
        this.userid = userid;
    }
}