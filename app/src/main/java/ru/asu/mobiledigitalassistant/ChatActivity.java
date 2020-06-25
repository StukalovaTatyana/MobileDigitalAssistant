package ru.asu.mobiledigitalassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;
import ru.asu.mobiledigitalassistant.pojo.db.DbContract;
import ru.asu.mobiledigitalassistant.pojo.db.DbHelper;
import ru.asu.mobiledigitalassistant.pojo.MessageWrapper;
import ru.asu.mobiledigitalassistant.pojo.User;

public class ChatActivity extends AppCompatActivity {

    private SimpleDateFormat simpleDateFormat;
    private MessageInput messageInput;
    private MessagesList messagesList;
    MessagesListAdapter<MessageWrapper> adapter;
    private IUser currentUser;
    private IUser botUser;
    private DbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        messageInput = findViewById(R.id.input);
        messagesList = findViewById(R.id.messagesList);

        configUsers();

        messageInput.setInputListener(input -> {
            sendMessage(input.toString());
            return true;
        });

        adapter = new MessagesListAdapter<>(currentUser.getId(), null);
        messagesList.setAdapter(adapter);

        dbHelper = new DbHelper(getBaseContext());
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        getPreviousMessages();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendMessage(String message) {
        BotQuestion request = new BotQuestion();
        request.setMessage(message);
        Date myMessageDate = new Date();
        adapter.addToStart(new MessageWrapper(UUID.randomUUID().toString(), currentUser, message, myMessageDate), true);

        try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DbContract.Message.COLUMN_NAME_SENDER, currentUser.getName());
            contentValues.put(DbContract.Message.COLUMN_NAME_TEXT, message);
            contentValues.put(DbContract.Message.COLUMN_NAME_TIME, myMessageDate.getTime());

            database.insert(DbContract.Message.TABLE_NAME, null, contentValues);
            NetworkService.getInstance()
                    .getVdaApi()
                    .askBot(request)
                    .enqueue(new Callback<BotQuestion>() {
                        @Override
                        public void onResponse(@NonNull Call<BotQuestion> call, @NonNull Response<BotQuestion> response) {
                            BotQuestion botQuestion = response.body();
                            System.out.println(botQuestion.getMessage() + "\n");
                            Date botMessageDate = new Date();
                            try (SQLiteDatabase database = dbHelper.getWritableDatabase()) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(DbContract.Message.COLUMN_NAME_SENDER, botUser.getName());
                                contentValues.put(DbContract.Message.COLUMN_NAME_TEXT, botQuestion.getMessage());
                                contentValues.put(DbContract.Message.COLUMN_NAME_TIME, botMessageDate.getTime());
                                long insertBotMessageId = database.insert(DbContract.Message.TABLE_NAME, null, contentValues);
                                adapter.addToStart(new MessageWrapper(String.valueOf(insertBotMessageId), botUser, botQuestion.getMessage(), botMessageDate), true);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<BotQuestion> call, @NonNull Throwable t) {
                            System.out.println("Error occurred while getting request!");
                            t.printStackTrace();
                        }
                    });
        }
    }

    private void configUsers() {
        currentUser = new User(UUID.randomUUID().toString(), "me", "");
        botUser = new User(UUID.randomUUID().toString(), "bot", "");
    }

    private void getPreviousMessages() {
        try (SQLiteDatabase database = dbHelper.getReadableDatabase()) {
            Cursor cursor = database.query(
                    DbContract.Message.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DbContract.Message._ID));
                String sender = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.Message.COLUMN_NAME_SENDER));
                String text = cursor.getString(cursor.getColumnIndexOrThrow(DbContract.Message.COLUMN_NAME_TEXT));
                Date time = new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DbContract.Message.COLUMN_NAME_TIME)));
                if (sender.equals(currentUser.getName())) {
                    adapter.addToStart(new MessageWrapper(String.valueOf(id), currentUser, text, time), true);
                } else {
                    adapter.addToStart(new MessageWrapper(String.valueOf(id), botUser, text, time), true);
                }

            }
        }
    }
}
