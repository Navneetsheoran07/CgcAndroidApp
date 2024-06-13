package com.sheoran.cgcandroidapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SharedPrfActivity extends AppCompatActivity {
EditText editname,editpass;
Button setbtn,getbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_prf);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editname = findViewById(R.id.editTextText3);
        editpass = findViewById(R.id.editTextTextPassword2);
        setbtn = findViewById(R.id.button14);
        getbtn = findViewById(R.id.button15);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        setbtn.setOnClickListener(v -> {

           myEdit.putString("Name",editname.getText().toString());
           myEdit.putString("Password",editpass.getText().toString());
           myEdit.commit();
            Toast.makeText(this, "Data Save  Successfully", Toast.LENGTH_SHORT).show();
       editname.setText("");
       editpass.setText("");

        });
        getbtn.setOnClickListener(v -> {


            String name = sharedPreferences.getString("Name", "No Name");
            String password = sharedPreferences.getString("Password", "No Password");
            editname.setText(name);
            editpass.setText(password);
        });
    }
}