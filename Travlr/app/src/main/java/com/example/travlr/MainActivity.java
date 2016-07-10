package com.example.travlr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String responseBody;
    private static String API_KEY = "Y1OXOaUDRn2PdRumz22F242YqVYhqM2o74JyoHSD";
    private Results mResult;
    private Hotel mHotel;

    ArrayAdapter arrayAdapter;
    ArrayList al;
    LocationEditFragment locationEditFragment;
    DateEditFragment dateEditFragment;
    ConceptPickerFragment conceptPickerFragment;
    FragmentManager fm;
    String locationAnswer;

    String dateStartAnswer;
    String dateEndAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = Results.getInstance();

        String destination = "Austin, TX";
        String startDate = "2016-10-06T05:00:00.000Z";
        String endDate = "2016-10-10T05:00:00.000Z";
        String getPricing = "true";
        String rooms = "1";
        String adults = "1";
        String children = "0";
        String tripType = "none";
        String concepts = "nature";


        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("destination", destination)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("getPricing", getPricing)
                .add("rooms", rooms)
                .add("adults", adults)
                .add("children", children)
                .add("tripType", tripType)
                .add("concepts", concepts)
                .build();

        Request request = new Request.Builder()
                .url("https://api.wayblazer.com/v1/accommodations/search")
                .header("x-api-key", API_KEY)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                responseBody = response.body().string();
                setResults(responseBody);

            }
        });
    }

    public void setResults(String result) {

        try {

            JSONObject resultObject = new JSONObject(result);
            JSONArray accommodationArray = resultObject.getJSONArray("accommodations");
            for (int i = 0; i < accommodationArray.length(); i++) {
                JSONObject hotelObject = accommodationArray.getJSONObject(i);
                JSONObject scoreObject = hotelObject.getJSONObject("score");
                String score = scoreObject.getString("score");
                JSONObject attractionObject = hotelObject.getJSONObject("attraction");
                String name = attractionObject.getString("name");
                JSONObject locationObject = attractionObject.getJSONObject("location");
                String latitude = locationObject.getString("latitude");
                String longitude = locationObject.getString("longitude");
                String address = locationObject.getString("formattedAddress");
                JSONObject imageObject = hotelObject.getJSONObject("image");
                JSONObject urlsObject = imageObject.getJSONObject("urls");
                String original = urlsObject.getString("original");
                mHotel = new Hotel(name, score, latitude, longitude, address, original);
                mResult.addHotel(mHotel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        fm = getSupportFragmentManager();
        locationEditFragment = new LocationEditFragment();
        fm.beginTransaction().replace(R.id.fragmentFrame, locationEditFragment).commit();

        OnSwipeTouchListener onSwipeTouchListener = new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
                if (fm.getFragments().contains(locationEditFragment)) {
                    locationAnswer = locationEditFragment.locationChoice.getText().toString();
                    fm.beginTransaction().replace(R.id.fragmentFrame, dateEditFragment).commit();

                }
                if (fm.getFragments().contains(dateEditFragment)) {
                    dateStartAnswer = dateEditFragment.startDate.getText().toString();
                    dateEndAnswer = dateEditFragment.endDate.getText().toString();
                    fm.beginTransaction().replace(R.id.fragmentFrame, conceptPickerFragment).commit();
                }

                if (fm.getFragments().contains(conceptPickerFragment)) {
                    SearchParametersObject thisSearch =
                            new SearchParametersObject(locationAnswer, dateStartAnswer, dateEndAnswer);
                    mResult.setSearchObject(thisSearch);
                    Intent intent = new Intent(MainActivity.this, HotelSwiperActivity.class);
                    startActivity(intent);

                    //TODO: send intent to hotel swiper activity
                }
                //your actions
            }
        };


    }
}









