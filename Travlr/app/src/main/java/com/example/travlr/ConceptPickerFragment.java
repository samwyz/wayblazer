package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micahherrera on 7/9/16.
 */
public class ConceptPickerFragment extends Fragment {

    private GridLayoutManager lLayout;
    private List<String> rowListItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.concept_grid_fragment, container, false);

        if(Results.getInstance().getSearchObject().getmLocation().toString().equalsIgnoreCase("austin, tx")) {
            rowListItem = new ArrayList<String>();
            rowListItem.add("austin_dining");
            rowListItem.add("austin_historical_culture");
            rowListItem.add("austin_music");
            rowListItem.add("austin_nature");
            rowListItem.add("austin_nightlife");
            rowListItem.add("austin_sports");
        }

        if(Results.getInstance().getSearchObject().getmLocation().toString().equalsIgnoreCase("berlin, germany")) {
            rowListItem = new ArrayList<String>();
            rowListItem.add("berlin_dining");
            rowListItem.add("berlin_culture_history");
            rowListItem.add("berlin_music");
            rowListItem.add("berlin_nature");
            rowListItem.add("berlin_nightlife");
            rowListItem.add("berlin_sports");
        }

        if(Results.getInstance().getSearchObject().getmLocation().toString().equalsIgnoreCase("auckland, nz")) {
            rowListItem = new ArrayList<String>();
            rowListItem.add("auckland_dining");
            rowListItem.add("auckland_culture");
            rowListItem.add("auckland_live_music");
            rowListItem.add("auckland_nature");
            rowListItem.add("auckland_nightlife");
            rowListItem.add("auckland_sports");
        }
        lLayout = new GridLayoutManager(container.getContext(), 2);

        RecyclerView rView = (RecyclerView)view.findViewById(R.id.recycler_view);
        //rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(container.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

        return view;
    }
}
