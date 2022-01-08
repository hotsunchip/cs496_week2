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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import io.socket.client.Url;

public class PhotoSmallAdapter extends RecyclerView.Adapter<PhotoSmallAdapter.ViewHolder> {
    public interface OnListItemSelectedInterface {
        void onItemSelected(View view, int position);//, int position_pic);
    }

    //private OnListItemSelectedInterface mListener;
    private ArrayList<BookInfo> mDataset;
    private Context mContext;
    private OnListItemSelectedInterface mListener;
//    private int mPosition;

    public PhotoSmallAdapter(Context context, ArrayList<BookInfo> myDataset, OnListItemSelectedInterface listener){//, int pos) {
        mDataset = myDataset;
        mContext = context;
        mListener = listener;
//        mPosition = pos;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
            convertView = LayoutInflater.from(mContext).inflate(R.layout.photo_item_small, parent, false);
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
                    .override(360, 360)
                    .into(holder.img_thumb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        Log.e("viewhodler", photo.getPath());
//        holder.layout_gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickListener.onItemClick(v, albumVO);
//            }
//        });
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
                layout_gallery = (ConstraintLayout) convertView.findViewById(R.id.layout_gallery_small);
                img_thumb = (ImageView) convertView.findViewById(R.id.imageView_gallery_small);

            convertView.setOnClickListener(new View.OnClickListener() {
                // gallery photo click
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    mListener.onItemSelected(view, position); //mPosition, position);
                    Log.d("test", "position = " + position);
                }
            });
//
//
//            convertView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    int position = getAdapterPosition();
//                    AlertDialog.Builder adb = new AlertDialog.Builder(mContext, R.style.AlertDialog_AppCompat_Light);
//                    adb.setTitle("Delete")
//                            .setNeutralButton("CONFIRM", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                    String imagePath = mDataset.get(position).getPath();
//                                    mDataset.remove(position);
//
//                                    File file = new File(imagePath).getAbsoluteFile();
//
//                                    if(file.exists()){
//                                        System.gc();
//                                        System.runFinalization();
//                                        boolean ch = file.delete();
//                                        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imagePath)));
//                                    }
//
//                                    // adapter.notifyDataSetChanged();
//                                }
//                            })
//                            .setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                    AlertDialog finalDialog = adb.create();
//                    finalDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                        @Override
//                        public void onShow(DialogInterface arg0) {
//                            finalDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#6E6557"));
//                            finalDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#6E6557"));
//                            finalDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.parseColor("#6E6557"));
//                        }
//                    });
//                    finalDialog.show();
//                    return true;
//                }
//            });
        }
    }

}