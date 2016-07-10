package com.example.travlr;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlacesAPI extends AppCompatActivity {
    private static final String PLACES_KEY = "&key=AIzaSyDdfoXP4rLO-Wz4tzXAY0YTQmqpfW20Myg";
    private static final String PLACES_URL="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
    private static final String PLACES_QUERY="&rankby=distance&keyword=";

    OkHttpClient client;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placesapi_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.placesapi_recycler);
        layoutManager = new LinearLayoutManager(PlacesAPI.this);
        mRecyclerView.setLayoutManager(layoutManager);
        client = new OkHttpClient();

        String search = concatSearch(getIntent().getStringExtra("latitude"),getIntent().getStringExtra("longitude"));
        new DownloadUrlTask().execute(search);
    }


    private class DownloadUrlTask extends AsyncTask<String, Void, List<Place>> {
        @Override
        protected List<Place> doInBackground(String... urls) {
            try {
                Request request = new Request.Builder()
                        .url(urls[0])
                        .build();
                Response response = client.newCall(request).execute();
                return parseJSON(response.body().string());

            } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Place> s) {
            Log.d("TAG", "Post Execute list size = " + s.size());
            mAdapter = new RecyclerAdapter(s, PlacesAPI.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    // Method to create the url for searching places
    String concatSearch(String Lat,String Long) {
        String placesQuery = PLACES_QUERY + Results.getInstance()
                .getPlacesConceptsArray().substring(0, Results.getInstance().getPlacesConceptsArray().length()-1);
        String location = Lat + "," + Long;
        return PLACES_URL + location + placesQuery + PLACES_KEY ;

    }

    public List<Place> parseJSON(String toParse) throws JSONException {
        JSONObject results = new JSONObject(toParse);
        JSONArray eachResult = results.getJSONArray("results");

        Place newPlace;
        List<Place> placesResults = new ArrayList<>();
        for (int i = 0; i < eachResult.length(); i++) {
            String photoRef;

            JSONObject newResult = eachResult.getJSONObject(i);
            String newName = newResult.getString("name");

            // Gets the photo reference to show actual image
            JSONArray photoArray;
            if (newResult.has("photos")) {
                photoArray = newResult.getJSONArray("photos");
                JSONObject photo = photoArray.getJSONObject(0);
                photoRef = photo.getString("photo_reference");

            } else {
                photoRef = "";
            }
            // Gets the business address
            String address = newResult.getString("vicinity");

            newPlace = new Place(newName, photoRef, address);
            placesResults.add(newPlace);


        }

        return placesResults;
    }


}