package com.example.app.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.activities.MainActivity;
import com.example.app.data.BookInfo;
import com.example.app.R;
import com.example.app.fragments.Fragment3;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CarouselViewAdapter extends RecyclerView.Adapter<CarouselViewAdapter.ViewHolder> {
//    public interface OnListItemSelectedInterface {
//        void onItemSelected(View view, int position);
//    }

    //private OnListItemSelectedInterface mListener;
    private ArrayList<BookInfo> mDataset;
    private Context mContext;
//    private OnListItemSelectedInterface mListener;
//    private int mPosition;

    public CarouselViewAdapter(Context context, ArrayList<BookInfo> myDataset) {
        mDataset = myDataset;
        mContext = context;
//        mListener = listener;
//        mPosition = pos;
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
                    .load(bookImgUrl).fitCenter()
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
        private ImageButton like_btn;
        private ImageButton detail_front_btn;
        private ImageButton detail_back_btn;
        private CardView card_front;
        private CardView card_back;
        public ViewHolder(View convertView) {
            super(convertView);
            img_thumb = (ImageView) convertView.findViewById(R.id.bookCover);
            like_btn = (ImageButton) convertView.findViewById(R.id.bookLikeBtn);
            detail_front_btn = (ImageButton) convertView.findViewById(R.id.bookDetailFrontBtn);
            detail_back_btn = (ImageButton) convertView.findViewById(R.id.bookDetailBackBtn);
            card_front = (CardView) convertView.findViewById(R.id.bookCardFront);
            card_back = (CardView) convertView.findViewById(R.id.bookCardBack);

            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    android.app.AlertDialog.Builder adb = new AlertDialog.Builder(mContext);

                    View view2 = LayoutInflater.from(mContext)
                            .inflate(R.layout.book_card_delete, null);
                    adb.setView(view2);
                    final Button buttonDelete = (Button) view2.findViewById(R.id.button_delete_confirm);
                    final Button buttonCancel = (Button) view2.findViewById(R.id.button_delete_cancel);

                    AlertDialog finalDialog = adb.create();

                    finalDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    finalDialog.setView(view2, 0, 0, 0, 0);

                    buttonDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDataset.remove(position);
//                            MainActivity.updateJSONImages(null, position);
                            Fragment3.refreshAdapter();
                            finalDialog.dismiss();
                        }
                    });
                    buttonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finalDialog.dismiss();
                        }
                    });
                    finalDialog.show();
                    return true;
                }
            });

            like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    like_btn.setSelected(!like_btn.isSelected());

                    if (like_btn.isSelected()) {
                        MainActivity.bookLikeList.add(mDataset.get(position));
                        Log.e("Book Added", String.valueOf(MainActivity.bookLikeList.size()));
                        Log.e("List", MainActivity.bookLikeList.toString());
                    } else {
                        MainActivity.bookLikeList.remove(mDataset.get(position));
                        Log.e("Book Removed", String.valueOf(MainActivity.bookLikeList.size()));
                        Log.e("List", MainActivity.bookLikeList.toString());
                    }
                }
            });

            detail_front_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    detail_front_btn.setSelected(!detail_front_btn.isSelected());
                    detail_back_btn.setSelected(!detail_front_btn.isSelected());

                    flipCard(detail_front_btn.isSelected());
                }
            });

            detail_back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    detail_front_btn.setSelected(!detail_front_btn.isSelected());
                    detail_back_btn.setSelected(!detail_front_btn.isSelected());

                    flipCard(detail_front_btn.isSelected());
                }
            });
        }

        private void flipCard(boolean isflipped) {
            if (isflipped) {
                flipCardAction(card_back, card_front);
            } else {
                flipCardAction(card_front, card_back);
            }
        }
        private void flipCardAction(ViewGroup visibleView, ViewGroup invisibleView) {
            visibleView.setVisibility(View.VISIBLE);
            float scale = mContext.getResources().getDisplayMetrics().density;
            float cameraDist = 8000 * scale;
            visibleView.setCameraDistance(cameraDist);
            invisibleView.setCameraDistance(cameraDist);
            AnimatorSet flipOutAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.front_animator);
            flipOutAnimatorSet.setTarget(invisibleView);
            AnimatorSet flipInAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.back_animator);
            flipInAnimatorSet.setTarget(visibleView);
            flipOutAnimatorSet.start();
            flipInAnimatorSet.start();
//            invisibleView.setVisibility(View.GONE);
        }
    }

}