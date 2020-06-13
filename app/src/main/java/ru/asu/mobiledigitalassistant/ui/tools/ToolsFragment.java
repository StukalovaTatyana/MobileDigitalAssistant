package ru.asu.mobiledigitalassistant.ui.tools;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import ru.asu.mobiledigitalassistant.pojo.EventTypes;

public class ToolsFragment extends ListFragment {

    private ToolsViewModel toolsViewModel;

    private ArrayAdapter<String> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);

        mAdapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                toolsViewModel.getEventTypes().stream()
                        .map(EventTypes::getNameEventType)
                        .toArray()
        );
        setListAdapter(mAdapter);
    }
}