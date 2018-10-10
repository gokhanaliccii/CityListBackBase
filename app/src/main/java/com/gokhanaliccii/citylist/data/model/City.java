package com.gokhanaliccii.citylist.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class City {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country")
    private String country;
    @JsonProperty("coord")
    private Coordinate coord;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplayName() {
        return String.format("%s,%s", name, country);
    }

}
