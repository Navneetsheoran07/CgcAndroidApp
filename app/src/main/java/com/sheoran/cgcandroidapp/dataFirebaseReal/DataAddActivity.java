package com.sheoran.cgcandroidapp.dataFirebaseReal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sheoran.cgcandroidapp.R;

public class DataAddActivity extends AppCompatActivity {
    private EditText employeeNameEdt, employeePhoneEdt, employeeAddressEdt;
    private Button sendDatabtn, show;
    EmployeeInfo employeeInfo;
    String employeeId;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        employeeNameEdt = findViewById(R.id.idEdtEmployeeName);
        employeePhoneEdt = findViewById(R.id.idEdtEmployeePhoneNumber);
        employeeAddressEdt = findViewById(R.id.idEdtEmployeeAddress);

        employeeInfo = new EmployeeInfo();
        sendDatabtn = findViewById(R.id.idBtnSendData);

        show = findViewById(R.id.button);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Employee");
        sendDatabtn.setOnClickListener(v -> {
            String name = employeeNameEdt.getText().toString();
            String phone = employeePhoneEdt.getText().toString();
            String address = employeeAddressEdt.getText().toString();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
                Toast.makeText(DataAddActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            } else {
                AddDataBase(name, phone, address);
            }
        });


        show.setOnClickListener(v -> {
            Intent intent = new Intent(DataAddActivity.this, ShowDataBase.class);
            startActivity(intent);
        });
    }

    private void AddDataBase(String name, String phone, String address) {


        employeeInfo.setEmployeeName(name);
        employeeInfo.setEmployeeContactNumber(phone);
        employeeInfo.setEmployeeAddress(address);
        employeeId=name;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myRef.child(employeeId).setValue(employeeInfo);
                Toast.makeText(DataAddActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DataAddActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }



}