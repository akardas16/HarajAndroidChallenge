package com.example.harajtask;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter<recyclerAdapter.recyclerHolder> adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<String> usernameList,titleList,thumbURLst,commentCount,cityList,dateList,bodyList;

    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerID);
        context=getApplicationContext();
        usernameList=new ArrayList<>();
        titleList=new ArrayList<>();
        thumbURLst=new ArrayList<>();
        commentCount=new ArrayList<>();
        cityList=new ArrayList<>();
        dateList=new ArrayList<>();
        bodyList=new ArrayList<>();
        adapter=new recyclerAdapter(context,usernameList,titleList,thumbURLst,commentCount,cityList,dateList,bodyList);
        loadJSONFromAsset();
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        loadJSONFromAsset();



    }

    public void loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        try {
            JSONObject myObject=new JSONObject(json);
            JSONArray array=myObject.getJSONArray("Json_values");
            for (int i=0;i<array.length();i++){
                JSONObject obj=new JSONObject(array.get(i).toString());
                usernameList.add(obj.get("username").toString());
                titleList.add(obj.get("title").toString());
                thumbURLst.add(obj.get("thumbURL").toString());
                commentCount.add(obj.get("commentCount").toString());
                cityList.add(obj.get("city").toString());
                dateList.add(obj.get("date").toString());
                bodyList.add(obj.get("body").toString());
               // Log.i("dsfdf", "loadJSONFromAsset: "+obj.get("username").toString());
            }
            recyclerView.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}



