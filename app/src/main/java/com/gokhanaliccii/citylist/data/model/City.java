package com.gokhanaliccii.citylist.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class City  implements Serializable{

    @JsonProperty("_id")
    private long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("coord")
    private Coordinate coord;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplayName() {
        return String.format("%s,%s", name, country);
    }

    public Coordinate getCoord() {
        return coord;
    }
}
