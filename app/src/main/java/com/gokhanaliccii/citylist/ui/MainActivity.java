package com.gokhanaliccii.citylist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gokhanaliccii.citylist.R;
import com.gokhanaliccii.citylist.ui.citylist.CityListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_fragment, CityListFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
