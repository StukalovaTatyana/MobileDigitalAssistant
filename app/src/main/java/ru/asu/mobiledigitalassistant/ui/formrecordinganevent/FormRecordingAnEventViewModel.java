package ru.asu.mobiledigitalassistant.ui.formrecordinganevent;

import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.Event;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;
import ru.asu.mobiledigitalassistant.pojo.Faculty;
import ru.asu.mobiledigitalassistant.pojo.Forms;
import ru.asu.mobiledigitalassistant.pojo.Group;

public class FormRecordingAnEventViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public FormRecordingAnEventViewModel(){
    }

    public List<Faculty> getFaculties() {
        CompletableFuture<List<Faculty>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<List<Faculty>> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .getFaculties()
                        .execute();
                List<Faculty> body = execute.body();
                return body;
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

    public List<Group> getFaculty(int id) {
        CompletableFuture<List<Group>> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<Faculty> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .getFaculty(id)
                        .execute();
                return execute.body().getListGroups();
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

    public Forms createForm(Forms forms) {
        CompletableFuture<Forms> future = CompletableFuture.supplyAsync(() -> {
            try {
                Response<Forms> execute = NetworkService.getInstance()
                        .getVdaApi()
                        .createForm(forms)
                        .execute();
                return execute.body();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
        try {
            return future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}