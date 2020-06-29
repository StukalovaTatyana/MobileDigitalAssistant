package ru.asu.mobiledigitalassistant.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;
import ru.asu.mobiledigitalassistant.pojo.Teachers;

public class SlideshowViewModel extends ViewModel {

    public SlideshowViewModel() {
    }

    public List<Teachers> getTeachers() {
        CompletableFuture<List<Teachers>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<Teachers>> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .getTeachers()
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