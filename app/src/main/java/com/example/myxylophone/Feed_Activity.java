package com.example.myxylophone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class Feed_Activity extends AppCompatActivity {

    Button submit;
    RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_);

        submit=findViewById(R.id.btn);
        mRatingBar=findViewById(R.id.ratingBar);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = mRatingBar.getRating();
                Intent intent = new Intent(Feed_Activity.this, SecondActivity.class);
                intent.putExtra("star", rating);
                startActivity(intent);
            }
        });



    }
}
