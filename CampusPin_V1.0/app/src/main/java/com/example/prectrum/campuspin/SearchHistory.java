package com.example.prectrum.campuspin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SearchHistory extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1 intent; (pass user)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        mListView = (ListView)findViewById(R.id.listView);
        displayHistory();
    }
    private void displayHistory() {
        //list view insert ......

    }
}
