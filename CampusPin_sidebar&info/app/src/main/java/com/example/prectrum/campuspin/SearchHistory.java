package com.example.prectrum.campuspin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class SearchHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1 intent; (pass user)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        // Get ListView object from xml.
        ListView eventListView = (ListView) findViewById(R.id.event_list);

        EventAdapter adapter = new EventAdapter(this);

        // Assign adapter to ListView.
        eventListView.setAdapter(adapter);

    }
    private String[] getEventNames () {
        String[] names = {
                "XXX", "XXX", "XXX",
                "XXX", "XXX", "XXX",
                "XXX", "XXX", "XXX",
                "Event10", "Event11", "Event12"};
        return names;

    }
    private void displayHistory() { // input: user out: history;
        //list view insert ......

    }
}
