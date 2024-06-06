package com.sheoran.cgcandroidapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallinActivity3 extends AppCompatActivity {
 EditText editText ;
 Button  dail,call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_callin3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        editText = findViewById(R.id.editTextPhone);
        dail = findViewById(R.id.button2);
        call = findViewById(R.id.button3);
        dail.setOnClickListener(v -> {
           String number = editText.getText().toString();
           Intent intent = new Intent(Intent.ACTION_DIAL);
           intent.setData(Uri.parse("tel:" + number));
           startActivity(intent);

        });
        call.setOnClickListener(v -> {

            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                String number = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 143);
            }



        });
    }
}