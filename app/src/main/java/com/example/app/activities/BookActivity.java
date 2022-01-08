package com.example.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.app.BookInfo;
import com.example.app.R;
import com.example.app.adapters.PhotoLargeAdapter;

public class BookActivity extends AppCompatActivity {
        RecyclerView mRecyclerView;
        CardView mReviewInfo;
        CardView mReviewInfoEdit;
        CardView mReviewPicture;
        TextView mReviewMembers;
        TextView mReviewDate;
        TextView mReviewDescription;
        EditText mReviewMembersEdit;
        EditText mReviewDescriptionEdit;
        PhotoLargeAdapter photoLargeAdapter;
        boolean onEditMode;
        private String bookId;
        private InputMethodManager imm;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book);


            Intent intent = getIntent();
            bookId = intent.getStringExtra("bookId");
//            BookInfo book = MainActivity.bookList.get(bookId);
            BookInfo book = createBookInfo();

            // setting action bar
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(bookId);//book.getReviewName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
//            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            onEditMode = false;
//
//            photoLargeAdapter = new PhotoLargeAdapter(this, book.getUriList(), position); //this, position);
//            mReviewInfo = (CardView) findViewById(R.id.cardView);
//            mReviewInfoEdit = (CardView) findViewById(R.id.cardView_edit);
//            mReviewPicture = (CardView) findViewById(R.id.cardView_edit_bkgd);
//            mRecyclerView = (RecyclerView) findViewById(R.id.photo_list);
//            mReviewMembers = (TextView) findViewById(R.id.photo_members);
//            mReviewDate = (TextView) findViewById(R.id.photo_date);
//            mReviewDescription = (TextView) findViewById(R.id.photo_description);
//            mReviewMembersEdit = (EditText) findViewById(R.id.photo_members_edit);
//            mReviewDescriptionEdit = (EditText) findViewById(R.id.photo_description_edit);
//            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//            mRecyclerView.setLayoutManager(mLinearLayoutManager);
//            mRecyclerView.setAdapter(photoLargeAdapter);
//            SnapHelper helper = new LinearSnapHelper();
//            helper.attachToRecyclerView(mRecyclerView);
//
//            mRecyclerView.smoothScrollToPosition(position_pic);
//
//            mReviewMembers.setText(book.getReviewMembers());
//            mReviewDate.setText(book.getReviewDate());
//            mReviewDescription.setText(book.getReviewDescription());
        }

    private BookInfo createBookInfo() {
        BookInfo book = new BookInfo();
        book.setBookId("9788954445290");
        book.setBookImg("https://bookthumb-phinf.pstatic.net/cover/168/912/16891202.jpg?type=m140&udate=20201029");
        book.setBookAuthor("정재혁");
        book.setBookPoint("10.0");
        book.setBookPrice("13320");
        book.setBookUrl1("http://www.yes24.com/Cooperate/Yes24Gateway.aspx?pid=95609&ReturnURL=http://www.yes24.com/Product/Goods/94407952");
        book.setBookUrl2("http://www.kyobobook.co.kr/cooper/redirect_over.jsp?LINK=NVB&next_url=http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&linkClass=&barcode=9788954445290");
        book.setBookUrl3("https://www.aladin.co.kr/part/wgate.aspx?k=yX0iVru1r6MZd1dA4HlGejY2Ue8syl&sk=641696&u=%2Fshop%2Fwproduct.aspx%3FISBN%3D8954445292");
        book.setBookUrl4("https://www.ypbooks.co.kr/book.yp?bookcd=101059066&gubun=NV");
        book.setBookExplain("전통과 현대의 감각이 만난 새로운 오늘자신만의 역사를 써 내려가는도쿄의 젊은 장인들세상 모든 일이 없어져도살아남을 단 하나의 직업 ‘장인’어제를 기억하는 내일과 가장 가까운 도시, 도쿄스스로 브랜드가 된 젊은 장인들의 일과 삶‘일’이란 무엇일까. 돈을 버는 수단이라는 건조한 사전적 의미를 지우고 나면, 한 사람의 정체성과 셀 수 없이 많은 사연이 숨어 있는 손때 묻은 개인의 역사가 아닐까. 더욱이 요즘, 자신만의 개성과 속도로");
        return book;
        }

    @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
}
