package com.xkcd.xkcd;

import com.parse.ParseGeoPoint;

/**
 * Created by laavanye on 19/3/16.
 */
public class Checkpoints {

    String image;
    String photo;
    ParseGeoPoint parseGeoPoint;

    public Checkpoints(String image, String photo, ParseGeoPoint parseGeoPoint) {
        this.image = image;
        this.photo = photo;
        this.parseGeoPoint = parseGeoPoint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ParseGeoPoint getParseGeoPoint() {
        return parseGeoPoint;
    }

    public void setParseGeoPoint(ParseGeoPoint parseGeoPoint) {
        this.parseGeoPoint = parseGeoPoint;
    }
}
