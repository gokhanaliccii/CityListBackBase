package com.gokhanaliccii.citylist.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

    @JsonProperty("lon")
    private double lon;
    @JsonProperty("lat")
    private double lat;
}
