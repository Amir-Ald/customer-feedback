package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDatabase database;

    EditText nameET;
    EditText commentET;
    RatingBar ratingBar;
    //MutableLiveData<List<User>> users = new MutableLiveData<>();
    static int id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = MyDatabase.getInstance(this);

        nameET= findViewById(R.id.nameET);
        commentET = findViewById(R.id.commentET);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);

    }

    public void saveFeedback(View view) {

        String name = nameET.getText().toString();
        String comment = commentET.getText().toString();
        float rating = ratingBar.getRating();

        if (name != "" && comment != "" && rating != 0.0f){
            User user= new User(id, name, rating, comment);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    database.userDao().addFeedback(user);
                }
            }).start();

            id++;

            nameET.setText("");
            commentET.setText("");
            ratingBar.setRating(0.0f);
        }else {
            Toast.makeText(this, "Please enter every field", Toast.LENGTH_SHORT).show();
        }

    }

    public void displayFeedback(View view) {
        Intent intent= new Intent(this, DisplayData.class);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //users.postValue(database.userDao().getAll());
                List<User> feedbackList = database.userDao().getAll();
                intent.putExtra("FeedbackList", (Serializable) feedbackList);
                startActivity(intent);
            }
        }).start();


    }
}