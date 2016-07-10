package com.example.travlr;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

        enterLocation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v.getRootView());

                    TranslateAnimation animation = new TranslateAnimation(0, -2000, 100, 100);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(1000);
                    animation.setFillAfter(false);
                    animation.setFillAfter(true);
                    animation.setStartOffset(300);

                    arrow.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);


                }
            }
        });


        enterLocation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    TranslateAnimation animation = new TranslateAnimation(0, -2000, 100, 100);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(800);
                    animation.setFillAfter(false);
                    animation.setFillAfter(true);
                    animation.setStartOffset(300);

                    arrow.setVisibility(View.VISIBLE);
                    arrow.startAnimation(animation);
                }
                return false;
            }
        });

        return view;

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)
                getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
