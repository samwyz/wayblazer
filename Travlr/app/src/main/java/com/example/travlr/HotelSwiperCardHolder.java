package com.example.travlr;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by micahherrera on 7/10/16.
 */
public class HotelSwiperCardHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView hotelImage;
    TextView hotelName;
    TextView hotelDesc;


    public HotelSwiperCardHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        hotelImage = (ImageView)itemView.findViewById(R.id.hotel_view_image);
        hotelName = (TextView)itemView.findViewById(R.id.hotel_view_name);
        hotelDesc = (TextView)itemView.findViewById(R.id.hotel_view_placesNearby);

    }

    @Override
    public void onClick(View view) {


    }

    public void putThePhoto(String ur){
        Picasso.with(itemView.getContext())
                //.load(productPhotoUR)
                .load(ur)
                .resize(380, 380)
                .onlyScaleDown()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(hotelImage);
    }
}
