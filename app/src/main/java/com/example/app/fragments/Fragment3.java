package com.example.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.activities.MainActivity;
import com.example.app.adapters.CarouselViewAdapter;
import com.example.app.data.BookInfo;
import com.example.app.R;
import com.example.app.activities.BookActivity;
import com.example.app.adapters.PhotoSmallAdapter;
import com.gtomato.android.ui.manager.CarouselLayoutManager;
import com.gtomato.android.ui.transformer.CoverFlowViewTransformer;
import com.gtomato.android.ui.transformer.InverseTimeMachineViewTransformer;
import com.gtomato.android.ui.transformer.WheelViewTransformer;
import com.gtomato.android.ui.widget.CarouselView;

import java.util.ArrayList;

public class Fragment3 extends Fragment {
    // fields
    private static CarouselView mRecyclerView;
    private static CarouselViewAdapter mBookAdapter;
//    public static ArrayList<BookInfo> bookList = null;

    // Required empty public constructor
    public Fragment3() {}

    public static Fragment3 newInstance() {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static void refreshAdapter() {
        mBookAdapter.notifyItemInserted(0);
        mRecyclerView.smoothScrollToPosition(0);
//        mBookAdapter.notifyDataSetChanged();
        // mRecyclerView.setAdapter(photoAdapter);
        Log.e("refreshed", "true");
    }

//    private BookInfo createDefaultBookInfo() {
//        BookInfo book = new BookInfo();
//        book.setBookId("9791158391508");
//        book.setBookTitle("Vue.js 철저 입문");
//        book.setBookImg("https://bookthumb-phinf.pstatic.net/cover/147/966/14796608.jpg?type=m140&udate=20190419");
//        book.setBookAuthor("카와구치 카즈야키타 케이스케노다 요헤이테지마 타쿠야카타야마 신야심효섭");
//        book.setBookPoint("0.0");
//        book.setBookPrice("27000");
//        book.setBookUrl1("http://www.yes24.com/Cooperate/Yes24Gateway.aspx?pid=95609&ReturnURL=http://www.yes24.com/Product/Goods/72169945");
//        book.setBookUrl2("http://www.kyobobook.co.kr/cooper/redirect_over.jsp?LINK=NVB&next_url=http://www.kyobobook.co.kr/product/detailViewKor.laf?mallGb=KOR&ejkGb=KOR&linkClass=&barcode=9791158391508");
//        book.setBookUrl3("https://www.aladin.co.kr/part/wgate.aspx?k=yX0iVru1r6MZd1dA4HlGejY2Ue8syl&sk=641696&u=%2Fshop%2Fwproduct.aspx%3FISBN%3DK652635584");
//        book.setBookUrl4("https://www.ypbooks.co.kr/book.yp?bookcd=100947768&gubun=NV");
//        book.setBookExplain("VUE.JS 입문서 결정판. 초보부터 실무까지 이 책 한권으로!VUE.JS는 깃허브에서 ‘가장 인기 있는 자바스크립트 프레임워크’로 꼽힐 만큼 많은 주목을 받고 있는 기술입니다. 《VUE.JS 철저 입문》에서는 프런트 엔드 특화 라이브러리 중에서 가장 큰 지지를 받는 VUE.JS를 중심으로 현대적인 프런트 엔드 개발을 경험해 볼 수 있게 구성했습니다.사용하기 쉽고 현업에서 많이 사용되는 VUE.JS를 VUE.JS 코어 팀 멤버");
//        return book;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 변수 초기화
//        if (bookList == null) {
//            bookList = new ArrayList<BookInfo>();
//            BookInfo bookDefault = createDefaultBookInfo();
//            bookList.add(bookDefault);
//            bookList.add(bookDefault);
//            bookList.add(bookDefault);
//            bookList.add(bookDefault);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false) ;

        mRecyclerView = (CarouselView) view.findViewById(R.id.bookCover_recyclerView);
//        mRecyclerView.setTransformer(new InverseTimeMachineViewTransformer());
        mRecyclerView.setTransformer(new CarouselView.ViewTransformer() {
            @Override
            public void onAttach(CarouselLayoutManager layoutManager) {
                layoutManager.setDrawOrder(CarouselView.DrawOrder.FirstFront);
            }

//            @Override
//            public void transform(View view, float position) {
//                int width = view.getMeasuredWidth(), height = view.getMeasuredHeight();
//                view.setTranslationX(width * position * (1) * 0.5f * (2f / (Math.abs(position) + 2)));
//                view.setScaleX(2f / (position + 2));
//                view.setScaleY(2f / (position + 2));
//                view.setAlpha(position < 0 ? Math.max(1 + position, 0) : 1);
//            }

            @Override
            public void transform(View view, float position) {
                int width = view.getMeasuredWidth(), height = view.getMeasuredHeight();
                float alpha, transX, scale;
                if (-1 < position && position < 5) { // (-5, 1)
                    if (position >= 0) { // (-5, 0]
                        // position     -∞  ... -5      -4      -3      -2      -1      0
                        // alpha        0   ... 0       0.2     0.4     0.6     0.8     1.0
                        // transX       -∞  ... -1.0w   -0.8w   -0.6w   -0.4w   -0.2w   0w
                        // scale        0   ... 0.5     0.6     0.7     0.8     0.9     1.0
//                        alpha = Math.max(0f, 1.0f - position * 0.2f);
                        alpha = 1.0f;
                        transX = position * width * 0.03f;
                        scale = Math.max(0f, 1.0f - position * 0.02f); // s = 1 - 0.4 * (0.2p)^2
                    } else /*if (position < 1) */ { // (0, 1)
                        // position     0       0.5     1       ...     +∞
                        // alpha        1.0     0.5     0       ...     0
                        // transX       0pw     0.25pw  0.5pw   ...     +∞
                        // scale        1.0     2.25    3.5     ...     +∞
                        alpha = Math.max(0f, 1.0f + position);
                        transX = position * mRecyclerView.getWidth() / 2;
                        scale = 1.0f - position * 0.5f;
                    }
                    view.setAlpha(alpha);
                    view.setTranslationX(transX);
                    view.setScaleX(scale);
                    view.setScaleY(scale);
                    view.setVisibility(View.VISIBLE);
                } else { // (-∞, -5] | [1, +∞)
                    // explicitly set visibility instead of alpha to improve performance
                    view.setVisibility(View.GONE);
                }
            }
        });

        mBookAdapter = new CarouselViewAdapter(getContext(), MainActivity.bookList);
        mRecyclerView.setAdapter(mBookAdapter);

        return view;
    }
}