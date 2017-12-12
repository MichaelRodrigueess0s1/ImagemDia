package com.bitw.bitw.imagemdia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.bitw.bitw.imagemdia.adapter.PlanetAdapter;
import com.bitw.bitw.imagemdia.model.Planet;
import com.bitw.bitw.imagemdia.service.PicassoImageLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ListView planetList = (ListView) findViewById(R.id.planet_list);
        planetList.setAdapter(new PlanetAdapter(this,
                getPlanets(), new PicassoImageLoader()));
    }

    private List<Planet> getPlanets() {
        Gson gson = new Gson();
        String json = Util.readFileFromAssets(this, "planets.json");
        return gson.fromJson(json, new TypeToken<List<Planet>>(){}.getType());
    }

    @OnClick(R.id.btn_imagemdia)
    public void goImagemDia(android.support.design.widget.FloatingActionButton button) {
        Intent i = new Intent(this, ImagemActivity.class);
        startActivity(i);
    }
}
