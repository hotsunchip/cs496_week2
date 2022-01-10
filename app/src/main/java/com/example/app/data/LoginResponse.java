package com.example.app.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class LoginResponse extends ResponseBody {
    @SerializedName("userid")
    private String userid;
    @SerializedName("codenum")
    private String codenum;
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("price")
    private String price;
    @SerializedName("review")
    private String review;
    @SerializedName("love")
    private String love;
    @SerializedName("imgbook")
    private String imgbook;
    @SerializedName("payone")
    private String payone;
    @SerializedName("paytwo")
    private String paytwo;
    @SerializedName("paythree")
    private String paythree;
    @SerializedName("payfour")
    private String payfour;
    @SerializedName("aboutbook")
    private String aboutbook;

    public String getUserid() {return userid;}
    public String getCodenum() {return codenum;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getPrice() {return price;}
    public String getReview() {return review;}
    public String getLove() {return love;}
    public String getImgbook() {return imgbook;}
    public String getPayone() {return payone;}
    public String getPaytwo() {return paytwo;}
    public String getPaythree() {return paythree;}
    public String getPayfour() {return payfour;}
    public String getAboutbook() {return aboutbook;}


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