package com.example.app.data;

import com.google.gson.annotations.SerializedName;

public class BarcodeData {
    @SerializedName("codenum")
    String codenum;
    @SerializedName("userid")
    String userid;

    public BarcodeData(String codenum,String userid) {
        this.codenum = codenum;
        this.userid = userid;
    }
}