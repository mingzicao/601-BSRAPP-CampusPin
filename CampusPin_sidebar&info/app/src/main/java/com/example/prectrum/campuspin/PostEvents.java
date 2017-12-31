package com.example.prectrum.campuspin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostEvents extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "USERNAME";
    private TextView mUsername;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_events);
        Intent intent = getIntent();
        mUsername = (TextView)findViewById(R.id.username);
        username = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mUsername.setText("Hi, " + username);
    }
}
