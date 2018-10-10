package com.gokhanaliccii.citylist.data.datasource.local;

import com.gokhanaliccii.citylist.data.model.City;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;

public class LocalCitySourceTest {

    private InputStream is;
    private LocalCitySource citySource;

    @Before
    public void setUp() {
        is = getClass().getClassLoader().getResourceAsStream("test_city_array.json");
        citySource = new LocalCitySource(is);
    }

    @Test
    public void should_LoadReturnCitiesCorrectly() {
        final int expectedCityCount = 6;
        List<City> allCities = citySource.getAllCities();

        assertThat(allCities.size(), equalTo(expectedCityCount));
    }

    @Test
    public void should_LoadFilterCitiesByThemIdCorrectly() {
        final String citiesStartWith = "N";
        final int expectedCityCount = 1;

        List<City> filteredCities = citySource.getFilteredCitiesByDisplayName(citiesStartWith);

        assertThat(filteredCities.size(), equalTo(expectedCityCount));
    }

    @Test
    public void should_CloseStreamAfterReadCitiesCorrectly() throws IOException {
        is = spy(getClass().getClassLoader().getResourceAsStream("test_city_array.json"));
        citySource = new LocalCitySource(is);

        citySource.getAllCities();

        Mockito.verify(is).close();
    }

    @Test
    public void should_FilterCitiesWithoutCaseSensitivity() {
        List<City> firstFilteredList = citySource.getFilteredCitiesByDisplayName("N");
        List<City> secondFilteredList = citySource.getFilteredCitiesByDisplayName("n");

        assertThat(firstFilteredList.size(), equalTo(secondFilteredList.size()));
    }

    @Test
    public void should_FindCityByIdCorrectly() {
        City city = citySource.getCityById(707860);

        assertThat(city.getName(), equalTo("Hurzuf"));
    }
}