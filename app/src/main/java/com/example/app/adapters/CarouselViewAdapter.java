package com.example.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.data.BookInfo;
import com.example.app.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CarouselViewAdapter extends RecyclerView.Adapter<CarouselViewAdapter.ViewHolder> {
//    public interface OnListItemSelectedInterface {
//        void onItemSelected(View view, int position, int position_pic);
//    }

    //private OnListItemSelectedInterface mListener;
    private ArrayList<BookInfo> mDataset;
    private Context mContext;
//    private OnListItemSelectedInterface mListener;
    private int mPosition;

    public CarouselViewAdapter(Context context, ArrayList<BookInfo> myDataset, int pos) { //, OnListItemSelectedInterface listener, int pos) {
        mDataset = myDataset;
        mContext = context;
//        mListener = listener;
        mPosition = pos;
    }
    @NonNull
    @Override
    public CarouselViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CarouselViewAdapter.ViewHolder holder, int position) {
        String photo = mDataset.get(position).getBookImg();
        try {
            URL bookImgUrl = new URL(photo);
            Glide.with(mContext)
                    .load(bookImgUrl).centerCrop()
                    .into(holder.img_thumb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        holder.img_thumb.setClipToOutline(true);
//        Log.e("viewhodler", photo.getPath()
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_thumb;
        public ViewHolder(View convertView) {
            super(convertView);
                img_thumb = (ImageView) convertView.findViewById(R.id.bookCover);
        }
    }

}