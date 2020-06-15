package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventTypes {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nameEventType")
    @Expose
    private String nameEventType;

    @SerializedName("events")
    @Expose
    private List<Event> events;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEventType() {
        return nameEventType;
    }

    public void setNameEventType(String nameEventType) {
        this.nameEventType = nameEventType;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return nameEventType;
    }
}
