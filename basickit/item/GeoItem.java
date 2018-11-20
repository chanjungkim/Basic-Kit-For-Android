package com.koreanlab.bestfood.item;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Chanj on 17/02/2018.
 */

public class GeoItem {
    public static double knownLatitude;
    public static double knownLongitude;

    public static LatLng getKnownLocation(){
        if(knownLatitude == 0 || knownLongitude == 0){
            return new LatLng(37.566229, 126.977689);
        } else {
            return new LatLng(knownLatitude, knownLongitude);
        }
    }
}
