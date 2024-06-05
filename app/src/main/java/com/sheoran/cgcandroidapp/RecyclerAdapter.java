package com.sheoran.cgcandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
  ArrayList<ModelClass>userlist;
  Context context;

    public RecyclerAdapter(ArrayList<ModelClass> userlist, Context context) {
        this.userlist = userlist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(context).inflate(R.layout.format,viewGroup,false);
       MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder myViewHolder, int i) {

        ModelClass modelClass = userlist.get(i);
        myViewHolder.imageView.setImageResource(modelClass.getImageurl());
        myViewHolder.textView.setText(modelClass.getName());
//        myViewHolder.textView1.setText(modelClass.getAge());
        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //   myViewHolder.textView1.setText(String.valueOf(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView ,textView1;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout =itemView.findViewById(R.id.relativelayout);
            imageView =itemView.findViewById(R.id.imvew);
            textView =itemView.findViewById(R.id.textvw);
            textView1 =itemView.findViewById(R.id.textvw1);
            button =itemView.findViewById(R.id.button);
        }
    }
}
