package com.gokhanaliccii.citylist.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Coordinate  implements Serializable{

    @JsonProperty("lon")
    private double lon;
    @JsonProperty("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
