package com.example.app;

import com.example.app.data.BarcodeData;
import com.example.app.data.BarcodeResponse;
import com.example.app.data.ByeData;
import com.example.app.data.ByeResponse;
import com.example.app.data.HeartdownData;
import com.example.app.data.HeartdownResponse;
import com.example.app.data.HeartupData;
import com.example.app.data.HeartupResponse;
import com.example.app.data.JoinData;
import com.example.app.data.JoinResponse;
import com.example.app.data.LoginData;
import com.example.app.data.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class APIService {
    public interface ApiService {
        @GET("/retrofit/get")
        Call<ResponseBody> getFunc(@Query("data") String data);

        @FormUrlEncoded
        @POST("/retrofit/post")
        Call<ResponseBody> postFunc(@Field("data") String data);

        @FormUrlEncoded
        @PUT("/retrofit/put/{id}")
        Call<ResponseBody> putFunc(@Path("id") String id, @Field("data") String data);

        @DELETE("/retrofit/delete/{id}")
        Call<ResponseBody> deleteFunc(@Path("id") String id);

        @POST("/sign/signup")
        Call<JoinResponse> userJoin(@Body JoinData data);

        @POST("/login/signcheck")
        Call<LoginResponse> userLoginCheck(@Body LoginData data);

        @POST("/login/signin")
        Call<LoginResponse> userLogin(@Body LoginData data);

        @POST("/play/barcodecheck")
        Call<BarcodeResponse> userBarcode(@Body BarcodeData data);

        @POST("/play/barcodedelete")
        Call<ByeResponse> userBye(@Body ByeData data);

        @POST("/heart/heartplus")
        Call<HeartupResponse> userHeartup(@Body HeartupData data);

        @POST("/heart/heartminus")
        Call<HeartdownResponse> userHeartdown(@Body HeartdownData data);


    }
}

















