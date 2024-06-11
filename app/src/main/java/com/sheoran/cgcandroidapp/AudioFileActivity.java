package com.sheoran.cgcandroidapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AudioFileActivity extends AppCompatActivity {
Button button;
ListView listView;
ArrayList<String>alpath;
ArrayList<String>alname;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio_file);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        button = findViewById(R.id.button10);
        listView = findViewById(R.id.listViewaudio);
        alpath = new ArrayList<>();
        alname = new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                    ReadMusic();
                }
                else {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 134);
                }
            }
        });
    }
    @SuppressLint("Range")
    private void ReadMusic() {
        Uri musicuri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver =getContentResolver();
    //    String sel= MediaStore.Audio.Media.IS_MUSIC+"!=0";
      //  String shortorder =MediaStore.Audio.Media.TITLE+" ASC";

        //Cursor cursor =resolver.query(musicuri,null,sel,null,shortorder);
        Cursor cursor =resolver.query(musicuri,null,null,null,null);
        if(cursor!=null){
            while(cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));

                Uri uri= ContentUris.withAppendedId(musicuri,id);
                alname.add(name);
                alpath.add(String.valueOf(uri));

                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alpath);
                listView.setAdapter(arrayAdapter);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String paths= alpath.get(position).toString();
                 Toast.makeText(AudioFileActivity.this, paths, Toast.LENGTH_SHORT).show();
             }
         });


            }

        }
    }
}