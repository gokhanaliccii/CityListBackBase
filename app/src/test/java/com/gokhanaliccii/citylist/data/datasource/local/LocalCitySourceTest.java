package com.gokhanaliccii.citylist.data.datasource.local;

import com.gokhanaliccii.citylist.data.model.City;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
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
        List<City> filteredCities = citySource.getFilteredCitiesByDisplayName(citiesStartWith);

        assertThat(filteredCities.size(), equalTo(expectedCityCount));
    }

    @Test
    public void should_CloseStreamAfterReadCitiesCorrectly() throws IOException {
        InputStream is = Mockito.spy(getClass().getClassLoader().getResourceAsStream("test_city_array.json"));
        LocalCitySource citySource = new LocalCitySource(is);
        citySource.getAllCities();

        Mockito.verify(is).close();
    }

    @Test
    public void should_FilterCitiesWithoutCaseSensitivity() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("test_city_array.json");
        LocalCitySource citySource = new LocalCitySource(is);
        List<City> firstFilteredList = citySource.getFilteredCitiesByDisplayName("N");
        List<City> secondFilteredList = citySource.getFilteredCitiesByDisplayName("n");

        assertThat(firstFilteredList.size(), equalTo(secondFilteredList.size()));
    }


}