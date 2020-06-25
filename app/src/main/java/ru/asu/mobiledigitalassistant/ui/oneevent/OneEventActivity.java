package ru.asu.mobiledigitalassistant.ui.oneevent;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

import ru.asu.mobiledigitalassistant.R;

public class OneEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_event);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Мероприятие");


        TextView nameEvent = findViewById(R.id.nameEventTextView);
        TextView eventDescription = findViewById(R.id.eventDescriptionTextView);
        TextView dateBeginEnd = findViewById(R.id.dateBeginEndTextView);

        nameEvent.setText(getIntent().getStringExtra("name"));
        eventDescription.setText(getIntent().getStringExtra("description"));
        dateBeginEnd.setText(MessageFormat.format("Дата проведения: {0}  -  {1}", getIntent().getStringExtra("dateBegin"), getIntent().getStringExtra("dateEnd")));
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
}