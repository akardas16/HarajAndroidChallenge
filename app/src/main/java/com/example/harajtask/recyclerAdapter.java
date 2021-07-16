package com.example.harajtask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.recyclerHolder>{


    ArrayList<String> usernameList,titleList,thumbURLst,commentCount,cityList,dateList,bodyList;
    Context context;

    public recyclerAdapter(Context context, ArrayList<String> usernameList, ArrayList<String> titleList, ArrayList<String> thumbURLst, ArrayList<String> commentCount, ArrayList<String> cityList, ArrayList<String> dateList, ArrayList<String> bodyList) {
        this.context=context;
        this.usernameList = usernameList;
        this.titleList = titleList;
        this.thumbURLst = thumbURLst;
        this.commentCount = commentCount;
        this.cityList = cityList;
        this.dateList = dateList;
        this.bodyList = bodyList;

    }

    public static class recyclerHolder extends RecyclerView.ViewHolder  {
        RoundedImageView roundedImageView;
        TextView location,date,carName,cmntCount,userName;
        LinearLayout linearLayout;
        public recyclerHolder(@NonNull View itemView) {
            super(itemView);
            roundedImageView=itemView.findViewById(R.id.imageIDs);
            location=itemView.findViewById(R.id.locationID);
            date=itemView.findViewById(R.id.dateID);
            carName=itemView.findViewById(R.id.carNameID);
            cmntCount=itemView.findViewById(R.id.commentCountID);
            userName=itemView.findViewById(R.id.nameID);
            linearLayout=itemView.findViewById(R.id.linearID);


        }


    }


    @NonNull
    @Override
    public recyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View ssd= LayoutInflater.from(context).inflate(R.layout.recycler_items,parent,false);


        return new recyclerHolder(ssd);
    }

    @Override
    public void onBindViewHolder(@NonNull final recyclerHolder holder, final int position) {

        holder.userName.setText(usernameList.get(position));
        holder.cmntCount.setText(commentCount.get(position));
        String dateString = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date(Long.parseLong(dateList.get(position))));
        holder.date.setText(dateString);
        holder.location.setText(cityList.get(position));
        holder.carName.setText(titleList.get(position));
        Picasso.get().load(thumbURLst.get(position)).into(holder.roundedImageView);

        Log.i("cccdre", "onBindViewHolder: "+thumbURLst.get(position).toString());
        holder.linearLayout.setOnClickListener(v -> {

            Intent intent=new Intent(context,InfoActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("URL",thumbURLst.get(position));
            intent.putExtra("title",titleList.get(position));
            intent.putExtra("date",dateList.get(position));
            intent.putExtra("location",cityList.get(position));
            intent.putExtra("userName",usernameList.get(position));
            intent.putExtra("body",bodyList.get(position));

            context.startActivity(intent);


        });
    }

    @Override
    public int getItemCount() {
        return usernameList.size();
    }


}
