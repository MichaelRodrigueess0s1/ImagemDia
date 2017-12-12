package com.bitw.bitw.imagemdia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bitw.bitw.imagemdia.Interfaces.INasaAPI;
import com.bitw.bitw.imagemdia.Interfaces.ImageLoader;
import com.bitw.bitw.imagemdia.model.PictureOfDay;
import com.bitw.bitw.imagemdia.service.HttpClient;
import com.bitw.bitw.imagemdia.service.PicassoImageLoader;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagemActivity extends AppCompatActivity {

    //private ImageLoader imageLoader;
    private com.androidnetworking.widget.ANImageView imageDia;
    private ImageButton imgbuttonvideo;
    private  String urlVideo = "";
    private TextView txtPicture;
    private HttpClient httpClient;
    PictureOfDay pictureOfDay;
    Context ctx;
    //private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagem);
        initializeComponents();
        changeStatusBarColor();
        httpClient = new HttpClient();
        ctx = this;
        ButterKnife.bind(this);
        buttonGoInfo();
        getPictureOfday();
    }


    private  void changeStatusBarColor()
    {
        getSupportActionBar().hide();
        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.BLACK);
        }
    }

    @OnClick(R.id.imgbuttonvideo)
    public void openMovie()
    {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(urlVideo));
        startActivity(i);
    }


    private void getPictureOfDayRetrofit()
    {
        //httpClient.getPictureOfDay(imageDia, txtPicture);
        ////imageDia.setImageUrl("http://blogs.discovermagazine.com/crux/files/2017/06/universe.jpg"); //getBitmapFromURL("http://looksok.files.wordpress.com/2011/12/me.jpg");
        //imageLoader.loadImage(imageDia, "http://blogs.discovermagazine.com/crux/files/2017/06/universe.jpg"); //getBitmapFromURL("http://looksok.files.wordpress.com/2011/12/me.jpg");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/apod?api_key=wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        INasaAPI nasaAPI = retrofit.create(INasaAPI.class);
        Call<PictureOfDay> call = nasaAPI.getPictureOfDay();
        call.enqueue(new Callback<PictureOfDay>() {
            @Override
            public void onResponse(Call<PictureOfDay> call, Response<PictureOfDay> response) {
                if(response.isSuccessful())
                {
                    PictureOfDay pictureOfDay = response.body();
                    if(!pictureOfDay.getMediaType().equals("video"))
                    {
                        imgbuttonvideo.setVisibility(View.INVISIBLE);
                        imageDia.setImageUrl(pictureOfDay.getUrl());
                    }else
                    {

                    }
                    txtPicture.setText(pictureOfDay.getExplanation());
                }
            }
            @Override
            public void onFailure(Call<PictureOfDay> call, Throwable t) {
                Toast.makeText(ctx, "Ocorreu um problema ao recuperar a Imagem do dia", Toast.LENGTH_LONG);
            }
        });
    }

    private  void getPictureOfday()
    {
        AndroidNetworking.get("https://api.nasa.gov/planetary/apod?api_key={key}")
                .addPathParameter("key", "wbigNahADuSF9ad8tJPSuiswddAs2qvp7ZkgTwpE")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(PictureOfDay.class, new ParsedRequestListener<PictureOfDay>() {
                    @Override
                    public void onResponse(PictureOfDay pc) {
                        pictureOfDay = pc;
                        //PictureOfDay pictureOfDay = response.body();
                        if(!pc.getMediaType().equals("video"))
                        {
                            imgbuttonvideo.setVisibility(View.INVISIBLE);
                            imageDia.setImageUrl(pictureOfDay.getUrl());
                        }else
                        {
                            urlVideo = pictureOfDay.getUrl();
                            imageDia.setVisibility(View.INVISIBLE);
                        }
                        txtPicture.setText(pictureOfDay.getExplanation());

                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ctx, "Ocorreu um problema ao recuperar a Imagem do dia", Toast.LENGTH_LONG);
                    }
                });
    }

    public  void buttonGoInfo()
    {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_moreinfo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, InfoActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("pictureOfDay", pictureOfDay);
                intent.putExtras(b);
                //intent.setData(Uri.parse(urlVideo));
                startActivity(intent);
            }
        });
    }


    private void initializeComponents()
    {
        //constraintLayout = (ConstraintLayout)findViewById(R.id.layout_imagem);
        imageDia = (com.androidnetworking.widget.ANImageView) findViewById(R.id.imageDia);
        imgbuttonvideo = (ImageButton) findViewById(R.id.imgbuttonvideo);
        txtPicture = (TextView) findViewById(R.id.textoPicture);
    }


}
