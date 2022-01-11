package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class HeartupData {
    @SerializedName("codenum")
    String codenum;
    @SerializedName("userid")
    String userid;

    public HeartupData(String codenum,String userid) {
        this.codenum = codenum;
        this.userid = userid;
    }
}