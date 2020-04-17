package ru.asu.mobiledigitalassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;
import ru.asu.mobiledigitalassistant.pojo.MessageWrapper;
import ru.asu.mobiledigitalassistant.pojo.User;

public class ChatActivity extends AppCompatActivity {

    private MessageInput messageInput;
    private MessagesList messagesList;
    MessagesListAdapter<MessageWrapper> adapter;
    private IUser currentUser;
    private IUser botUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageInput = findViewById(R.id.input);
        messagesList = findViewById(R.id.messagesList);

        configUsers();

        messageInput.setInputListener(input -> {
            sendMessage(input.toString());
            return true;
        });

        adapter = new MessagesListAdapter<>(currentUser.getId(), null);
        messagesList.setAdapter(adapter);

        // TODO: 16.04.2020 Добавить базу данных для хранения сообщений
        // getPreviousMessages();
    }

    private void sendMessage(String message) {
        BotQuestion request = new BotQuestion();
        request.setMessage(message);
        adapter.addToStart(new MessageWrapper(currentUser, request), true);
        NetworkService.getInstance()
                .getVdaApi()
                .askBot(request)
                .enqueue(new Callback<BotQuestion>() {
                    @Override
                    public void onResponse(@NonNull Call<BotQuestion> call, @NonNull Response<BotQuestion> response) {
                        BotQuestion botQuestion = response.body();


                        System.out.println(botQuestion.getMessage() + "\n");
                        adapter.addToStart(new MessageWrapper(botUser, botQuestion), true);


                    }

                    @Override
                    public void onFailure(@NonNull Call<BotQuestion> call, @NonNull Throwable t) {

                        System.out.println("Error occurred while getting request!");
                        t.printStackTrace();
                    }
                });
    }

    private void configUsers() {
        // TODO: 16.04.2020 Разобраться с пользователями, не меняет пользователя 
        currentUser = new User(UUID.randomUUID().toString(), "me", "");
        botUser = new User(UUID.randomUUID().toString(), "bot", "");
    }
}
