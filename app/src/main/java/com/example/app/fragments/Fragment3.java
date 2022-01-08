package com.example.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.BookInfo;
import com.example.app.R;
import com.example.app.activities.BookActivity;
import com.example.app.adapters.PhotoSmallAdapter;

import java.util.ArrayList;

public class Fragment3 extends Fragment implements PhotoSmallAdapter.OnListItemSelectedInterface {
    // fields
    RecyclerView mRecyclerView;
    private static PhotoSmallAdapter mBookAdapter;
    public static ArrayList<BookInfo> bookList = null;

    // Required empty public constructor
    public Fragment3() {}

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static void refreshAdapter() {
        mBookAdapter.notifyDataSetChanged();
        // mRecyclerView.setAdapter(photoAdapter);
        Log.e("refreshed", "true");
    }

    private BookInfo createDefaultBookInfo() {
        BookInfo book = new BookInfo();
        book.setBookId("9791158391508");
        book.setBookTitle("Vue.js 철저 입문");
        book.setBookImg("https://bookthumb-phinf.pstatic.net/cover/147/966/14796608.jpg?type=m140&udate=20190419");
        book.setBookAuthor("카와구치 카즈야키타 케이스케노다 요헤이테지마 타쿠야카타야마 신야심효섭");
        book.setBookPoint("0.0");
        book.setBookPrice("27000");
        book.setBookUrl1("http://www.yes24.com/Cooperate/Yes24Gateway.aspx?pid=95609&ReturnURL=http://www.yes24.com/Product/Goods/72169945");
        book.setBookUrl2("http://www.kyobobook.co.kr/cooper/redirect_over.jsp?LINK=NVB&next_url=http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&linkClass=&barcode=9791158391508");
        book.setBookUrl3("https://www.aladin.co.kr/part/wgate.aspx?k=yX0iVru1r6MZd1dA4HlGejY2Ue8syl&sk=641696&u=%2Fshop%2Fwproduct.aspx%3FISBN%3DK652635584");
        book.setBookUrl4("https://www.ypbooks.co.kr/book.yp?bookcd=100947768&gubun=NV");
        book.setBookExplain("VUE.JS 입문서 결정판. 초보부터 실무까지 이 책 한권으로!VUE.JS는 깃허브에서 ‘가장 인기 있는 자바스크립트 프레임워크’로 꼽힐 만큼 많은 주목을 받고 있는 기술입니다. 《VUE.JS 철저 입문》에서는 프런트 엔드 특화 라이브러리 중에서 가장 큰 지지를 받는 VUE.JS를 중심으로 현대적인 프런트 엔드 개발을 경험해 볼 수 있게 구성했습니다.사용하기 쉽고 현업에서 많이 사용되는 VUE.JS를 VUE.JS 코어 팀 멤버");
        return book;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
        if (bookList == null) {
            bookList = new ArrayList<BookInfo>();
            BookInfo bookDefault = createDefaultBookInfo();
            bookList.add(bookDefault);
            bookList.add(bookDefault);
            bookList.add(bookDefault);
            bookList.add(bookDefault);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false) ;

        mRecyclerView = (RecyclerView) view.findViewById(R.id.bookCover_recyclerView);
        RecyclerView.LayoutManager mGridLayoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mBookAdapter = new PhotoSmallAdapter(getContext(), bookList, this);
        mRecyclerView.setAdapter(mBookAdapter);

        return view;
    }

    // bookImg가 클릭되었을 때
    @Override
    public void onItemSelected(View view, int position) {
        Log.e("Listen", String.valueOf(position));
        Intent intent = new Intent(getActivity(), BookActivity.class);
        intent.putExtra("pos", position);
//        intent.putExtra("pos_pic", 0);
        startActivity(intent);
    }
}