package com.sheoran.cgcandroidapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText name,email,number,password;
Button submit;
TextView textView ,textView2,textView3,textView4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name= findViewById(R.id.name);
        submit= findViewById(R.id.submit);
        textView= findViewById(R.id.textView);
        email = findViewById(R.id.editTextTextEmailAddress);
        password= findViewById(R.id.editTextTextPassword);
        number = findViewById(R.id.editTextText);
        textView2 = findViewById(R.id.textView6);
        textView3 = findViewById(R.id.textView7);
        textView4 = findViewById(R.id.textView8);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = name.getText().toString().trim();
                String nm = number.getText().toString();

                String data = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                textView.setText(names);
                textView2.setText(data);
                textView3.setText(nm);
                textView4.setText(pass);
            }
        });


    }
}