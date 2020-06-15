package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nameEvent")
    @Expose
    private String nameEvent;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("beginDate")
    @Expose
    private Date beginDate;

    @SerializedName("endDate")
    @Expose
    private Date endDate;

    @SerializedName("client")
    @Expose
    private Client client;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return nameEvent.toUpperCase() + "\r\n" +
                (description.length() <=30 ? description : description.substring(0, 30)).toLowerCase() + "...";
    }
}
