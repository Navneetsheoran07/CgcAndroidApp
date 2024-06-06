package com.sheoran.cgcandroidapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MsgActivity extends AppCompatActivity {

    EditText ms,nm;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_msg);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ms = findViewById(R.id.editTextText2);
        nm = findViewById(R.id.editTextPhone2);
        button = findViewById(R.id.button4);
        button.setOnClickListener(v -> {
          //  String message = ms.getText().toString();
          //  String number = nm.getText().toString();
//            Intent intent = new Intent(Intent.ACTION_SENDTO);
//            intent.setData(Uri.parse("smsto:" + number));
//            intent.putExtra("sms_body", message);
//            startActivity(intent);

            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
              sendSms();
            }
            else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 143);
            }



        });
    }

    private void sendSms() {

        try{
        String message = ms.getText().toString();
        String number = nm.getText().toString();


        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, null, null);
        Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();
    }
        catch (Exception e){
            Toast.makeText(this, "Message Not Send", Toast.LENGTH_SHORT).show();
        }
    }
}