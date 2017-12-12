package com.bitw.bitw.imagemdia.Interfaces;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

/**
 * Created by Michael on 06/12/2017.
 */

public interface ImageLoader {
    void loadImage(ImageView view, String source);
    //void loadBackgroundImage(ConstraintLayout layout, String source);
}