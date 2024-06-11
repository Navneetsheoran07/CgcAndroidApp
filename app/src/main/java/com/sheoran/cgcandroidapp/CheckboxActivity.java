package com.sheoran.cgcandroidapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckboxActivity extends AppCompatActivity {
Button  button;
CheckBox check1,check2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkbox);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button9);
        check1 = findViewById(R.id.checkBox);
        check2 = findViewById(R.id.checkBox2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked()){
                    Toast.makeText(CheckboxActivity.this," Male", Toast.LENGTH_SHORT).show();
                }
                if(check2.isChecked()){
                    Toast.makeText(CheckboxActivity.this, "female", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}