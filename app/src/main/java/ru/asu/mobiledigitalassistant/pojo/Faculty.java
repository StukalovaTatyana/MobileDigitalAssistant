package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Faculty {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nameFaculty")
    @Expose
    private String nameFaculty;

    @SerializedName("groups")
    @Expose
    private List<Group> listGroups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

    public List<Group> getListGroups() {
        return listGroups;
    }

    public void setListGroups(List<Group> listGroups) {
        this.listGroups = listGroups;
    }

    @Override
    public String toString() {
        return nameFaculty;
    }
}
