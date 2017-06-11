package me.androidbox.busbybaking.model;

import org.parceler.Parcel;

/**
 * Created by steve on 5/24/17.
 */
@Parcel
public class Steps {
    private int id;
    private String shortDescription;
    private String videoURL;
    private String thumbnailURL;

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
