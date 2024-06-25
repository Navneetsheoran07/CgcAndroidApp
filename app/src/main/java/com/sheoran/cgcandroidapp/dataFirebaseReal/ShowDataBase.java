package com.sheoran.cgcandroidapp.dataFirebaseReal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sheoran.cgcandroidapp.R;

import java.util.ArrayList;

public class ShowDataBase extends AppCompatActivity {

    ListView lv;
    ArrayList<String> employeeArrayList;
    String userId;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data_base);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        lv = findViewById(R.id.lv);
        employeeArrayList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference("Employee");

     ShowDataAll();

    }

    private void ShowDataAll() {

employeeArrayList.clear();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,employeeArrayList);
        lv.setAdapter(adapter);
       reference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



              for(DataSnapshot ds :dataSnapshot.getChildren()){

                  String value = ds.getValue(String.class);
                  employeeArrayList.add(value);
                  adapter.notifyDataSetChanged();
              }
           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               adapter.notifyDataSetChanged();
           }
       });
    }
}