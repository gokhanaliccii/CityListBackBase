package com.gokhanaliccii.citylist.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public abstract class BaseMapFragment extends BaseFragment {

    protected GoogleMap mGoogleMap;

    protected void loadGoogleMap(@Nullable Bundle savedInstanceState){
        getMapView().onCreate(savedInstanceState);
        getMapView().onResume();
        getMapView().getMapAsync(googleMap -> {
            mGoogleMap = googleMap;
            onMapViewReady();
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        getMapView().onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMapView().onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getMapView().onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMapView().onDestroy();
    }

    public abstract void onMapViewReady();

    public abstract MapView getMapView();
}
