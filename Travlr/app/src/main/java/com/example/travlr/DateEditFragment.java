package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by micahherrera on 7/9/16.
 */
public class DateEditFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    private SimpleDateFormat dateFormatter;
    private Calendar startDateCalendar;
    private Calendar endDateCalendar;
    private int datePickerID;
    private DialogFragment datePicker1;
    private DialogFragment datePicker2;
    private TimeZone tz;
    private DateFormat df;
    public String currentStartDate;
    public String currentEndDate;
    TextView startDate;
    TextView endDate;
    ImageView imageArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.date_edit_fragment, container, false);

        dateFormatter = new SimpleDateFormat("E, dd MMM yyyy", Locale.US);
        startDateCalendar = Calendar.getInstance();
        endDateCalendar = Calendar.getInstance();

        startDate = (TextView) view.findViewById(R.id.startDate_string);
        endDate = (TextView) view.findViewById(R.id.endDate_string);

        startDate.setText(dateFormatter.format(startDateCalendar.getTime()));
        endDate.setText(dateFormatter.format(endDateCalendar.getTime()));
        imageArrow = (ImageView) view.findViewById(R.id.date_image);





        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker1 = DatePickerFragment.newInstance(new Date());
                datePicker1.show(getFragmentManager(), "DATE");
                datePickerID = 1;

            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker2 = DatePickerFragment.newInstance(new Date());
                datePicker2.show(getFragmentManager(), "DATE");
                datePickerID = 2;


            }
        });

        return view;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        if(datePickerID==1){
            startDate.setText(dateFormatter.format(c.getTime()));
            //currentStartDate = df.format(c.getTime());
            Log.d("dateString", "onDateSet: "+currentStartDate);

            tz = TimeZone.getTimeZone("UTC");
            df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
            df.setTimeZone(tz);
            currentStartDate = df.format(c.getTime());

        }
        else if(datePickerID==2){
            endDate.setText(dateFormatter.format(c.getTime()));
            //currentEndDate = df.format(c.getTime());
            Log.d("dateString2", "onDateSet: "+currentEndDate);
            TranslateAnimation animation = new TranslateAnimation(0, 1000, 130, 100);
            animation.setInterpolator(new LinearInterpolator());
            animation.setDuration(800);
            animation.setFillAfter(false);
            animation.setFillAfter(true);

            imageArrow.setVisibility(View.VISIBLE);
            imageArrow.startAnimation(animation);

            tz = TimeZone.getTimeZone("UTC");
            df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmZ");
            df.setTimeZone(tz);
            currentEndDate = df.format(c.getTime());
        }


    }

}
