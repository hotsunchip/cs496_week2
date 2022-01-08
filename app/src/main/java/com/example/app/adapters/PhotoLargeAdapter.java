package com.example.app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.BookInfo;
import com.example.app.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PhotoLargeAdapter extends RecyclerView.Adapter<PhotoLargeAdapter.ViewHolder> {
//    public interface OnListItemSelectedInterface {
//        void onItemSelected(View view, int position, int position_pic);
//    }

    //private OnListItemSelectedInterface mListener;
    private ArrayList<BookInfo> mDataset;
    private Context mContext;
//    private OnListItemSelectedInterface mListener;
    private int mPosition;

    public PhotoLargeAdapter(Context context, ArrayList<BookInfo> myDataset, int pos) { //, OnListItemSelectedInterface listener, int pos) {
        mDataset = myDataset;
        mContext = context;
//        mListener = listener;
        mPosition = pos;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
            convertView = LayoutInflater.from(mContext).inflate(R.layout.photo_item_large, parent, false);
        Log.e("onCreateViewHolder", String.valueOf(true));
        return new ViewHolder(convertView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String photo = mDataset.get(position).getBookImg();
        try {
            URL bookImgUrl = new URL(photo);
            Glide.with(mContext)
                    .load(bookImgUrl)
                    .override(1040, 1040)
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
        private ConstraintLayout layout_gallery;
        private ImageView img_thumb;
        public ViewHolder(View convertView) {
            super(convertView);
                layout_gallery = (ConstraintLayout) convertView.findViewById(R.id.layout_gallery_large);
                img_thumb = (ImageView) convertView.findViewById(R.id.imageView_gallery_large);

        }
    }

}