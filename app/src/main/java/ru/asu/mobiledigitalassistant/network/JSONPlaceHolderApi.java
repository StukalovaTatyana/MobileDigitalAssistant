package ru.asu.mobiledigitalassistant.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.asu.mobiledigitalassistant.pojo.Post;


public interface JSONPlaceHolderApi {
    @GET("/api/helloWorld")
    Call<Post> getPostWithId();
}
