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
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {
Button  button;
ListView listView;
VideoView videoView;

    ArrayList<String>alpath;
    ArrayList<String>alname;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button= findViewById(R.id.button11);
        listView = findViewById(R.id.videolistview);
        videoView = findViewById(R.id.videoView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED) {
                    ReadVideo();
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.READ_MEDIA_VIDEO}, 134);
                }

            }
        });

    }
    @SuppressLint("Range")
    private void ReadVideo() {
        alpath = new ArrayList<>();
        alname = new ArrayList<>();

        Uri musicuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        ContentResolver resolver = getContentResolver();


          Cursor cursor =resolver.query(musicuri,null,null,null,null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID));

                Uri uri = ContentUris.withAppendedId(musicuri, id);
                alname.add(name);
                alpath.add(String.valueOf(uri));

                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alpath);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String paths = alpath.get(position).toString();
                        Toast.makeText(VideoActivity.this, paths, Toast.LENGTH_SHORT).show();
                        PlayVideo(paths);


                    }
                });


            }

        }
    }

    private void PlayVideo(String paths) {
        videoView.setVideoURI(Uri.parse(paths));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();


    }
}