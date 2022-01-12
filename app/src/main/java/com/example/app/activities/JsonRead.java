package com.example.app.activities;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonRead extends AppCompatActivity  {

    TextView txtData;
    AssetManager assetManager;

    public JsonRead(){}
    public JSONObject reading(Context context, String fileName){
//        this.mContext = context;
        Log.e("filename",fileName);
        AssetManager assetManager = context.getResources().getAssets();
        InputStream source = null;
        JSONObject JObject = null;
        try{
            source = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(source));
            String strResult = "";
            String line = "";

            while((line=reader.readLine()) != null){
                strResult += line;
            }
            JObject = new JSONObject(strResult);
//            Log.d("jsonread",JObject.toString());
            return JObject;

        }catch (IOException | JSONException e){
            e.printStackTrace();
//            Log.d("jsonrnotead","JObject.toString()");
        }
        return JObject;
    }

}
