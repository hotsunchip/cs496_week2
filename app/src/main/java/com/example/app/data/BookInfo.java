package com.example.app.data;

import android.net.Uri;

import java.util.ArrayList;

public class BookInfo {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookPrice;
    private String bookPoint;
    private String bookLove;
    private String bookImg;
    private String bookUrl1;
    private String bookUrl2;
    private String bookUrl3;
    private String bookUrl4;
    private String bookExplain;

//    public GalleryImage(JSONObject jsonText) throws JSONException {
//        // this.uriList = jsonText.getString("uriList");
//        JSONArray jsonList = jsonText.getJSONArray("uriList");
//
//        //uriList array list 생성
//        uriList = new ArrayList<Uri>();
//        for(int i = 0 ; i < jsonList.length() ; i++){
//            Uri uri;
//            uri = (Uri) jsonList.get(i);
//            //찍어보는 방법
////            Log.e("check", tag.toString());
//            this.uriList.add(uri);
//        }
//    }
    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookPrice() {
        return bookPrice;
    }
    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }
    public String getBookPoint() {
        return bookPoint;
    }
    public void setBookPoint(String bookPoint) {
        this.bookPoint = bookPoint;
    }
    public String getBookLove() {
        return bookLove;
    }
    public void setBookLove(String bookLove) {
        this.bookLove = bookLove;
    }
    public String getBookImg() {
        return bookImg;
    }
    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }
    public String getBookUrl1() {
        return bookUrl1;
    }
    public void setBookUrl1(String bookUrl1) {
        this.bookUrl1 = bookUrl1;
    }
    public String getBookUrl2() {
        return bookUrl2;
    }
    public void setBookUrl2(String bookUrl2) {
        this.bookUrl2 = bookUrl2;
    }
    public String getBookUrl3() {
        return bookUrl3;
    }
    public void setBookUrl3(String bookUrl3) {
        this.bookUrl3 = bookUrl3;
    }
    public String getBookUrl4() {
        return bookUrl4;
    }
    public void setBookUrl4(String bookUrl4) {
        this.bookUrl4 = bookUrl4;
    }
    public String getBookExplain() {
        return bookExplain;
    }
    public void setBookExplain(String bookExplain) {
        this.bookExplain = bookExplain;
    }
}
