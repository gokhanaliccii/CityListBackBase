package com.gokhanaliccii.citylist.data.datasource.local;

import com.gokhanaliccii.citylist.data.model.City;

import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class LocalCitySourceTest {

    @Test
    public void should_LoadReturnCitiesCorrectly() {
        final int expectedCityCount = 6;

        InputStream is = getClass().getClassLoader().getResourceAsStream("test_city_array.json");
        LocalCitySource citySource = new LocalCitySource(is);
        List<City> allCities = citySource.getAllCities();

        assertThat(allCities.size(), equalTo(expectedCityCount));
    }

    @Test
    public void should_LoadFilterCitiesByThemIdCorrectly() {
        final String citiesStartWith = "N";
        final int expectedCityCount = 1;

        InputStream is = getClass().getClassLoader().getResourceAsStream("test_city_array.json");
        LocalCitySource citySource = new LocalCitySource(is);
        List<City> allCities = citySource.getFilteredCitiesByDisplayName(citiesStartWith);

        assertThat(allCities.size(), equalTo(expectedCityCount));
    }




}