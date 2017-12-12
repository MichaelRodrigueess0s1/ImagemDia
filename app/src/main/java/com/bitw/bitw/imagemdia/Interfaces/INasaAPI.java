package com.bitw.bitw.imagemdia.Interfaces;

import com.bitw.bitw.imagemdia.model.PictureOfDay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Michael on 11/12/2017.
 */

public interface INasaAPI {

    @GET()
    Call<PictureOfDay> getPictureOfDay();

}
