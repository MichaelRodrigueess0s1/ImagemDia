package com.bitw.bitw.imagemdia.service;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import com.bitw.bitw.imagemdia.Interfaces.ImageLoader;
import com.squareup.picasso.Picasso;

/**
 * Created by Michael on 06/12/2017.
 */

public class PicassoImageLoader implements ImageLoader {


    @Override
    public void loadImage(ImageView view, String source) {
        Picasso.with(view.getContext()).load(source).into(view);
    }

}
