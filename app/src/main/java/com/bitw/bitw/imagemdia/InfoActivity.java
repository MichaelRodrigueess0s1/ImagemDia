package com.bitw.bitw.imagemdia;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.bitw.bitw.imagemdia.model.PictureOfDay;

public class InfoActivity extends AppCompatActivity {

    TextView txtPicture;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ctx = this;
        txtPicture = (TextView) findViewById(R.id.textoPicture);
        final PictureOfDay pictureOfDay = (PictureOfDay) getIntent().getSerializableExtra("pictureOfDay");
        txtPicture.setText(pictureOfDay.toString());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnback);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnback);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ctx, ImagemActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
