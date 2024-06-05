package com.sheoran.cgcandroidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity3 extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelClass>userlist;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView= findViewById(R.id.recyclerView);
        userlist= new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,
                RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);

        userlist.add(new ModelClass("Rahul",R.drawable.a,20));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,21));
        userlist.add(new ModelClass("Ravi",R.drawable.aabc,22));
        userlist.add(new ModelClass("Rohit",R.drawable.aax,23));
        userlist.add(new ModelClass("Ravi",R.drawable.abb,24));
        userlist.add(new ModelClass("Rohit",R.drawable.ab,25));
        userlist.add(new ModelClass("Ravi",R.drawable.abc,26));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,27));
        userlist.add(new ModelClass("Rahul",R.drawable.a,20));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,21));
        userlist.add(new ModelClass("Ravi",R.drawable.aabc,22));
        userlist.add(new ModelClass("Rohit",R.drawable.aax,23));
        userlist.add(new ModelClass("Ravi",R.drawable.abb,24));
        userlist.add(new ModelClass("Rohit",R.drawable.ab,25));
        userlist.add(new ModelClass("Ravi",R.drawable.abc,26));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,27));
        userlist.add(new ModelClass("Rahul",R.drawable.a,20));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,21));
        userlist.add(new ModelClass("Ravi",R.drawable.aabc,22));
        userlist.add(new ModelClass("Rohit",R.drawable.aax,23));
        userlist.add(new ModelClass("Ravi",R.drawable.abb,24));
        userlist.add(new ModelClass("Rohit",R.drawable.ab,25));
        userlist.add(new ModelClass("Ravi",R.drawable.abc,26));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,27));
        userlist.add(new ModelClass("Rahul",R.drawable.a,20));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,21));
        userlist.add(new ModelClass("Ravi",R.drawable.aabc,22));
        userlist.add(new ModelClass("Rohit",R.drawable.aax,23));
        userlist.add(new ModelClass("Ravi",R.drawable.abb,24));
        userlist.add(new ModelClass("Rohit",R.drawable.ab,25));
        userlist.add(new ModelClass("Ravi",R.drawable.abc,26));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,27));
        userlist.add(new ModelClass("Rahul",R.drawable.a,20));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,21));
        userlist.add(new ModelClass("Ravi",R.drawable.aabc,22));
        userlist.add(new ModelClass("Rohit",R.drawable.aax,23));
        userlist.add(new ModelClass("Ravi",R.drawable.abb,24));
        userlist.add(new ModelClass("Rohit",R.drawable.ab,25));
        userlist.add(new ModelClass("Ravi",R.drawable.abc,26));
        userlist.add(new ModelClass("Rohit",R.drawable.aabb,27));

        RecyclerAdapter recyclerAdapter= new RecyclerAdapter(userlist, getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);
    }
}
