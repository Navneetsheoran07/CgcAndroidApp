package com.sheoran.cgcandroidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RadioActivity extends AppCompatActivity {
    RadioGroup    rdgp;
    RadioButton  radioButton;
    Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rdgp = findViewById(R.id.radioGroup);

        button = findViewById(R.id.buttonrd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= rdgp.getCheckedRadioButtonId();
                radioButton=findViewById(id);
                if (id==-1){
                    Toast.makeText(RadioActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RadioActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}