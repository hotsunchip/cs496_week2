package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class BarcodeData {

    @SerializedName("userid")
    String userid;

    @SerializedName("codenum")
    String codenum;

    public BarcodeData(String userid, String codenum) {
        this.codenum = codenum;
        this.userid = userid;
    }
}