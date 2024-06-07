package com.sheoran.cgcandroidapp.RetrofitApiDatas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sheoran.cgcandroidapp.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiActivity extends AppCompatActivity {
TextView textView;
ApiService apiService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.apitxt);
        apiService=ApiClient.getRetrofit().create(ApiService.class);
        Call<ArrayList<ApiModel>> call = apiService.getpostlist();
        call.enqueue(new Callback<ArrayList<ApiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ApiModel>> call, Response<ArrayList<ApiModel>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(ApiActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
              return;

                }
                StringBuilder stringBuilder  = new StringBuilder();
                for (ApiModel apiModel : response.body()) {
                   stringBuilder.append("Id => " + apiModel.getId() + "\n");
                    stringBuilder.append("Title => " + apiModel.getTitle() + "\n");
                    stringBuilder.append("Body => " + apiModel.getBody() + "\n");
                    stringBuilder.append("User Id => " + apiModel.getUserId() + "\n");
                    textView.setText(stringBuilder.toString());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ApiModel>> call, Throwable throwable) {

                Toast.makeText(ApiActivity.this, "error"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}