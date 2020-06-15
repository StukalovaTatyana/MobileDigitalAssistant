package ru.asu.mobiledigitalassistant.ui.events;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import ru.asu.mobiledigitalassistant.R;
import ru.asu.mobiledigitalassistant.pojo.Event;

public class EventsListActivity extends ListActivity {

    private EventsListViewModel mViewModel;

    private ArrayAdapter<Event> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        mViewModel = new EventsListViewModel();
        //    mViewModel = new ViewModelProvider(this).get(EventsListViewModel.class);

        mAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                mViewModel.getEventType(getIntent().getIntExtra("id", 0)).toArray()
        );
        setListAdapter(mAdapter);
    }
}