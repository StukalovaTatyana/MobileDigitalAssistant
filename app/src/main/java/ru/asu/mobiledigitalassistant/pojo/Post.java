package ru.asu.mobiledigitalassistant.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("response")
    @Expose
    private String body;

    public String getBody(){
        return body;
    }

    public void setBody(String body){
        this.body = body;
    }
}
