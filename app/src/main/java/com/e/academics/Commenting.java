package com.e.academics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Commenting extends AppCompatActivity {
    private  String post,postId;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commenting);

        recyclerView = (RecyclerView)findViewById(R.id.recyClerCommenting);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        post = getIntent().getExtras().getString("post");
        setTitle(post);
    }
}
