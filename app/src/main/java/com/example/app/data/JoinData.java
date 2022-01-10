package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("userid")
    private String userid;

    @SerializedName("userpw")
    private String userpw;

    @SerializedName("nickname")
    private String nickname;

    public JoinData(String userid, String userpw, String nickname) {
        this.userid = userid;
        this.userpw = userpw;
        this.nickname = nickname;
    }
}