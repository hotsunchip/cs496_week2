package com.example.app.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app.adapters.CarouselViewAdapter;
import com.example.app.data.BookInfo;
import com.example.app.R;
import com.example.app.adapters.VPAdapter;
import com.example.app.fragments.Fragment3;
import com.example.app.fragments.Fragment_empty2;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

//0111
import com.example.app.APIService;
import com.example.app.R;
import com.example.app.RetrofitClient;
import com.example.app.data.BarcodeResponse;
import com.example.app.data.BarcodeData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<BookInfo> bookList;
    public static ArrayList<BookInfo> bookLikeList;
    public static String userid;
    private ProgressBar mProgressView;
    private APIService.ApiService service;
    public static String codenum;
    public static String title;
    public static String author;
    public static String price;
    public static String review;
    public static String love;
    public static String imgbook;
    public static String payone;
    public static String paytwo;
    public static String paythree;
    public static String payfour;
    public static String aboutbook;




    // fields
    private static Context mContext;
    private long backKeyPressedTime = 0;
    private Toast toast;
    private static ViewPager2 viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        bookList = new ArrayList<>();
        bookLikeList = new ArrayList<>();
        mProgressView = (ProgressBar) findViewById(R.id.progress);
        service = RetrofitClient.getClient().create(APIService.ApiService.class);

//        Intent intent = getIntent();

        // setting action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        viewPager = (ViewPager2) findViewById(R.id.viewpager);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_empty2());
        fragments.add(new Fragment3());
        VPAdapter adapter = new VPAdapter(this, fragments);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                super.onPageSelected(pos);
                if (pos == 0) {
                    IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                    integrator.setCaptureActivity(ScannerActivity.class);
                    integrator.initiateScan();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d("onActivityResult", "onActivityResult: .");
        if (resultCode == Activity.RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            String re = scanResult.getContents();
//            int bookId = Integer.parseInt(re);
            CarouselViewAdapter.codenum = re;
            Log.d("onActivityResult", "onActivityResult: ." + re);
            Toast.makeText(this, re, Toast.LENGTH_LONG).show();
//            bringBookInfo();
            setPage(1);
            //0111
            BookInfos(re);
//            bringBookInfo();
//            Intent bookIntent = new Intent(MainActivity.this, BookActivity.class);
//            bookIntent.putExtra("pos", 0);
//            startActivity(bookIntent);
        }
    }
    //asdasdas

//    private void bringBookInfo() {
//        BookInfo book = new BookInfo();
//        book.setBookId("9788954445290");
//        book.setBookTitle("????????? ?????? ????????????");
//        book.setBookImg("https://bookthumb-phinf.pstatic.net/cover/168/912/16891202.jpg?type=m140&udate=20201029");
//        book.setBookAuthor("?????????");
//        book.setBookPoint("10.0");
//        book.setBookPrice("13320");
//        book.setBookUrl1("http://www.yes24.com/Cooperate/Yes24Gateway.aspx?pid=95609&ReturnURL=http://www.yes24.com/Product/Goods/94407952");
//        book.setBookUrl2("http://www.kyobobook.co.kr/cooper/redirect_over.jsp?LINK=NVB&next_url=http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&linkClass=&barcode=9788954445290");
//        book.setBookUrl3("https://www.aladin.co.kr/part/wgate.aspx?k=yX0iVru1r6MZd1dA4HlGejY2Ue8syl&sk=641696&u=%2Fshop%2Fwproduct.aspx%3FISBN%3D8954445292");
//        book.setBookUrl4("https://www.ypbooks.co.kr/book.yp?bookcd=101059066&gubun=NV");
//        book.setBookExplain("????????? ????????? ????????? ?????? ????????? ?????????????????? ????????? ??? ????????????????????? ?????? ??????????????? ?????? ?????? ???????????????????????? ??? ????????? ?????? ????????????????????? ???????????? ????????? ?????? ????????? ??????, ??????????????? ???????????? ??? ?????? ???????????? ?????? ?????????????????? ????????????. ?????? ?????? ??????????????? ????????? ????????? ????????? ????????? ??????, ??? ????????? ???????????? ??? ??? ?????? ?????? ????????? ?????? ?????? ?????? ?????? ????????? ????????? ?????????. ????????? ??????, ???????????? ????????? ?????????");
//
//        Fragment3.bookList.add(0, book);
//        Fragment3.refreshAdapter();
//    }

    //0111
    private void BookInfos(String re) {
        String barcode = re;
        String usingid = userid;
        startBarcode(new BarcodeData(barcode, usingid));
        showProgress(true);
    }
    private void startBarcode(BarcodeData data) {
        Call<BarcodeResponse> call_barcode = service.userBarcode(data);
        call_barcode.enqueue(new Callback<BarcodeResponse>() {
            @Override
            public void onResponse(Call<BarcodeResponse> call, Response<BarcodeResponse> response) {
                if (response.isSuccessful()) {
                    codenum = response.body().getCodenum();
                    title = response.body().getTitle();
                    author = response.body().getAuthor();
                    price = response.body().getPrice();
                    review = response.body().getReview();
                    love = response.body().getLove();
                    imgbook = response.body().getImgbook();
                    payone = response.body().getPayone();
                    paytwo = response.body().getPaytwo();
                    paythree = response.body().getPaythree();
                    payfour = response.body().getPayfour();
                    aboutbook = response.body().getAboutbook();

                    BookInfo book = new BookInfo();
                    book.setBookId(codenum);
                    book.setBookTitle(title);
                    book.setBookImg(imgbook);
                    book.setBookAuthor(author);
                    book.setBookPoint(review);
                    book.setBookPrice(price);
                    book.setBookLove(love);
                    book.setBookUrl1(payone);
                    book.setBookUrl2(paytwo);
                    book.setBookUrl3(paythree);
                    book.setBookUrl4(payfour);
                    book.setBookExplain(aboutbook);

                    MainActivity.bookList.add(0, book);
                    ScannerActivity.updateBookList(book, -1);
                    Fragment3.refreshAdapter();

                    String result = "???????????? ?????????????????????!";
                    Log.v("", "result = " + result);
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    showProgress(false);
                } else {
                    String result ="????????? ????????? ?????????????????????.";
                    Log.v("", "error = " + result);
                    Toast.makeText(MainActivity.this, "error = " + result, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BarcodeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "????????? ?????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                Log.e("????????? ?????? ?????????????????????.", t.getMessage());
                showProgress(false);
            }
        });
    }



    //?????????????????? ???????????? ??????
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'??????\' ????????? ??? ??? ??? ???????????? ???????????????.", Toast.LENGTH_LONG);
            toast.show();
            return;
        } else {
            finish();;
            toast.cancel();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //??????????????? ????????? ??? ??????????????? ?????????
        overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        this.overridePendingTransition(0, 0);
        super.onResume();
    }

    public static void setPage(int index) {
        viewPager.setCurrentItem(index);
    }

    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}