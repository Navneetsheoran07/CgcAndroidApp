package com.sheoran.cgcandroidapp.AuthFirebasess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.sheoran.cgcandroidapp.MainActivity;
import com.sheoran.cgcandroidapp.R;

public class GoogleAuthActivity extends AppCompatActivity {
GoogleSignInClient googleSignInClient ;

    SignInButton signInButton ;
    FirebaseAuth firebaseAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_google_auth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        signInButton= findViewById(R.id.bt_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("554665852899-g29itnagqlfeprohb3v5fm7vvuedij62.apps.googleusercontent.com")
                .requestEmail()
                .build();
googleSignInClient= GoogleSignIn.getClient(this,signInOptions);
signInButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Intent intent = googleSignInClient.getSignInIntent();
startActivityForResult(intent,180);
    }


});
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if (firebaseUser!=null){
            startActivity(new Intent(GoogleAuthActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==180){
            Task<GoogleSignInAccount> signInAccountTask= GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()){
                String s = "Google Sign inSuccessful";
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

                try {
                    GoogleSignInAccount googleSignInAccount= signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential= GoogleAuthProvider.getCredential(googleSignInAccount
                                .getIdToken(),null);

                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this,
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isComplete()){
                                            startActivity(new Intent(GoogleAuthActivity.this,MainActivity.class)
                                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                        }else {
                                            Toast.makeText(GoogleAuthActivity.this, "Authentication Failed: "+task.getException()
                                                    , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }


                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}