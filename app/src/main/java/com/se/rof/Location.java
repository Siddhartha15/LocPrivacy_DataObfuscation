package com.se.rof;

import static java.lang.Math.pow;

class Location{
    double lat, lon;

    double getDistance(Location loc2){
        return Math.sqrt( pow(lat - loc2.lat, 2) + pow(lon - loc2.lon, 2) );
    }


    Location(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    Location() {}
}
