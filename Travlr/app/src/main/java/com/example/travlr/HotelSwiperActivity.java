package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class HotelSwiperActivity extends AppCompatActivity {
    HotelSwiperAdapter hotelAdapter;
    ArrayList<Hotel> al;
    Results mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_swiper);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.swiper);


        mResults = Results.getInstance();
        al = mResults.getHotels();

        //choose your favorite adapter
        hotelAdapter = new HotelSwiperAdapter(HotelSwiperActivity.this, al);

        //set the listener and the adapter
        flingContainer.setAdapter(hotelAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                Hotel turnaround = al.get(0);
                al.add(turnaround);
                al.remove(0);
                hotelAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(HotelSwiperActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(HotelSwiperActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
//                al.add("XML ".concat(String.valueOf(i)));
//                hotelAdapter.notifyDataSetChanged();
//                Log.d("LIST", "notified");
//                i++;
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                //TODO: send intent to places viewer with hotel info for top
                Toast.makeText(HotelSwiperActivity.this, "Clicked!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
