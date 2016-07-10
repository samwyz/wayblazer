package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class HotelSwiperActivity extends AppCompatActivity{
    HotelSwiperAdapter hotelAdapter;
    ArrayList<Hotel> al;
    SwipeFlingAdapterView.onFlingListener flingListener;
    Results mResults;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_swiper);

        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.swiper);

        mResults = Results.getInstance();
        al = mResults.getHotels();

        //choose your favorite adapter
        hotelAdapter = new HotelSwiperAdapter(HotelSwiperActivity.this, al);

        //set the listener and the adapter
        flingContainer.setAdapter(hotelAdapter);

        flingListener = new SwipeFlingAdapterView.onFlingListener() {
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

            }

            @Override
            public void onRightCardExit(Object dataObject) {

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
        };

        flingContainer.setFlingListener(flingListener);

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Intent intent = new Intent(HotelSwiperActivity.this, PlacesAPI.class);
                Hotel hotel = al.get(itemPosition);
                intent.putExtra("latitude", hotel.getLatitude());
                intent.putExtra("longitude", hotel.getLongitude());
                startActivity(intent);
                //TODO: send intent to places viewer with hotel info for top
                //Toast.makeText(HotelSwiperActivity.this, "Clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.positive_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.remove(0);
                flingContainer.getTopCardListener().selectLeft();
                Hotel h = al.get(0);

                hotelAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setCurrentItem (int item, boolean smoothScroll) {
        viewPager.setCurrentItem(item, smoothScroll);
    }




}
