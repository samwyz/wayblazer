package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by micahherrera on 7/9/16.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView conceptPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        conceptPhoto = (ImageView)itemView.findViewById(R.id.concept_photo);
    }

    @Override
    public void onClick(View view) {
        if(conceptPhoto.getAlpha()==1f) {
            conceptPhoto.setAlpha(.5f);
            Log.d("VIEW", "onClick: "+getAdapterPosition());
            switch (getAdapterPosition()){
                case 0:
                    Results.getInstance().addPlacesConcept("food");
                    Results.getInstance().addWBConcept("food");

           }

        } else {
            conceptPhoto.setAlpha(1f);
        }

    }

    public void putThePhoto(String ur){
        Picasso.with(itemView.getContext())
                //.load(productPhotoUR)
                .load("android.resource://com.example.travlr/drawable/"+ur)
                .resize(380, 380)
                .onlyScaleDown()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(conceptPhoto);
    }
}
