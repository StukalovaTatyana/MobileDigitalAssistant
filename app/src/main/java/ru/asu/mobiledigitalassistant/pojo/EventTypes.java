package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTypes {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nameEventType")
    @Expose
    private String nameEventType;

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
}
