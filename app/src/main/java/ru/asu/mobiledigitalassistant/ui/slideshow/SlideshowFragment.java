package ru.asu.mobiledigitalassistant.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ru.asu.mobiledigitalassistant.R;
import ru.asu.mobiledigitalassistant.pojo.EventTypes;
import ru.asu.mobiledigitalassistant.pojo.Teachers;
import ru.asu.mobiledigitalassistant.ui.events.EventsListActivity;
import ru.asu.mobiledigitalassistant.ui.tools.ToolsViewModel;

public class SlideshowFragment extends ListFragment {

    private SlideshowViewModel slideshowViewModel;

    private ArrayAdapter<Teachers> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        mAdapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                slideshowViewModel.getTeachers().toArray()
        );
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Teachers teachers = (Teachers) l.getItemAtPosition(position);
        Intent intent = new Intent(getContext(), Teachers.class);
        intent.putExtra("id", teachers.getId());
        startActivity(intent);
    }
}