package com.bitw.bitw.imagemdia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitw.bitw.imagemdia.Interfaces.ImageLoader;
import com.bitw.bitw.imagemdia.R;
import com.bitw.bitw.imagemdia.model.Planet;

import java.util.List;

/**
 * Created by Michael on 06/12/2017.
 */

public class PlanetAdapter extends BaseAdapter {

    private Context context;
    private List<Planet> planets;
    private ImageLoader imageLoader;

    public PlanetAdapter(Context context, List<Planet> planets, ImageLoader imageLoader) {
        this.context = context;
        this.planets = planets;
        this.imageLoader = imageLoader;
    }

    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int position) {
        return planets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return planets.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.planet_list_item,
                    parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        configureItem(holder, planets.get(position));

        return convertView;
    }

    private void configureItem(ViewHolder holder, Planet planet) {
        holder.name.setText(planet.getName());
        holder.description.setText(planet.getShortDescription());

        if (imageLoader != null) {
            imageLoader.loadImage(holder.thumb, planet.getImageUrl());
        }
    }
    private class ViewHolder {
        private ImageView thumb;
        private TextView name;
        private TextView description;

        public ViewHolder(View parent) {
            thumb = (ImageView) parent.findViewById(R.id.planet_thumb);
            name = (TextView) parent.findViewById(R.id.planet_name);
            description = (TextView) parent.findViewById(R.id.planet_description);
        }
    }


}
