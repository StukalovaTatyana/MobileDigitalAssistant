package ru.asu.mobiledigitalassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.asu.mobiledigitalassistant.network.NetworkService;
import ru.asu.mobiledigitalassistant.pojo.BotQuestion;

public class BotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        Button button = findViewById(R.id.button);
        final TextView questionField = findViewById(R.id.question_field);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BotQuestion request = new BotQuestion();
                        request.setMessage(questionField.getText().toString());
                        NetworkService.getInstance()
                                .getVdaApi()
                                .askBot(request)
                                .enqueue(new Callback<BotQuestion>() {
                                    @Override
                                    public void onResponse(@NonNull Call<BotQuestion> call, @NonNull Response<BotQuestion> response) {
                                        BotQuestion botQuestion = response.body();

                                        System.out.println(botQuestion.getMessage() + "\n");
                                        questionField.setText("");
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<BotQuestion> call, @NonNull Throwable t) {

                                        System.out.println("Error occurred while getting request!");
                                        t.printStackTrace();
                                    }
                                });

                    }
                }
        );
    }
}
