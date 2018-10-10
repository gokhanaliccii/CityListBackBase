package com.gokhanaliccii.citylist.data.datasource.local;

import com.gokhanaliccii.citylist.data.datasource.CityDataSource;

import java.io.InputStream;

public class CityServiceLocator {

    private static CityServiceLocator sINSTANCE;

    private CityDataSource cityDataSource;

    private CityServiceLocator() {
    }

    public static CityServiceLocator getInstance() {
        if (sINSTANCE == null) {
            sINSTANCE = new CityServiceLocator();
        }

        return sINSTANCE;
    }

    public void prepareCities(InputStream is) {
        cityDataSource = new LocalCitySource(is);
    }

    public CityDataSource getCityDataSource() {
        return cityDataSource;
    }
}