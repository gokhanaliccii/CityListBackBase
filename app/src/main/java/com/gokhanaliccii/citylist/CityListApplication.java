package com.gokhanaliccii.citylist;

import android.app.Application;
import android.content.res.AssetManager;

import com.gokhanaliccii.citylist.data.datasource.local.CityServiceLocator;

import java.io.IOException;
import java.io.InputStream;

public class CityListApplication extends Application {

    private static final String CITIES_FILE = "cities.json";

    @Override
    public void onCreate() {
        super.onCreate();

        loadCities();
    }

    private void loadCities() {
        AssetManager assetManager = getApplicationContext().getAssets();
        try {
            InputStream is = assetManager.open(CITIES_FILE);
            CityServiceLocator.getInstance().prepareCities(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
