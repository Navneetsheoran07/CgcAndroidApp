package com.sheoran.cgcandroidapp.AuthFirebasess;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sheoran.cgcandroidapp.R;

public class RegisterActivity extends AppCompatActivity {
EditText email ,psd ,cmfdpsd;
Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        email = findViewById(R.id.editTextTextEmailAddress2);
        psd = findViewById(R.id.editTextTextPassword3);
        cmfdpsd = findViewById(R.id.editTextTextPassword4);
        button = findViewById(R.id.button16);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString();
                String psd1 = psd.getText().toString();
                String cmfdpsd1 = cmfdpsd.getText().toString();


                if (psd1.equals(cmfdpsd1)) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
               firebaseAuth.createUserWithEmailAndPassword(email1,psd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {


                       Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                       startActivity(intent);
                   }


               }
               );
                }
                else{

                    Toast.makeText(RegisterActivity.this, "password not matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}