package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("userid")
    String userid;

    @SerializedName("userpw")
    String userpw;

    public LoginData(String userid, String userpw) {
        this.userid = userid;
        this.userid = userpw;
    }
}