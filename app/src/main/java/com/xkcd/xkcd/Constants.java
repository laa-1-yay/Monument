package com.xkcd.xkcd;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by Karan on 19-03-2016.
 */
public class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.xkcd.xkcd";

    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 10; // 20 meters

    public static final HashMap<String, LatLng> CHECKPOINTS = new HashMap<String, LatLng>();
    static {

        CHECKPOINTS.put("S. RadhaKrishnan", new LatLng(28.6174263,77.1953708));
        CHECKPOINTS.put("Zail Singh", new LatLng(28.6174303,77.1954033));
    }

}
