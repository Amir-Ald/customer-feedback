package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ModifyData extends AppCompatActivity {

    MyDatabase database;
    EditText nameET;
    EditText commentET;
    RatingBar ratingBar;
    String userName;
    static int userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_data);
        database = MyDatabase.getInstance(this);
        nameET= findViewById(R.id.modifyName);
        commentET = findViewById(R.id.modifyComment);
        ratingBar= (RatingBar) findViewById(R.id.modifyrate);

        Intent i = getIntent();

        nameET.setText(i.getStringExtra("feedbackName"));
        userName = i.getStringExtra("feedbackName");

        new Thread(new Runnable() {
            @Override
            public void run() {
                //users.postValue(database.userDao().getAll());
                userId = database.userDao().getId(userName);
                Float rating = database.userDao().getRating(userId);
                String comment = database.userDao().getComment(userId);
                commentET.setText(comment);
                ratingBar.setRating(rating);
            }
        }).start();


    }


    public void deleteFeedback(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.userDao().deleteFeedback(userId);
            }
        }).start();

        Toast.makeText(view.getRootView().getContext(), "Feedback"+ String.valueOf(userId)+" is deleted!", Toast.LENGTH_SHORT).show();

        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void editFeedback(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = nameET.getText().toString();
                Float rating = ratingBar.getRating();
                String comment = commentET.getText().toString();
                database.userDao().editFeedback(name, rating, comment, userId);
            }
        }).start();

        Toast.makeText(view.getRootView().getContext(), "Feedback"+ String.valueOf(userId)+" is modified!", Toast.LENGTH_SHORT).show();
    }

    public void showMain(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}