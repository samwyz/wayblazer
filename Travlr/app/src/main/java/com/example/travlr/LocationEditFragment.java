package com.example.travlr;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by samwyz on 7/9/16.
 */
public class LocationEditFragment extends Fragment {

    TextView locationQuestion;
    EditText enterLocation;
    ImageView arrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.location_edit_fragment, container, false);

        locationQuestion = (TextView) view.findViewById(R.id.location_question);
        arrow = (ImageView) view.findViewById(R.id.location_arrow);
        enterLocation = (EditText) view.findViewById(R.id.location_editText);


        enterLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                TranslateAnimation animation = new TranslateAnimation(0, 1000, 130, 100);
                animation.setInterpolator(new LinearInterpolator());
                animation.setDuration(800);
                animation.setFillAfter(false);
                animation.setFillAfter(true);
                animation.setStartOffset(300);

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    arrow.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                }
                return false;
            }
        });

        return view;

    }
}
