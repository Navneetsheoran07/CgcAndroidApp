package com.sheoran.cgcandroidapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.P)
public class BioMatricCallBacks extends BiometricPrompt.AuthenticationCallback {

    Context context;

    public BioMatricCallBacks(Context context) {
        this.context = context;
    }

    @Override
    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        Toast.makeText(context, "Successfully Ver", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        Toast.makeText(context, "error"+errorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context, "please try again", Toast.LENGTH_SHORT).show();
    }
}
