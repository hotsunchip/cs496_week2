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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app.activities.MainActivity;
import com.example.app.data.BookInfo;
import com.example.app.R;
import com.example.app.data.ByeData;
import com.example.app.data.ByeResponse;
import com.example.app.data.HeartdownData;
import com.example.app.data.HeartdownResponse;
import com.example.app.data.HeartupData;
import com.example.app.data.HeartupResponse;
import com.example.app.fragments.Fragment3;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.example.app.APIService;
import com.example.app.R;
import com.example.app.RetrofitClient;
import com.example.app.data.BarcodeResponse;
import com.example.app.data.BarcodeData;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarouselViewAdapter extends RecyclerView.Adapter<CarouselViewAdapter.ViewHolder> {
//    public interface OnListItemSelectedInterface {
//        void onItemSelected(View view, int position);
//    }

    //private OnListItemSelectedInterface mListener;
    private ArrayList<BookInfo> mDataset;
    private Context mContext;
    public static String userid;
    public static String codenum;
    private ProgressBar mProgressView;
    private APIService.ApiService service;

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
        private final ImageButton like_btn;
        private ImageButton detail_front_btn;
        private ImageButton detail_back_btn;
        private View card_front;
        private View card_back;

        private TextView card_Title;
        private TextView card_Author;
        private TextView card_Rate;
        private TextView card_Desc;
        public ViewHolder(View convertView) {
            super(convertView);
            img_thumb = (ImageView) convertView.findViewById(R.id.bookCover);
            like_btn = (ImageButton) convertView.findViewById(R.id.bookLikeBtn);
            detail_front_btn = (ImageButton) convertView.findViewById(R.id.bookDetailFrontBtn);
            detail_back_btn = (ImageButton) convertView.findViewById(R.id.bookDetailBackBtn);
            card_front = convertView.findViewById(R.id.bookCardFront);
            card_back = convertView.findViewById(R.id.bookCardBack);

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
                            mProgressView = (ProgressBar) view.findViewById(R.id.login_progress);
                            service = RetrofitClient.getClient().create(APIService.ApiService.class);
                            mDataset.remove(position);
//                            MainActivity.updateJSONImages(null, position);
                            Fragment3.refreshAdapter();
                            finalDialog.dismiss();

                            //0111
                            byebye(userid, codenum);
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

            detail_front_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    detail_front_btn.setSelected(!detail_front_btn.isSelected());
                    detail_back_btn.setSelected(!detail_back_btn.isSelected());

                    flipCard(detail_front_btn.isSelected());
                }
            });

            detail_back_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    detail_front_btn.setSelected(!detail_front_btn.isSelected());
                    detail_back_btn.setSelected(!detail_back_btn.isSelected());

                    flipCard(detail_front_btn.isSelected());
                    like_btn.setClickable(true);
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
                        //0111
                        heartplus(codenum, userid);
                    } else {
                        MainActivity.bookLikeList.remove(mDataset.get(position));
                        Log.e("Book Removed", String.valueOf(MainActivity.bookLikeList.size()));
                        Log.e("List", MainActivity.bookLikeList.toString());
                        //0111
                        heartminus(codenum, userid);
                    }
                }
            });
        }


        private void heartplus(String codenum, String userid) {
            String barcodenum = codenum;
            String usingid = userid;
            startHeartplus(new HeartupData(codenum, userid));
            showProgress(true);
        }

        private void heartminus(String codenum, String userid) {
            String barcodenum = codenum;
            String usingid = userid;
            startHeartminus(new HeartdownData(codenum, userid));
            showProgress(true);
        }

        private void startHeartplus(HeartupData data) {
            Call<HeartupResponse> call_heartplus = service.userHeartup(data);
            call_heartplus.enqueue(new Callback<HeartupResponse>() {
                @Override
                public void onResponse(Call<HeartupResponse> call, Response<HeartupResponse> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();
                    if (response.isSuccessful()) {
                        String result = "하트 클릭 성공!";
                        Log.v("", "result = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, message, Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    } else {
                        String result = "하트 클릭 실패.";
                        Log.v("", "error = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, "error = " + result, Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getCode() == 200) {
                        String result = "Heart up complete.";
                        Log.e("Result: ", result);
                    }
                }
                @Override
                public void onFailure(Call<HeartupResponse> call, Throwable t) {
//                    Toast.makeText(CarouselViewAdapter.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e("하트 추가 클릭 에러 발생", t.getMessage());
                    showProgress(false);
                }
            });
        }

        private void startHeartminus(HeartdownData data) {
            Call<HeartdownResponse> call_heartminus = service.userHeartdown(data);
            call_heartminus.enqueue(new Callback<HeartdownResponse>() {
                @Override
                public void onResponse(Call<HeartdownResponse> call, Response<HeartdownResponse> response) {
                    int code = response.body().getCode();
                    String message = response.body().getMessage();
                    if (response.isSuccessful()) {
                        String result = "하트를 취소했습니다!";
                        Log.v("", "result = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, message, Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    } else {
                        String result = "하트 취소 실패.";
                        Log.v("", "error = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, "error = " + result, Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().getCode() == 200) {
                        String result = "Heart up complete.";
                        Log.e("Result: ", result);
                    }
                }
                @Override
                public void onFailure(Call<HeartdownResponse> call, Throwable t) {
//                    Toast.makeText(CarouselViewAdapter.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e("하트 제거 클릭 에러 발생", t.getMessage());
                    showProgress(false);
                }
            });
        }

        private void byebye(String userid, String codenum) {
            String usingid = userid;
            String barcodenum = codenum;
            startBye(new ByeData(barcodenum, usingid));
            showProgress(true);
        }

        private void startBye(ByeData data) {
            Call<ByeResponse> call_bye = service.userBye(data);
            call_bye.enqueue(new Callback<ByeResponse>() {
                @Override
                public void onResponse(Call<ByeResponse> call, Response<ByeResponse> response) {
                    if (response.isSuccessful()) {
                        int code = response.body().getCode();
                        String message = response.body().getMessage();
                        String result = "로그인에 성공하였습니다!";
                        Log.v("", "result = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, result, Toast.LENGTH_SHORT).show();
                        showProgress(false);
                    } else {
                        String result ="로그인에 실패하였습니다.";
                        Log.v("", "error = " + result);
//                        Toast.makeText(CarouselViewAdapter.this, "error = " + result, Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<ByeResponse> call, Throwable t) {
//                    Toast.makeText(CarouselViewAdapter.this, "로그인 에러 발생하였습니다.", Toast.LENGTH_SHORT).show();
                    Log.e("로그인 에러 발생하였습니다.", t.getMessage());
                    showProgress(false);
                }
            });
        }

        private void showProgress(boolean show) {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }



        private void flipCard(boolean isflipped) {
            if (isflipped) {
                flipCardAction(card_back, card_front);
            } else {
                flipCardAction(card_front, card_back);
            }
        }
        private void flipCardAction(View visibleView, View invisibleView) {
            visibleView.setVisibility(View.VISIBLE);
            float scale = mContext.getResources().getDisplayMetrics().density;
            float cameraDist = 100000 * scale;
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