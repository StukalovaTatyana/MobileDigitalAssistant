package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("patronumic")
    @Expose
    private String patronumic;

    @SerializedName("phone")
    @Expose
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronumic() {
        return patronumic;
    }

    public void setPatronumic(String patronumic) {
        this.patronumic = patronumic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
