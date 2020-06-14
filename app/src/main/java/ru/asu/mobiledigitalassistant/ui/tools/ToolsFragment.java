package ru.asu.mobiledigitalassistant.ui.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;

import ru.asu.mobiledigitalassistant.pojo.EventTypes;

public class ToolsFragment extends ListFragment {

    private ToolsViewModel toolsViewModel;

    private ArrayAdapter<EventTypes> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);

        mAdapter = new ArrayAdapter(
                getActivity(),
                android.R.layout.simple_list_item_1,
                toolsViewModel.getEventTypes().toArray()
        );
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getContext(), l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}