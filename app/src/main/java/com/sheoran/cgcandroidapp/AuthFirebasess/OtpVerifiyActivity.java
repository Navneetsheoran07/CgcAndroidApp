package com.sheoran.cgcandroidapp.AuthFirebasess;

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

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.sheoran.cgcandroidapp.R;

import java.util.concurrent.TimeUnit;

public class OtpVerifiyActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText edtphone,edtotp;

    Button verifyOtpbtn, grenateOtpBtn;

    String verificationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_verifiy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtotp = findViewById(R.id.idEdtOtp);
        edtphone= findViewById(R.id.idEdtPhoneNumber);
        verifyOtpbtn= findViewById(R.id.idBtnVerify);
        grenateOtpBtn= findViewById(R.id.idBtnGetOtp);

        mAuth = FirebaseAuth.getInstance();
        
        
        grenateOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = "+91"+edtphone.getText().toString().trim();
                sendVerificationCode(phone);
            }
        });
        
        
        verifyOtpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = edtotp.getText().toString().trim();
                verifyCode(code);
            }
        });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential  credential = PhoneAuthProvider.getCredential(verificationid,code);
        signINWithCredential(credential);
    }

    private void signINWithCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(OtpVerifiyActivity.this,"Authentication Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(OtpVerifiyActivity.this,"Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendVerificationCode(String phone) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallback)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if (code!=null){
                edtotp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid=s;
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(OtpVerifiyActivity.this,"Failed due to "+ e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };


}