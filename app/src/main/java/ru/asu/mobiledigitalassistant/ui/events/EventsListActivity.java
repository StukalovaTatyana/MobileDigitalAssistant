package ru.asu.mobiledigitalassistant.ui.events;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;

import ru.asu.mobiledigitalassistant.R;
import ru.asu.mobiledigitalassistant.pojo.Event;
import ru.asu.mobiledigitalassistant.ui.oneevent.OneEventActivity;

public class EventsListActivity extends ListActivity {

    private EventsListViewModel mViewModel;

    private ArrayAdapter<Event> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        Toolbar toolbar = findViewById(R.id.toolbarEvents);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        toolbar.setNavigationOnClickListener(v -> finish());


        mViewModel = new EventsListViewModel();
        //    mViewModel = new ViewModelProvider(this).get(EventsListViewModel.class);

        mAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                mViewModel.getEventType(getIntent().getIntExtra("id", 0)).toArray()
        );
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        Event event = (Event) l.getItemAtPosition(position);
        Intent intent = new Intent(getApplicationContext(), OneEventActivity.class);
        intent.putExtra("id", event.getId());
        intent.putExtra("name",event.getNameEvent());
        intent.putExtra("description", event.getDescription());
        intent.putExtra("dateBegin", simpleDateFormat.format(event.getBeginDate()));
        intent.putExtra("dateEnd", simpleDateFormat.format(event.getEndDate()));
        startActivity(intent);
    }
}