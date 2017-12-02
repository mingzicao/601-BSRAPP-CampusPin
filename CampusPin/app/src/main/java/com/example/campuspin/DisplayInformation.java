package com.example.campuspin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);
        Intent intent = getIntent();
        String searchString = intent.getStringExtra("abc");
        // Get the Intent that started this activity and extract the string
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(searchString.toString());
    }
}
