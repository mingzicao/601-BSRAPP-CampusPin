package com.example.prectrum.campuspin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prectrum on 12/5/17.
 */

public class User {
    public String username;
    private List<Event> searchEvents;
    private List<Event>favs;

    public User(String username) {
        this.username = username;
        this.favs = new ArrayList<>();
        this.searchEvents = new ArrayList<>();

    }
    private void setFav(Event event) {
        favs.add(event);
    }
    private void addHistory (Event event) {
        searchEvents.add(event);
    }
}
