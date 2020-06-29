package ru.asu.mobiledigitalassistant.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;
import ru.asu.mobiledigitalassistant.pojo.Faculty;
import ru.asu.mobiledigitalassistant.pojo.Forms;
import ru.asu.mobiledigitalassistant.pojo.Teachers;


public interface VdaApi {
    @POST("/api/bot")
    Call<BotQuestion> askBot(@Body BotQuestion request);

    @GET("/api/event-types")
    Call<List<EventTypes>> getEventTypes();

    @GET("/api/event-types/{id}/actual")
    Call<EventTypes> getEventType(@Path("id") int id);

    @POST("/api/forms")
    Call<Forms> createForm(@Body Forms forms);

    @GET("/api/faculties")
    Call<List<Faculty>> getFaculties();

    @GET("/api/faculties/{id}")
    Call<Faculty> getFaculty(@Path("id") int id);

    @GET("/api/teachers")
    Call<List<Teachers>> getTeachers();
}

