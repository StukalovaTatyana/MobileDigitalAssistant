package ru.asu.mobiledigitalassistant.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;


public interface VdaApi {
    @POST("/api/bot")
    Call<BotQuestion> askBot(@Body BotQuestion request);

    @GET("/api/event-types")
    Call<List<EventTypes>> getEventTypes();
}

