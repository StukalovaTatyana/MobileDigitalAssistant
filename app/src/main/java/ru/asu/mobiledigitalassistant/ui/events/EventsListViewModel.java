package ru.asu.mobiledigitalassistant.ui.events;

import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.Event;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;

public class EventsListViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public EventsListViewModel(){
    }


    public List<Event> getEventType(int id) {
        CompletableFuture<List<Event>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<EventTypes> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .getEventType(id)
                        .execute();
                return execute.body().getEvents();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        });
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}