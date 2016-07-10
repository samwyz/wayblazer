package com.example.travlr;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by micahherrera on 7/10/16.
 */
public class HotelSwiperCardAdapter extends RecyclerView.Adapter<HotelSwiperCardHolder> {

    Context mContext;
    ArrayList<Hotel> mHotels;

    public HotelSwiperCardAdapter(Context context, ArrayList<Hotel> itemList) {
        this.mHotels = itemList;
        this.mContext = context;
    }

    @Override
    public HotelSwiperCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_view, null);
        HotelSwiperCardHolder rcv = new HotelSwiperCardHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(HotelSwiperCardHolder holder, int position) {
        holder.putThePhoto(mHotels.get(position).getPicUrl());
        holder.hotelName.setText(mHotels.get(position).getHotelName());
        holder.hotelDesc.setText(mHotels.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return this.mHotels.size();
    }
}
