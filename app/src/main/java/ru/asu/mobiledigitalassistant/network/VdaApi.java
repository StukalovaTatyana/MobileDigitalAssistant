package ru.asu.mobiledigitalassistant.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;


public interface VdaApi {
    @POST("/api/bot")
    Call<BotQuestion> askBot(@Body BotQuestion request);
}

