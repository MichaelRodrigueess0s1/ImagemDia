package com.bitw.bitw.imagemdia.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Michael on 10/12/2017.
 */

public class PictureOfDay implements Serializable{

    @Override
    public String toString() {
        return "PictureOfDay{" +
                "title='" + title + '\'' +
                ", explanation='" + explanation + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", copyright='" + copyright + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }

    private String title;
    private String explanation;
    private String url;
    private String date;
    private String copyright;
    @SerializedName("media_type")
    private String mediaType;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


}