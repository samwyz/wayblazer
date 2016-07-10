package com.example.travlr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by samwyz on 7/9/16.
 */
public class LocationEditFragment extends Fragment {

    TextView locationQuestion;
    EditText locationChoice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.location_edit_fragment, container, false);

        locationQuestion = (TextView) view.findViewById(R.id.location_question);
        locationChoice = (EditText) view.findViewById(R.id.location_editText);

        return view;

    }
}
