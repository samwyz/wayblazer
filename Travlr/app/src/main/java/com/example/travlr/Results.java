package com.example.travlr;

import java.util.ArrayList;

/**
 * Created by samwyz on 7/9/16.
 */
public class Results {

    private static ArrayList<Hotel> mHotelList;
    private static ArrayList<Place> mPlaceList;
    private static Results ourInstance = new Results();
    private static SearchParametersObject searchParametersObject;
    private static String mPlacesConceptsArray;
    private static String mWBConceptsArray;

    public static Results getInstance() {
        return ourInstance;
    }

    private Results() {
    }

    public int addHotel(Hotel hotel) {
        if (mHotelList == null){
            mHotelList = new ArrayList<>();
        } mHotelList.add(hotel);
        return mHotelList.indexOf(hotel);
    }

    public int addPlace(Place place) {
        if (mPlaceList == null){
            mPlaceList = new ArrayList<>();
        } mPlaceList.add(place);
        return mPlaceList.indexOf(place);
    }

    public void addWBConcept(String concept){
        if (mWBConceptsArray == null){
            mWBConceptsArray = "";
        } mWBConceptsArray += concept+", ";

    }

    public void addPlacesConcept(String concept){
        if (mPlacesConceptsArray == null){
            mPlacesConceptsArray = "";
        } mPlacesConceptsArray+= concept+"|";

    }

    public ArrayList<Hotel> getHotels(){
        return mHotelList;
    }

    public void clearHotels(){
        mHotelList.clear();
    }

    public ArrayList<Place> getPlaces() {
        return mPlaceList;
    }

    public void clearPlaces() {
        mPlaceList.clear();
    }

    public void setSearchObject(SearchParametersObject object){
        this.searchParametersObject = object;
    }

    public SearchParametersObject getSearchObject(){
        return this.searchParametersObject;
    }

    public String getPlacesConceptsArray(){
        return mPlacesConceptsArray;
    }
    public String getWBConceptsArray(){
        return mWBConceptsArray;
    }
}
