package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */
public class Hotel {

    private String hotelName;
    private String score;
    private String latitude;
    private String longitude;
    private String address;
    private String picUrl;
    private String priceUrl;

    public Hotel(String hotelName, String score, String latitude, String longitude, String address, String picUrl, String priceUrl) {
        this.hotelName = hotelName;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.picUrl = picUrl;
        this.priceUrl = priceUrl;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPriceUrl(){
        return this.priceUrl;
    }

    public void setPriceUrl(String priceUrl){
        this.priceUrl = priceUrl;
    }
}
