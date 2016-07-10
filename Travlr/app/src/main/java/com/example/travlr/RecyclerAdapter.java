package com.example.travlr;

/**
 * Created by brendan on 7/9/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brendan on 7/9/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.myViewHolder> {
    LayoutInflater inflater;
    List<Place> placesList;
    Context context;

    public RecyclerAdapter(List<Place> placeList, Context context) {
        this.placesList = placeList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.from(parent.getContext()).inflate(R.layout.places_item,
                parent, false);
        myViewHolder holder = new myViewHolder(v, context, placesList);
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        String imgRef = placesList.get(position).getmImageReference();

        if (!imgRef.equalsIgnoreCase("")) {
            Picasso.with(this.context)
                    .load(imageRef(imgRef))
                    .resize(400,400)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_travlr_logo)
                    .into(holder.placeImage);
        }else {
            holder.placeImage.setImageResource(R.mipmap.ic_launcher);
        }
        // TODO: get default images for different concepts



        holder.placeName.setText(placesList.get(position).getmName());
        holder.placeAddress.setText(placesList.get(position).getmAddress());


    }


    @Override
    public int getItemCount() {
        return placesList.size();
    }

    // setting the ViewHolder for my recyclerview with a clickListener

    public class myViewHolder extends RecyclerView.ViewHolder {

        public ImageView placeImage;
        public TextView placeName;
        public TextView placeAddress;
        List<Place> mPlaceList = new ArrayList<>();
        Context context;

        public myViewHolder(View itemView, Context context, List<Place> mPlaceList) {
            super(itemView);
            this.mPlaceList = mPlaceList;
            this.context = context;

            placeImage = (ImageView) itemView.findViewById(R.id.places_image);
            placeName = (TextView) itemView.findViewById(R.id.places_name);
            placeAddress = (TextView) itemView.findViewById(R.id.places_address);


        }
    }

    public String imageRef(String ref) {
        String first = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
        String key = "&key=AIzaSyDdfoXP4rLO-Wz4tzXAY0YTQmqpfW20Myg";
        String full = first + ref + key;
        return full;
    }
}
