package com.example.harajtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");
        String userName = intent.getStringExtra("userName");
        String body = intent.getStringExtra("body");
        TextView bodyview=findViewById(R.id.bodyID);
        bodyview.setText(body);
        ImageView imageView=findViewById(R.id.myImageID);
        Picasso.get().load(url).into(imageView);
        TextView carName=findViewById(R.id.carNameID);
        carName.setText(title);
        TextView dates=findViewById(R.id.dateID);
        String dateString = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.getDefault()).format(new Date(Long.parseLong(date)));
        dates.setText(String.format("%sPM", dateString));

        TextView nameView=findViewById(R.id.nameID);
        nameView.setText(userName);
        TextView locationView=findViewById(R.id.locationID);
        locationView.setText(location);
        ImageView share=findViewById(R.id.shareID);
        share.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_SEND);
            intent1.setType("text/plain");
            intent1.putExtra(Intent.EXTRA_TEXT, title);
            startActivity(intent1);
        });

    }
}