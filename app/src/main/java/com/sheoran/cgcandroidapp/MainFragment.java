package com.sheoran.cgcandroidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_main,container,false);

        textView=view.findViewById(R.id.text_vies);

        String sTittle = getArguments().getString("tittle");
        textView.setText(sTittle);
        return view; }
}