package com.gokhanaliccii.citylist.ui.cities.citylist;

import com.gokhanaliccii.citylist.base.Interruptable;
import com.gokhanaliccii.citylist.data.model.City;

import java.util.List;

public interface CityContract {

    interface View {

        void onCitiesLoaded(List<City> cities);
    }

    interface ViewModel<Model> extends Interruptable {

        void loadAllCities();

        void setModel(Model model);

        void filterCities(String input);

        void onCitiesLoaded(List<City> cities);
    }

    interface Model extends Interruptable {

        void loadAllCities();

        void loadFilteredCities(String input);
    }
}
