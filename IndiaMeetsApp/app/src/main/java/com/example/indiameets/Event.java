package com.example.indiameets;

/**
 * Created by grashmi on 11/19/2015.
 */
public class Event {
    String description,name ,date,time,location;
    public Event(String name, String description, String date, String time, String location) {
        this.name=name;
        this.description=description;
        this.time=time;
        this.location=location;
    }
}