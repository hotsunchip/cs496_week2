package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class ByeData {
    @SerializedName("codenum")
    String codenum;
    @SerializedName("userid")
    String userid;

    public ByeData(String codenum, String userid) {
        this.codenum = codenum;
        this.userid = userid;
    }
}