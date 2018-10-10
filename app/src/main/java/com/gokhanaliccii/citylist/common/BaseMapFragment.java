package com.gokhanaliccii.citylist.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public abstract class BaseMapFragment extends BaseFragment {

    protected GoogleMap mGoogleMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getMapView().onCreate(savedInstanceState);
        getMapView().getMapAsync(googleMap -> {
            mGoogleMap = googleMap;
            onMapViewReady();
        });

        return super.

                onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void onResume() {
        getMapView().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        getMapView().onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        getMapView().onStop();
        super.onStop();
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
