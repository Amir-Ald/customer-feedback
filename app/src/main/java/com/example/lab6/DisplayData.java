package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class DisplayData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent i = getIntent();
        List<User> list = (List<User>) i.getSerializableExtra("FeedbackList");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedbackRV);
        MyAdapter myAdapter = new MyAdapter(list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    public void closeDisplay(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}