package com.example.travlr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

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
    OnSwipeTouchListener onSwipeTouchListener;
    String dateStartAnswer;
    String dateEndAnswer;
    RelativeLayout fragmentFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mResult = Results.getInstance();


        fragmentFrame = (RelativeLayout)findViewById(R.id.fragmentFrame);

        fm = getSupportFragmentManager();
        locationEditFragment = new LocationEditFragment();
        fm.beginTransaction().replace(R.id.fragmentFrame, locationEditFragment).commit();


        onSwipeTouchListener = new OnSwipeTouchListener(MainActivity.this) {
            @Override
        public void onSwipeLeft() {
            if (fm.getFragments().contains(locationEditFragment)) {
                locationAnswer = locationEditFragment.enterLocation.getText().toString();
                dateEditFragment = new DateEditFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.swipe_in, R.anim.swipe_out);
                transaction.replace(R.id.fragmentFrame, dateEditFragment).commit();
//                fm.beginTransaction().replace(R.id.fragmentFrame, dateEditFragment).commit();
//                overridePendingTransition(R.anim.swipe_in, R.anim.swipe_out);

            }
            else if (fm.getFragments().contains(dateEditFragment)) {
                dateStartAnswer = dateEditFragment.currentStartDate;
                dateEndAnswer = dateEditFragment.currentEndDate;

                SearchParametersObject thisSearch =
                        new SearchParametersObject(locationAnswer, dateStartAnswer, dateEndAnswer);
                mResult.setSearchObject(thisSearch);

                conceptPickerFragment = new ConceptPickerFragment();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.swipe_in, R.anim.swipe_out);
                transaction.replace(R.id.fragmentFrame, conceptPickerFragment).commit();
                //fm.beginTransaction().replace(R.id.fragmentFrame, conceptPickerFragment).commit();
            }

            else if (fm.getFragments().contains(conceptPickerFragment)) {

                //String destination = "austin, tx";
                String destination = mResult.getSearchObject().getmLocation();
                String startDate = mResult.getSearchObject().getmStartDate();
                String endDate = mResult.getSearchObject().getmEndDate();
                String getPricing = "true";
                String rooms = "1";
                String adults = "1";
                String children = "0";
                String tripType = "none";
                String concepts = mResult.getPlacesConceptsArray().substring(0, mResult.getPlacesConceptsArray().length()-1);



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
        }
    };

        fragmentFrame.setOnTouchListener(onSwipeTouchListener);

        //TODO: move to conceptPickerFrag







    }

    public void setResults(String result) {

        if (mResult.getHotels() != null) {
            mResult.clearHotels();
        }

        try {

            Log.d("RESULT", "setResults: " + result);
            JSONObject resultObject = new JSONObject(result);
            JSONArray accommodationArray = resultObject.getJSONArray("accommodations");
            for (int i = 0; i < 7; i++) {
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

        Intent intent = new Intent(MainActivity.this, HotelSwiperActivity.class);
        startActivity(intent);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        dateEditFragment.onDateSet(datePicker, year, monthOfYear, dayOfMonth);
    }
    }










