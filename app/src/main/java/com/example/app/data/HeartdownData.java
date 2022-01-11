package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class HeartdownData {
    @SerializedName("codenum")
    String codenum;
    @SerializedName("userid")
    String userid;

    public HeartdownData(String codenum,String userid) {
        this.codenum = codenum;
        this.userid = userid;
    }
}