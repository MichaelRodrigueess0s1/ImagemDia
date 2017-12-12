package com.bitw.bitw.imagemdia.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.ANResponse;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.widget.ANImageView;
import com.bitw.bitw.imagemdia.ImagemActivity;
import com.bitw.bitw.imagemdia.model.PictureOfDay;

import org.json.JSONArray;

import static android.content.ContentValues.TAG;

/**
 * Created by Michael on 11/12/2017.
 */

public class HttpClient extends AsyncTask<String, String, PictureOfDay>{

    public PictureOfDay getPictureOfDaySync(final View view)
    {
        PictureOfDay picc = new PictureOfDay();


        new AsyncTask<Void, Void, PictureOfDay>() {
            @Override
            protected PictureOfDay doInBackground(Void... voids) {

                ANRequest request = AndroidNetworking.get("https://api.nasa.gov/planetary/apod?api_key=wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                        //.addPathParameter("userId", "wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                        //.setPriority(Priority.HIGH)
                        .build();

                ANResponse<PictureOfDay> response = request.executeForJSONObject();
                if(response.isSuccess())
                {

                    //picc = response.getResult();
                }
                return  null;
            }
            @Override
            protected void onPostExecute(PictureOfDay pictureOfDay) {
            }
        }.execute();

        return  picc;
    }

    public void getPictureOfDay(final ANImageView imageDia, TextView txtPicture) {
        AndroidNetworking.get("https://api.nasa.gov/planetary/apod?api_key={key}")
                .addPathParameter("key", "wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(PictureOfDay.class, new ParsedRequestListener<PictureOfDay>() {
                    @Override
                    public void onResponse(PictureOfDay pictureOfDay) {

                        if(!pictureOfDay.getMediaType().equals("video"))
                        {
                            imageDia.setImageUrl(pictureOfDay.getUrl());

                        }else
                        {

                        }

                    }
                    @Override
                    public void onError(ANError anError) {
                        // handle error
                    }
                });
    }

    @Override
    protected void  onPreExecute()
    {

    }

    @Override
    protected void onPostExecute(PictureOfDay param)
    {

    }

    @Override
    protected PictureOfDay doInBackground(String... strings) {

        ANRequest request = AndroidNetworking.get("https://api.nasa.gov/planetary/apod?api_key=wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                //.addPathParameter("userId", "wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                //.setPriority(Priority.HIGH)
                .build();

        ANResponse<PictureOfDay> response = request.executeForJSONObject();
        if(response.isSuccess())
        {

            //picc = response.getResult();
        }



        return null;
    }
}
