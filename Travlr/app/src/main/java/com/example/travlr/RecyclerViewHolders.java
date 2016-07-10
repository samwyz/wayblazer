package com.example.travlr;

/**
 * Created by samwyz on 7/9/16.
 */

import android.support.v7.widget.RecyclerView;
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
//        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }

    public void putThePhoto(String ur){
        Picasso.with(itemView.getContext())
                //.load(productPhotoUR)
                .load("android.resource://com.example.micahherrera.project2ecommerceapp/drawable/"+ur)
                .resize(145, 145)
                .onlyScaleDown()
                .into(conceptPhoto);
    }
}
