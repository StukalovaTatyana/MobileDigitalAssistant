package ru.asu.mobiledigitalassistant.ui.tools;

import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;

public class ToolsViewModel extends ViewModel {

    public ToolsViewModel() {
    }

    public List<EventTypes> getEventTypes() {
        CompletableFuture<List<EventTypes>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<EventTypes>> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .getEventTypes()
                        .execute();
                return execute.body();
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