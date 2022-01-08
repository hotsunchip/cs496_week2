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
import com.example.app.fragments.Fragment3;

public class BookActivity extends AppCompatActivity {
        RecyclerView mRecyclerView;
        CardView mReviewInfo;
        CardView mReviewPicture;
        TextView mReviewMembers;
        TextView mReviewDate;
        TextView mReviewDescription;
        EditText mReviewMembersEdit;
        EditText mReviewDescriptionEdit;
        PhotoLargeAdapter photoLargeAdapter;
        boolean onEditMode;
        private int position;
        private InputMethodManager imm;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book);


            Intent intent = getIntent();
            position = intent.getIntExtra("pos", 0);
//            BookInfo book = MainActivity.bookList.get(bookId);
//            BookInfo book = createBookInfo();
//            Fragment3.bookList.add(0, book);

            // setting action bar
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(String.valueOf(position));//book.getReviewName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
//            imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            onEditMode = false;
//
            photoLargeAdapter = new PhotoLargeAdapter(this, Fragment3.bookList, 0); //this, position);
            mReviewInfo = (CardView) findViewById(R.id.cardView);
            mReviewPicture = (CardView) findViewById(R.id.cardView_edit_bkgd);
            mRecyclerView = (RecyclerView) findViewById(R.id.photo_list);
            mReviewMembers = (TextView) findViewById(R.id.photo_members);
            mReviewDate = (TextView) findViewById(R.id.photo_date);
            mReviewDescription = (TextView) findViewById(R.id.photo_description);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setAdapter(photoLargeAdapter);
            SnapHelper helper = new LinearSnapHelper();
            helper.attachToRecyclerView(mRecyclerView);
            mRecyclerView.smoothScrollToPosition(position);

//            mReviewMembers.setText(book.getBookTitle());
//            mReviewDate.setText(book.getBookId());
//            mReviewDescription.setText(book.getBookExplain());
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
