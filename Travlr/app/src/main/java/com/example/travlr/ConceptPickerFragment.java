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

        //rowListItem = Results.getInstance().;
        lLayout = new GridLayoutManager(container.getContext(), 2);

        RecyclerView rView = (RecyclerView)view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(container.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

        return view;
    }
}
