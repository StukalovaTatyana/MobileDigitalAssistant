package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Instant;

public class Forms {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("createDate")
    @Expose
    private Instant createDate;

    @SerializedName("client")
    @Expose
    private Client client;

    @SerializedName("event")
    @Expose
    private Event event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
